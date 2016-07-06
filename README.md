## Casssandra setup

Install Cassandra as per the [DataStax guide](http://docs.datastax.com/en/landing_page/doc/landing_page/current.html)
and ensure that you have `cqlsh` access to the database. Set up the test keyspace and table using:

```bash
    cqlsh < data/userdb/userdb.cql
    cqlsh < data/musicdb/musicdb.cql
    cqlsh < data/musicdb/musicdata.cql
    cqlsh < data/cql/spark_output.cql
```

That is it!


## Building and running

You can run this from [sbt-spark-submit](https://github.com/saurfang/sbt-spark-submit) using
```bash
    sbt "sparkSubmit --class CassandraSparkApp"
```