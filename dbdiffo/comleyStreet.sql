CREATE DATABASE IF NOT EXISTS comelyStreet;

USE comelyStreet;

CREATE TABLE booking_detail
(
  id bigint NOT NULL AUTO_INCREMENT,
  client_id bigint NOT NULL,
  customer_name char(255) NOT NULL,
  customer_mobile_number char(255) NOT NULL,
  customer_email_id char(255),
  booked_services blob NOT NULL,
  proposed_start_time datetime NOT NULL,
  proposed_end_time datetime NOT NULL,
  actual_start_time datetime,
  actual_end_time datetime,
  customer_in_time datetime,
  customer_out_time datetime,
  status char(255) NOT NULL,
  num_of_person integer NOT NULL DEFAULT 1,
  rating integer,
  CONSTRAINT pk_booking_detail PRIMARY KEY (id)
);

CREATE TABLE client_daily_scheduling_detail
(
  id bigint NOT NULL AUTO_INCREMENT,
  client_id bigint NOT NULL,
  scheduled_date date NOT NULL,
  scheduled_blob blob NOT NULL,
  CONSTRAINT pk_client_daily_scheduling_detail PRIMARY KEY (id)
);

CREATE TABLE client_detail
(
  id bigint NOT NULL AUTO_INCREMENT,
  email_id char(255) NOT NULL,
  password_hash text NOT NULL,
  name text NOT NULL,
  city char(255) NOT NULL,
  area_name text NOT NULL,
  address text NOT NULL,
  services blob NOT NULL,
  operation_time blob NOT NULL,
  minimum_service_time integer NOT NULL DEFAULT 15,
  num_employees integer NOT NULL,
  allowed_advance_booking_days integer NOT NULL DEFAULT 7,
  metadata blob,
  forgot_password_key text,
  CONSTRAINT pk_client_detail PRIMARY KEY (id)
);

CREATE TABLE client_scheduling_override_detail
(
  id bigint NOT NULL,
  client_id bigint NOT NULL,
  override_date date NOT NULL,
  num_of_employees integer,
  operation_time blob,
  client_services blob,
  CONSTRAINT pk_client_scheduling_override_detail PRIMARY KEY (id)
);

ALTER TABLE booking_detail ADD CONSTRAINT fk_booking_detail_client_id
  FOREIGN KEY (client_id) REFERENCES client_detail (id);

ALTER TABLE client_daily_scheduling_detail ADD CONSTRAINT fk_client_daily_scheduling_detail_client_id
  FOREIGN KEY (client_id) REFERENCES client_detail (id);

ALTER TABLE client_scheduling_override_detail ADD CONSTRAINT fk_client_scheduling_override_detail_client_id
  FOREIGN KEY (client_id) REFERENCES client_detail (id);
