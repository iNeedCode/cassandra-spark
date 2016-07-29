## Casssandra setup

Install Cassandra as per the [DataStax guide](http://docs.datastax.com/en/landing_page/doc/landing_page/current.html)
and ensure that you have `cqlsh` access to the database. Set up the test keyspace and table using:

```bash
    cqlsh < data/userdb/schema.cql
    cqlsh < data/musicdb/schema.cql
    cqlsh < data/musicdb/data.cql
    cqlsh < data/cql/spark_output.cql
```



## Building and running

You can run this from [sbt-spark-submit](https://github.com/saurfang/sbt-spark-submit) using
```bash
    sbt "sparkSubmit --class CassandraSparkApp"
    sbt run
```

## pushing textfile to nc
```bash
    while :; do cat 2600-0.txt | nc -l 9999 ; sleep 1; done;
```