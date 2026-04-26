import streamlit as st
from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
import uvicorn
import threading
from .api.routes import router as api_router
from .ui.views import render_upload_section, render_view_section
from .config import API_HOST, API_PORT

# Create FastAPI app
app = FastAPI(
    title="Campaign Manager API",
    description="API for managing marketing campaign data",
    version="1.0.0"
)

# Add CORS middleware
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Include API routes
app.include_router(api_router, prefix="/api")

# Streamlit UI
def run_streamlit():
    st.set_page_config(page_title="Campaign Manager", layout="wide")
    
    st.sidebar.title("Navigation")
    page = st.sidebar.radio("Go to", ["Upload", "View"])
    
    if page == "Upload":
        render_upload_section()
    else:
        render_view_section()

def run_fastapi():
    uvicorn.run(app, host=API_HOST, port=API_PORT)

if __name__ == "__main__":
    # Start FastAPI in a separate thread
    api_thread = threading.Thread(target=run_fastapi, daemon=True)
    api_thread.start()
    
    # Run Streamlit
    run_streamlit() 