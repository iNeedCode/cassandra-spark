CREATE KEYSPACE test_spark WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'};


CREATE TABLE test_spark.test (
    id      INT,
    name    TEXT,
    PRIMARY KEY (id)
);

INSERT INTO test_Spark.test(id, name) VALUES (1, 'Spark');
INSERT INTO test_Spark.test(id, name) VALUES (2, 'Cassandra');
INSERT INTO test_Spark.test(id, name) VALUES (3, 'Scala');
INSERT INTO test_Spark.test(id, name) VALUES (4, 'SBT');
INSERT INTO test_Spark.test(id, name) VALUES (5, 'CQL');
