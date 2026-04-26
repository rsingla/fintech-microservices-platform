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