CREATE DATABASE smartplan
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

COMMENT ON DATABASE smartplan
    IS 'Projects for teams';

CREATE SCHEMA plans
    AUTHORIZATION postgres;