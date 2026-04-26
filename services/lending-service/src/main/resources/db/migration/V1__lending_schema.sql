create table loan_applications (
  id varchar(64) primary key,
  customer_id varchar(64) not null,
  requested_amount numeric(19, 2) not null,
  annual_income numeric(19, 2) not null,
  monthly_debt numeric(19, 2) not null,
  credit_score integer not null,
  decision varchar(32) not null,
  reason_code varchar(64) not null,
  explanation text not null,
  submitted_at timestamp with time zone not null
);
