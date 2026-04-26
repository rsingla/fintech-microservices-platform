from .cost_breakdown import CostBreakdown

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