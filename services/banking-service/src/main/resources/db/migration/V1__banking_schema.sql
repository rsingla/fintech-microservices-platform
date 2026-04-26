create table bank_accounts (
  id varchar(64) primary key,
  customer_id varchar(64) not null,
  account_number varchar(32) not null unique,
  type varchar(32) not null,
  balance numeric(19, 2) not null,
  currency varchar(3) not null,
  status varchar(32) not null,
  created_at timestamp with time zone not null,
  updated_at timestamp with time zone not null
);
