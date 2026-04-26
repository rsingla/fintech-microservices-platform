from typing import Optional


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
            assigned_offer_code=data.get("assigned_offer_code"),
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