DROP ROLE IF EXISTS springstudent;

CREATE ROLE springstudent WITH LOGIN PASSWORD 'springstudent';

ALTER ROLE springstudent CREATEDB;

GRANT ALL PRIVILEGES ON DATABASE postgres TO springstudent;