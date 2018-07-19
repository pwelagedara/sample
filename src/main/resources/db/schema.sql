CREATE TABLE t_users (
  id         INTEGER PRIMARY KEY,
  name  VARCHAR(30)  NOT NULL UNIQUE
);

CREATE TABLE users (
  username varchar(256),
  password varchar(256),
  enabled boolean
);

CREATE TABLE authorities (
  username varchar(256),
  authority varchar(256)
);