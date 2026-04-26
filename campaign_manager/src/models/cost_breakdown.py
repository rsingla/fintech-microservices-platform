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