from fastapi import APIRouter, HTTPException, UploadFile, File
from typing import List, Dict, Any
import pandas as pd
from io import StringIO
from ..services.database import DatabaseService
from ..models.base_models import CampaignData

router = APIRouter()
db_service = DatabaseService()

@router.post("/upload/json", response_model=Dict[str, Any])
async def upload_json_data(data: List[Dict[str, Any]]):
    """Upload campaign data in JSON format"""
    try:
        campaigns = [CampaignData(**camp) for camp in data]
        
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

@router.post("/upload/csv", response_model=Dict[str, Any])
async def upload_csv_data(file: UploadFile = File(...)):
    """Upload campaign data using CSV file"""
    try:
        if not file.filename.endswith('.csv'):
            raise HTTPException(
                status_code=400,
                detail="Only CSV files are allowed"
            )
        
        content = await file.read()
        csv_data = StringIO(content.decode())
        df = pd.read_csv(csv_data)
        
        df.columns = df.columns.str.replace(' ', '_').str.lower()
        df = df.where(pd.notnull(df), None)
        data = df.to_dict('records')
        
        campaigns = [CampaignData(**camp) for camp in data]
        
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