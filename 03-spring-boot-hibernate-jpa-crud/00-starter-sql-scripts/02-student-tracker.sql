CREATE DATABASE student_tracker;

CREATE TABLE student (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(45),
  last_name VARCHAR(45),
  email VARCHAR(45)
);