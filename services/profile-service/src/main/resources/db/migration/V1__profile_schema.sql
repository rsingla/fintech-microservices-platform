create table customer_profiles (
  id varchar(64) primary key,
  first_name varchar(120) not null,
  last_name varchar(120) not null,
  date_of_birth date,
  email varchar(255) not null unique,
  phone_number varchar(40),
  address_line1 varchar(255),
  address_line2 varchar(255),
  city varchar(120),
  state varchar(120),
  postal_code varchar(20),
  country varchar(120),
  created_at timestamp with time zone not null,
  updated_at timestamp with time zone not null
);
