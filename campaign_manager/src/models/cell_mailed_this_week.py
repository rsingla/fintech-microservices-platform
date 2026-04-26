from typing import List, Optional

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