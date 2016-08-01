import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.log4j.PropertyConfigurator
import org.apache.spark.{SparkConf, SparkContext}

object CassandraSparkApp extends App {

  // adjust the system log with ON or OFF
  PropertyConfigurator.configure("conf/log4j.properties")

  // Configure the Cassandra host and create a new Spark context
  val conf = new SparkConf(true)
    .set("spark.cassandra.connection.host", "127.0.0.1")
    // These settings are needed if you run this from the IDE or sbt. They get set by "spark-submit"
    .setMaster("local[1]")
    .setAppName("CassandraSpark")

  val cc = new SparkContext(conf)

  // Connect to the database and do some operations on the data
  val rdd: CassandraTableScanRDD[CassandraRow] = cc.cassandraTable("spark_musicdb", "tracks_by_album")

  // Print all entries that have even IDs
  // rdd.filter(_.getInt("number") > 2).foreach(println)
  println("\nCOUNT OF tracks_by_album: " + rdd.count())
  println(s"SUM OF COLUMN number: ${rdd.map(_.getInt("number")).sum} \n")

  val backToCassandra = rdd.limit(10)
    .map {
      cRow => (cRow.getInt("number"), cRow.getString("album_title"))
    }
    .saveToCassandra("spark_output", "key_value", SomeColumns("id", "name"))

  val collection = cc.parallelize(Seq((9999, "COLLECTION KEY 9999"), (9998, "COLLECTION KEY 9998")))
  collection.saveToCassandra("spark_output", "key_value", SomeColumns("id", "name"))

  cc.stop
}
