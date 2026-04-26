from typing import List, Optional
from .cell_mailed_this_week import CellMailedThisWeek

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
            actual_send_date=data.get("actual_send_date"),
            total_pieces_sent_this_week=data["total_pieces_sent_this_week"],
            cells_mailed_this_week=[
                CellMailedThisWeek.from_dict(cell) for cell in data["cells_mailed_this_week"]
            ],
        )

    def to_dict(self):
        return {
            "mail_drop_id": self.mail_drop_id,
            "mailing_week_start_date": self.mailing_week_start_date,
            "planned_send_date": self.planned_send_date,
            "actual_send_date": self.actual_send_date,
            "total_pieces_sent_this_week": self.total_pieces_sent_this_week,
            "cells_mailed_this_week": [cell.to_dict() for cell in self.cells_mailed_this_week],
        }