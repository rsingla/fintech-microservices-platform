from pydantic import BaseModel
from typing import List, Optional
from datetime import datetime

class CostBreakdown(BaseModel):
    printing_cost_actual: float
    postage_cost_actual: float
    data_cost_actual: float
    other_costs_actual: float

class CostDetails(BaseModel):
    overall_budget: float
    total_campaign_cost_planned: float
    total_campaign_cost_actual: float
    cost_per_piece_planned: float
    cost_per_piece_actual: float
    cost_breakdown: CostBreakdown

class StrategyCell(BaseModel):
    cell_no: str
    cell_description: str
    assigned_creative_id: str
    assigned_offer_code: Optional[str]
    campaign_total_mailed: int
    campaign_total_responses: int
    campaign_total_conversions: int
    campaign_total_conversion_value: float

class CellMailedThisWeek(BaseModel):
    cell_no: str
    quantity_mailed_this_week: int
    responses_this_week: int
    conversions_this_week: int
    conversion_value_this_week: float

class WeeklyMailDrop(BaseModel):
    mail_drop_id: str
    mailing_week_start_date: str
    planned_send_date: str
    actual_send_date: Optional[str]
    total_pieces_sent_this_week: int
    cells_mailed_this_week: List[CellMailedThisWeek]

class PerformanceSummary(BaseModel):
    total_campaign_pieces_sent: int
    total_campaign_responses: int
    overall_response_rate: float
    total_campaign_conversions: int
    overall_conversion_rate: float
    total_campaign_conversion_value: float
    average_conversion_value: float
    campaign_roi: float

class CampaignData(BaseModel):
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