## Casssandra setup

Install Cassandra as per the [DataStax guide](http://docs.datastax.com/en/landing_page/doc/landing_page/current.html)
and ensure that you have `cqlsh` access to the database. Set up the test keyspace and table using:

```bash
    cqlsh < data/userdb/schema.cql
    cqlsh < data/musicdb/schema.cql
    cqlsh < data/musicdb/data.cql
    cqlsh < data/spark_output/schema.cql
```




## Running the Applications
##### Building and running
You can run this from [sbt-spark-submit](https://github.com/saurfang/sbt-spark-submit) using

```bash
    sbt run
```

##### CassandraSparkApp
A simple app which shows how to use C* as a datasource in a Spark job. Basically it reads data from C* and writes it back with a simple limiting of data to the keyspace `spark_output`.

##### SparkStreamingApp
Run the spark streaming application in the `sbt run`. This Example counts words from a book. The book was downloaded from the project [gutenberg](http://www.gutenberg.org/wiki) where you can download whole books as textfiles. In order to stream data to the TextStream from Spark you have to push the file over the command `nc`.  Use the following command for more fun

```bash
    while :; do cat data/book/2600-0.txt | nc -l 9999 ; sleep 1; done;
```

It loads the book every second and it will be processed by spark. 

##### CassandraStreamingApp
This Example shows the integration of Spark Streaming and Cassandra where the input of the input from `nc` is used to load the proper data from the cassandra table `albums_by_genre`. The key word to which spark is join is the release `year` of the album. That means you can input the year 1999 in the netcat.
 
```bash
    nc -lk 9999
```