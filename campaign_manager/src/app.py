import streamlit as st
from fastapi import FastAPI, UploadFile, File, HTTPException
from fastapi.middleware.cors import CORSMiddleware
import uvicorn
from typing import List, Dict, Any
import pandas as pd
from io import StringIO
import json
from .services.database import DatabaseService
from .models.base_models import CampaignData
import asyncio
import threading
from streamlit.web.server import Server
from streamlit.runtime.scriptrunner import RerunException, RerunData
from streamlit.runtime.scriptrunner.script_runner import ScriptRunner

# Create FastAPI app
api = FastAPI(
    title="Campaign Manager API",
    description="API for managing marketing campaign data",
    version="1.0.0"
)

# Add CORS middleware
api.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Database service instance
db_service = DatabaseService()

# API Routes
@api.post("/api/upload/json", response_model=Dict[str, Any])
async def upload_json_data(data: List[Dict[str, Any]]):
    """Upload campaign data in JSON format"""
    try:
        # Validate data using Pydantic model
        campaigns = [CampaignData(**camp) for camp in data]
        
        # Insert validated data
        with db_service as db:
            inserted_ids = db.insert_many([camp.dict() for camp in campaigns])
        
        return {
            "message": "Data uploaded successfully",
            "inserted_count": len(inserted_ids),
            "inserted_ids": inserted_ids
        }
        
    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"Error uploading data: {str(e)}"
        )

@api.post("/api/upload/csv", response_model=Dict[str, Any])
async def upload_csv_data(file: UploadFile = File(...)):
    """Upload campaign data using CSV file"""
    try:
        # Verify file type
        if not file.filename.endswith('.csv'):
            raise HTTPException(
                status_code=400,
                detail="Only CSV files are allowed"
            )
        
        # Read CSV content
        content = await file.read()
        csv_data = StringIO(content.decode())
        df = pd.read_csv(csv_data)
        
        # Clean and process data
        df.columns = df.columns.str.replace(' ', '_').str.lower()
        df = df.where(pd.notnull(df), None)
        data = df.to_dict('records')
        
        # Validate using Pydantic model
        campaigns = [CampaignData(**camp) for camp in data]
        
        # Insert validated data
        with db_service as db:
            inserted_ids = db.insert_many([camp.dict() for camp in campaigns])
        
        return {
            "message": "CSV data uploaded successfully",
            "inserted_count": len(inserted_ids),
            "inserted_ids": inserted_ids,
            "filename": file.filename
        }
        
    except Exception as e:
        raise HTTPException(
            status_code=500,
            detail=f"Error uploading CSV: {str(e)}"
        )

# Streamlit UI
def render_upload_section():
    st.header("Upload Data")
    
    # File upload
    uploaded_file = st.file_uploader("Choose a CSV or Excel file", type=["csv", "xlsx"])
    
    if uploaded_file:
        try:
            # Read and process file
            df = pd.read_csv(uploaded_file) if uploaded_file.name.endswith('.csv') \
                else pd.read_excel(uploaded_file)
            
            # Clean data
            df.columns = df.columns.str.replace(' ', '_').str.lower()
            df = df.where(pd.notnull(df), None)
            
            # Preview data
            st.subheader("Data Preview")
            st.dataframe(df.head())
            
            # Upload button
            if st.button("Upload to Database"):
                with st.spinner("Uploading data..."):
                    data = df.to_dict('records')
                    campaigns = [CampaignData(**camp) for camp in data]
                    
                    with db_service as db:
                        inserted_ids = db.insert_many([camp.dict() for camp in campaigns])
                    
                    st.success(f"Successfully uploaded {len(inserted_ids)} records!")
                    
        except Exception as e:
            st.error(f"Error processing file: {str(e)}")

def render_view_section():
    st.header("View Campaigns")
    
    with db_service as db:
        campaigns = db.get_all_campaigns()
    
    if not campaigns:
        st.info("No campaigns found in the database.")
        return
    
    # Convert to DataFrame for display
    df = pd.DataFrame([camp.dict() for camp in campaigns])
    
    # Add filters
    st.sidebar.header("Filters")
    status_filter = st.sidebar.multiselect(
        "Campaign Status",
        options=df['campaign_status'].unique()
    )
    
    # Apply filters
    if status_filter:
        df = df[df['campaign_status'].isin(status_filter)]
    
    # Display data
    st.dataframe(df)

def main():
    st.set_page_config(page_title="Campaign Manager", layout="wide")
    
    # Navigation
    st.sidebar.title("Navigation")
    page = st.sidebar.radio("Go to", ["Upload", "View"])
    
    if page == "Upload":
        render_upload_section()
    else:
        render_view_section()

# Run both Streamlit and FastAPI
def run_fastapi():
    uvicorn.run(api, host="0.0.0.0", port=8000)

def run_streamlit():
    main()

if __name__ == "__main__":
    # Start FastAPI in a separate thread
    api_thread = threading.Thread(target=run_fastapi, daemon=True)
    api_thread.start()
    
    # Run Streamlit
    run_streamlit() 