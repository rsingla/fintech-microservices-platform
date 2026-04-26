import streamlit as st
from pymongo import MongoClient
from typing import List
from .config import MONGO_URI, MONGO_DB_NAME, MONGO_COLLECTION_NAME
from .models.campaign_data import CampaignData

@st.cache_resource
def init_connection():
    """Initialize MongoDB connection with caching"""
    try:
        client = MongoClient(MONGO_URI, 
                           tlsAllowInvalidCertificates=True,
                           serverSelectionTimeoutMS=5000)
        # The ismaster command is cheap and does not require auth.
        client.admin.command('ismaster')
        st.success("MongoDB connection successful!")
        return client
    except Exception as e:
        st.error(f"Could not connect to MongoDB: {e}")
        st.stop()

@st.cache_data(ttl=60)
def get_data() -> List[CampaignData]:
    """Fetch campaign data from MongoDB with caching"""
    client = init_connection()
    db = client[MONGO_DB_NAME]
    collection = db[MONGO_COLLECTION_NAME]
    
    items = list(collection.find().sort('_id', -1))
    campaigns = []
    for item in items:
        try:
            if '_id' in item:
                del item['_id']
            campaigns.append(CampaignData.from_dict(item))
        except Exception as e:
            st.error(f"Error processing campaign data: {e}")
            st.error(f"Problematic document: {item}")
            continue
    return campaigns 