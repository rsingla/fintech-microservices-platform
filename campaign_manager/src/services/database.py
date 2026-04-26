from pymongo import MongoClient
from typing import List, Dict, Any
import os
from dotenv import load_dotenv
from ..models.base_models import CampaignData

# Load environment variables
load_dotenv()

class DatabaseService:
    def __init__(self):
        self.mongo_uri = os.getenv('MONGO_URI', 'mongodb://localhost:27017/')
        self.db_name = os.getenv('MONGO_DB_NAME', 'marketing_db')
        self.collection_name = os.getenv('MONGO_COLLECTION_NAME', 'campaign_data')
        self.client = None
        self.db = None
        self.collection = None

    def connect(self):
        """Establish database connection"""
        if not self.client:
            self.client = MongoClient(self.mongo_uri, tlsAllowInvalidCertificates=True)
            self.db = self.client[self.db_name]
            self.collection = self.db[self.collection_name]

    def disconnect(self):
        """Close database connection"""
        if self.client:
            self.client.close()
            self.client = None
            self.db = None
            self.collection = None

    def insert_many(self, data: List[Dict[str, Any]]) -> List[str]:
        """Insert multiple campaign records"""
        self.connect()
        result = self.collection.insert_many(data)
        return [str(id) for id in result.inserted_ids]

    def get_all_campaigns(self) -> List[CampaignData]:
        """Retrieve all campaigns"""
        self.connect()
        campaigns = list(self.collection.find().sort('_id', -1))
        return [CampaignData(**{k: v for k, v in camp.items() if k != '_id'}) 
                for camp in campaigns]

    def __enter__(self):
        self.connect()
        return self

    def __exit__(self, exc_type, exc_val, exc_tb):
        self.disconnect() 