from pymongo import MongoClient
from pprint import pprint
import os
from dotenv import load_dotenv
from typing import List, Optional
from datetime import datetime, timedelta

# Load environment variables
load_dotenv()
MONGO_URI = os.getenv("MONGO_URI", "mongodb://localhost:27017/")
MONGO_DB_NAME = os.getenv("MONGO_DB_NAME", "marketing_db")
MONGO_COLLECTION_NAME = os.getenv("MONGO_COLLECTION_NAME", "campaign_data")


# Define data models
class CostBreakdown:
    printing_cost_actual: float
    postage_cost_actual: float
    data_cost_actual: float
    other_costs_actual: float

    def __init__(
        self,
        printing_cost_actual: float,
        postage_cost_actual: float,
        data_cost_actual: float,
        other_costs_actual: float,
    ):
        self.printing_cost_actual = printing_cost_actual
        self.postage_cost_actual = postage_cost_actual
        self.data_cost_actual = data_cost_actual
        self.other_costs_actual = other_costs_actual

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            printing_cost_actual=data["printing_cost_actual"],
            postage_cost_actual=data["postage_cost_actual"],
            data_cost_actual=data["data_cost_actual"],
            other_costs_actual=data["other_costs_actual"],
        )

    def to_dict(self):
        return {
            "printing_cost_actual": self.printing_cost_actual,
            "postage_cost_actual": self.postage_cost_actual,
            "data_cost_actual": self.data_cost_actual,
            "other_costs_actual": self.other_costs_actual,
        }


class CostDetails:
    overall_budget: float
    total_campaign_cost_planned: float
    total_campaign_cost_actual: float
    cost_per_piece_planned: float
    cost_per_piece_actual: float
    cost_breakdown: CostBreakdown

    def __init__(
        self,
        overall_budget: float,
        total_campaign_cost_planned: float,
        total_campaign_cost_actual: float,
        cost_per_piece_planned: float,
        cost_per_piece_actual: float,
        cost_breakdown: CostBreakdown,
    ):
        self.overall_budget = overall_budget
        self.total_campaign_cost_planned = total_campaign_cost_planned
        self.total_campaign_cost_actual = total_campaign_cost_actual
        self.cost_per_piece_planned = cost_per_piece_planned
        self.cost_per_piece_actual = cost_per_piece_actual
        self.cost_breakdown = cost_breakdown

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            overall_budget=data["overall_budget"],
            total_campaign_cost_planned=data["total_campaign_cost_planned"],
            total_campaign_cost_actual=data["total_campaign_cost_actual"],
            cost_per_piece_planned=data["cost_per_piece_planned"],
            cost_per_piece_actual=data["cost_per_piece_actual"],
            cost_breakdown=CostBreakdown.from_dict(data["cost_breakdown"]),
        )

    def to_dict(self):
        return {
            "overall_budget": self.overall_budget,
            "total_campaign_cost_planned": self.total_campaign_cost_planned,
            "total_campaign_cost_actual": self.total_campaign_cost_actual,
            "cost_per_piece_planned": self.cost_per_piece_planned,
            "cost_per_piece_actual": self.cost_per_piece_actual,
            "cost_breakdown": self.cost_breakdown.to_dict(),
        }


class StrategyCell:
    cell_no: str
    cell_description: str
    assigned_creative_id: str
    assigned_offer_code: Optional[str]
    campaign_total_mailed: int
    campaign_total_responses: int
    campaign_total_conversions: int
    campaign_total_conversion_value: float

    def __init__(
        self,
        cell_no: str,
        cell_description: str,
        assigned_creative_id: str,
        assigned_offer_code: Optional[str],
        campaign_total_mailed: int,
        campaign_total_responses: int,
        campaign_total_conversions: int,
        campaign_total_conversion_value: float,
    ):
        self.cell_no = cell_no
        self.cell_description = cell_description
        self.assigned_creative_id = assigned_creative_id
        self.assigned_offer_code = assigned_offer_code
        self.campaign_total_mailed = campaign_total_mailed
        self.campaign_total_responses = campaign_total_responses
        self.campaign_total_conversions = campaign_total_conversions
        self.campaign_total_conversion_value = campaign_total_conversion_value

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            cell_no=data["cell_no"],
            cell_description=data["cell_description"],
            assigned_creative_id=data["assigned_creative_id"],
            assigned_offer_code=data.get("assigned_offer_code"),  # Use .get() for optional fields
            campaign_total_mailed=data["campaign_total_mailed"],
            campaign_total_responses=data["campaign_total_responses"],
            campaign_total_conversions=data["campaign_total_conversions"],
            campaign_total_conversion_value=data["campaign_total_conversion_value"],
        )

    def to_dict(self):
        return {
            "cell_no": self.cell_no,
            "cell_description": self.cell_description,
            "assigned_creative_id": self.assigned_creative_id,
            "assigned_offer_code": self.assigned_offer_code,
            "campaign_total_mailed": self.campaign_total_mailed,
            "campaign_total_responses": self.campaign_total_responses,
            "campaign_total_conversions": self.campaign_total_conversions,
            "campaign_total_conversion_value": self.campaign_total_conversion_value,
        }


class CellMailedThisWeek:
    cell_no: str
    quantity_mailed_this_week: int
    responses_this_week: int
    conversions_this_week: int
    conversion_value_this_week: float

    def __init__(
        self,
        cell_no: str,
        quantity_mailed_this_week: int,
        responses_this_week: int,
        conversions_this_week: int,
        conversion_value_this_week: float,
    ):
        self.cell_no = cell_no
        self.quantity_mailed_this_week = quantity_mailed_this_week
        self.responses_this_week = responses_this_week
        self.conversions_this_week = conversions_this_week
        self.conversion_value_this_week = conversion_value_this_week

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            cell_no=data["cell_no"],
            quantity_mailed_this_week=data["quantity_mailed_this_week"],
            responses_this_week=data["responses_this_week"],
            conversions_this_week=data["conversions_this_week"],
            conversion_value_this_week=data["conversion_value_this_week"],
        )

    def to_dict(self):
        return {
            "cell_no": self.cell_no,
            "quantity_mailed_this_week": self.quantity_mailed_this_week,
            "responses_this_week": self.responses_this_week,
            "conversions_this_week": self.conversions_this_week,
            "conversion_value_this_week": self.conversion_value_this_week,
        }


class WeeklyMailDrop:
    mail_drop_id: str
    mailing_week_start_date: str
    planned_send_date: str
    actual_send_date: Optional[str]
    total_pieces_sent_this_week: int
    cells_mailed_this_week: List[CellMailedThisWeek]

    def __init__(
        self,
        mail_drop_id: str,
        mailing_week_start_date: str,
        planned_send_date: str,
        actual_send_date: Optional[str],
        total_pieces_sent_this_week: int,
        cells_mailed_this_week: List[CellMailedThisWeek],
    ):
        self.mail_drop_id = mail_drop_id
        self.mailing_week_start_date = mailing_week_start_date
        self.planned_send_date = planned_send_date
        self.actual_send_date = actual_send_date
        self.total_pieces_sent_this_week = total_pieces_sent_this_week
        self.cells_mailed_this_week = cells_mailed_this_week

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            mail_drop_id=data["mail_drop_id"],
            mailing_week_start_date=data["mailing_week_start_date"],
            planned_send_date=data["planned_send_date"],
            actual_send_date=data.get("actual_send_date"),  # Use .get() for optional fields
            total_pieces_sent_this_week=data["total_pieces_sent_this_week"],
            cells_mailed_this_week=[
                CellMailedThisWeek.from_dict(cell)
                for cell in data["cells_mailed_this_week"]
            ],
        )

    def to_dict(self):
        return {
            "mail_drop_id": self.mail_drop_id,
            "mailing_week_start_date": self.mailing_week_start_date,
            "planned_send_date": self.planned_send_date,
            "actual_send_date": self.actual_send_date,
            "total_pieces_sent_this_week": self.total_pieces_sent_this_week,
            "cells_mailed_this_week": [
                cell.to_dict() for cell in self.cells_mailed_this_week
            ],
        }


class PerformanceSummary:
    total_campaign_pieces_sent: int
    total_campaign_responses: int
    overall_response_rate: float
    total_campaign_conversions: int
    overall_conversion_rate: float
    total_campaign_conversion_value: float
    average_conversion_value: float
    campaign_roi: float

    def __init__(
        self,
        total_campaign_pieces_sent: int,
        total_campaign_responses: int,
        overall_response_rate: float,
        total_campaign_conversions: int,
        overall_conversion_rate: float,
        total_campaign_conversion_value: float,
        average_conversion_value: float,
        campaign_roi: float,
    ):
        self.total_campaign_pieces_sent = total_campaign_pieces_sent
        self.total_campaign_responses = total_campaign_responses
        self.overall_response_rate = overall_response_rate
        self.total_campaign_conversions = total_campaign_conversions
        self.overall_conversion_rate = overall_conversion_rate
        self.total_campaign_conversion_value = total_campaign_conversion_value
        self.average_conversion_value = average_conversion_value
        self.campaign_roi = campaign_roi

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            total_campaign_pieces_sent=data["total_campaign_pieces_sent"],
            total_campaign_responses=data["total_campaign_responses"],
            overall_response_rate=data["overall_response_rate"],
            total_campaign_conversions=data["total_campaign_conversions"],
            overall_conversion_rate=data["overall_conversion_rate"],
            total_campaign_conversion_value=data["total_campaign_conversion_value"],
            average_conversion_value=data["average_conversion_value"],
            campaign_roi=data["campaign_roi"],
        )

    def to_dict(self):
        return {
            "total_campaign_pieces_sent": self.total_campaign_pieces_sent,
            "total_campaign_responses": self.total_campaign_responses,
            "overall_response_rate": self.overall_response_rate,
            "total_campaign_conversions": self.total_campaign_conversions,
            "overall_conversion_rate": self.overall_conversion_rate,
            "total_campaign_conversion_value": self.total_campaign_conversion_value,
            "average_conversion_value": self.average_conversion_value,
            "campaign_roi": self.campaign_roi,
        }


class CampaignData:
    campaign_id: str
    campaign_name: str
    description: str
    campaign_goal: str
    target_audience_criteria: str
    overall_start_date: str
    overall_end_date: str
    campaign_status: str
    cost_details: CostDetails
    strategy_cells: List[StrategyCell]
    weekly_mail_drops: List[WeeklyMailDrop]
    performance_summary: PerformanceSummary

    def __init__(
        self,
        campaign_id: str,
        campaign_name: str,
        description: str,
        campaign_goal: str,
        target_audience_criteria: str,
        overall_start_date: str,
        overall_end_date: str,
        campaign_status: str,
        cost_details: CostDetails,
        strategy_cells: List[StrategyCell],
        weekly_mail_drops: List[WeeklyMailDrop],
        performance_summary: PerformanceSummary,
    ):
        self.campaign_id = campaign_id
        self.campaign_name = campaign_name
        self.description = description
        self.campaign_goal = campaign_goal
        self.target_audience_criteria = target_audience_criteria
        self.overall_start_date = overall_start_date
        self.overall_end_date = overall_end_date
        self.campaign_status = campaign_status
        self.cost_details = cost_details
        self.strategy_cells = strategy_cells
        self.weekly_mail_drops = weekly_mail_drops
        self.performance_summary = performance_summary

    @classmethod
    def from_dict(cls, data: dict):
        return cls(
            campaign_id=data["campaign_id"],
            campaign_name=data["campaign_name"],
            description=data["description"],
            campaign_goal=data["campaign_goal"],
            target_audience_criteria=data["target_audience_criteria"],
            overall_start_date=data["overall_start_date"],
            overall_end_date=data["overall_end_date"],
            campaign_status=data["campaign_status"],
            cost_details=CostDetails.from_dict(data["cost_details"]),
            strategy_cells=[
                StrategyCell.from_dict(cell) for cell in data["strategy_cells"]
            ],
            weekly_mail_drops=[
                WeeklyMailDrop.from_dict(drop) for drop in data["weekly_mail_drops"]
            ],
            performance_summary=PerformanceSummary.from_dict(data["performance_summary"]),
        )

    def to_dict(self):
        return {
            "campaign_id": self.campaign_id,
            "campaign_name": self.campaign_name,
            "description": self.description,
            "campaign_goal": self.campaign_goal,
            "target_audience_criteria": self.target_audience_criteria,
            "overall_start_date": self.overall_start_date,
            "overall_end_date": self.overall_end_date,
            "campaign_status": self.campaign_status,
            "cost_details": self.cost_details.to_dict(),
            "strategy_cells": [cell.to_dict() for cell in self.strategy_cells],
            "weekly_mail_drops": [drop.to_dict() for drop in self.weekly_mail_drops],
            "performance_summary": self.performance_summary.to_dict(),
        }


def create_sample_campaign_data():
    # Create a sample campaign
    cost_breakdown = CostBreakdown(
        printing_cost_actual=5000.0,
        postage_cost_actual=7500.0,
        data_cost_actual=2000.0,
        other_costs_actual=1000.0
    )
    
    cost_details = CostDetails(
        overall_budget=20000.0,
        total_campaign_cost_planned=18000.0,
        total_campaign_cost_actual=15500.0,
        cost_per_piece_planned=0.90,
        cost_per_piece_actual=0.775,
        cost_breakdown=cost_breakdown
    )
    
    strategy_cells = [
        StrategyCell(
            cell_no="A1",
            cell_description="High Value Customers",
            assigned_creative_id="CR001",
            assigned_offer_code="OFF100",
            campaign_total_mailed=10000,
            campaign_total_responses=500,
            campaign_total_conversions=100,
            campaign_total_conversion_value=50000.0
        )
    ]
    
    cells_mailed_this_week = [
        CellMailedThisWeek(
            cell_no="A1",
            quantity_mailed_this_week=2500,
            responses_this_week=125,
            conversions_this_week=25,
            conversion_value_this_week=12500.0
        )
    ]
    
    weekly_mail_drops = [
        WeeklyMailDrop(
            mail_drop_id="MD001",
            mailing_week_start_date="2024-03-01",
            planned_send_date="2024-03-04",
            actual_send_date="2024-03-04",
            total_pieces_sent_this_week=2500,
            cells_mailed_this_week=cells_mailed_this_week
        )
    ]
    
    performance_summary = PerformanceSummary(
        total_campaign_pieces_sent=10000,
        total_campaign_responses=500,
        overall_response_rate=5.0,
        total_campaign_conversions=100,
        overall_conversion_rate=20.0,
        total_campaign_conversion_value=50000.0,
        average_conversion_value=500.0,
        campaign_roi=222.58
    )
    
    campaign = CampaignData(
        campaign_id="CAM001",
        campaign_name="Spring 2024 Promotion",
        description="Spring season promotional campaign targeting high-value customers",
        campaign_goal="Increase customer engagement and sales during spring season",
        target_audience_criteria="Customers with average spend > $1000 in last 6 months",
        overall_start_date="2024-03-01",
        overall_end_date="2024-05-31",
        campaign_status="Active",
        cost_details=cost_details,
        strategy_cells=strategy_cells,
        weekly_mail_drops=weekly_mail_drops,
        performance_summary=performance_summary
    )
    
    return campaign

def main():
    # Load environment variables
    load_dotenv()
    
    # MongoDB connection settings
    MONGO_URI = os.getenv("MONGO_URI", "mongodb://localhost:27017/")
    MONGO_DB_NAME = os.getenv("MONGO_DB_NAME", "marketing_db")
    MONGO_COLLECTION_NAME = os.getenv("MONGO_COLLECTION_NAME", "campaign_data")
    
    try:
        # Create MongoDB client with SSL configuration
        client = MongoClient(MONGO_URI, 
                           tlsAllowInvalidCertificates=True,
                           serverSelectionTimeoutMS=5000)
        
        # Get database and collection
        db = client[MONGO_DB_NAME]
        collection = db[MONGO_COLLECTION_NAME]
        
        # Create sample campaign data
        campaign = create_sample_campaign_data()
        
        # Convert to dictionary and insert
        campaign_dict = campaign.to_dict()
        result = collection.insert_one(campaign_dict)
        
        print(f"Successfully inserted campaign with ID: {result.inserted_id}")
        
        # Optional: Verify the insertion by retrieving the document
        inserted_doc = collection.find_one({"campaign_id": "CAM001"})
        if inserted_doc:
            print("\nVerified inserted document:")
            pprint(inserted_doc)
        
    except Exception as e:
        print(f"An error occurred: {e}")
    finally:
        client.close()

if __name__ == "__main__":
    main()