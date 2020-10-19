--This is just the script I used to initially create the database.

DROP TABLE IF EXISTS GROUP_MEMBER;

CREATE TABLE GROUP_MEMBER (
      email VARCHAR PRIMARY KEY,
      first VARCHAR,
      last VARCHAR,
      avatar VARCHAR,
      lang VARCHAR
);