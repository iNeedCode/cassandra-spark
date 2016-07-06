
CREATE KEYSPACE IF NOT EXISTS spark_userdb
    WITH replication = {'class': 'SimpleStrategy', 'replication_factor' : 1};

USE spark_userdb;

CREATE TABLE IF NOT EXISTS users_by_email (
  name VARCHAR,
  dob TIMESTAMP,
  email VARCHAR,
  join_date TIMESTAMP,
  PRIMARY KEY (email)
);

CREATE TABLE IF NOT EXISTS users_by_name_and_dob (
  name VARCHAR,
  dob TIMESTAMP,
  email VARCHAR,
  join_date TIMESTAMP,
  PRIMARY KEY ((name,dob))
);


CREATE TABLE IF NOT EXISTS users_by_join_date (
  name VARCHAR,
  dob TIMESTAMP,
  email VARCHAR,
  join_date TIMESTAMP,
  PRIMARY KEY (join_date, email)
);

INSERT INTO users_by_email (name, dob, email, join_date) VALUES ('John', '1986-12-01', 'john@data.org', '2014-03-08');
INSERT INTO users_by_email (name, dob, email, join_date) VALUES ('Mary', '1986-12-01', 'mary@data.org', '2014-03-08');
INSERT INTO users_by_email (name, dob, email, join_date) VALUES ('John', '1979-02-18', 'john@data.edu', '2013-01-01');

INSERT INTO users_by_name_and_dob (name, dob, email, join_date) VALUES ('John', '1986-12-01', 'john@data.org', '2014-03-08');
INSERT INTO users_by_name_and_dob (name, dob, email, join_date) VALUES ('Mary', '1986-12-01', 'mary@data.org', '2014-03-08');
INSERT INTO users_by_name_and_dob (name, dob, email, join_date) VALUES ('John', '1979-02-18', 'john@data.edu', '2013-01-01');

INSERT INTO users_by_join_date (name, dob, email, join_date) VALUES ('John', '1986-12-01', 'john@data.org', '2014-03-08');
INSERT INTO users_by_join_date (name, dob, email, join_date) VALUES ('Mary', '1986-12-01', 'mary@data.org', '2014-03-08');
INSERT INTO users_by_join_date (name, dob, email, join_date) VALUES ('John', '1979-02-18', 'john@data.edu', '2013-01-01');

