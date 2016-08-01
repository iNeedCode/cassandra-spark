CREATE KEYSPACE IF NOT EXISTS spark_output WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};

CREATE TABLE IF NOT EXISTS spark_output.key_value (
    id int PRIMARY KEY,
    name text
);