from typing import List
from .cost_details import CostDetails
from .strategy_cell import StrategyCell
from .weekly_mail_drop import WeeklyMailDrop
from .performance_summary import PerformanceSummary

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