create table products (
  sku varchar(64) primary key,
  name varchar(255) not null,
  price numeric(19, 2) not null,
  currency varchar(3) not null,
  updated_at timestamp with time zone not null
);
