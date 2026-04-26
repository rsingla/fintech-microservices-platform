import streamlit as st
import pandas as pd
from ..services.database import DatabaseService
from ..models.base_models import CampaignData

db_service = DatabaseService()

def render_upload_section():
    st.header("Upload Data")
    
    uploaded_file = st.file_uploader("Choose a CSV or Excel file", type=["csv", "xlsx"])
    
    if uploaded_file:
        try:
            df = pd.read_csv(uploaded_file) if uploaded_file.name.endswith('.csv') \
                else pd.read_excel(uploaded_file)
            
            df.columns = df.columns.str.replace(' ', '_').str.lower()
            df = df.where(pd.notnull(df), None)
            
            st.subheader("Data Preview")
            st.dataframe(df.head())
            
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
    
    df = pd.DataFrame([camp.dict() for camp in campaigns])
    
    st.sidebar.header("Filters")
    status_filter = st.sidebar.multiselect(
        "Campaign Status",
        options=df['campaign_status'].unique()
    )
    
    if status_filter:
        df = df[df['campaign_status'].isin(status_filter)]
    
    st.dataframe(df) 