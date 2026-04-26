import os
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

# MongoDB Configuration
MONGO_URI = os.getenv('MONGO_URI', 'mongodb://localhost:27017/')
MONGO_DB_NAME = os.getenv('MONGO_DB_NAME', 'marketing_db')
MONGO_COLLECTION_NAME = os.getenv('MONGO_COLLECTION_NAME', 'campaign_data')

# API Configuration
API_HOST = os.getenv('API_HOST', '0.0.0.0')
API_PORT = int(os.getenv('API_PORT', 8000))

# Streamlit Configuration
STREAMLIT_PORT = int(os.getenv('STREAMLIT_PORT', 8501)) 