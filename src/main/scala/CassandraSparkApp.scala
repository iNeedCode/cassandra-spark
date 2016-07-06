import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import com.datastax.spark.connector._

object CassandraSparkApp extends App {
  // Configure the Cassandra host and create a new Spark context
  val conf = new SparkConf(true)
    .set("spark.cassandra.connection.host", "127.0.0.1")
    // These settings are needed if you run this from the IDE or sbt. They get set by "spark-submit"
    .setMaster("local[1]")
    .setAppName("CassandraSpark")

  val cc = new SparkContext(conf)

  // Connect to the database and do some operations on the data
  val rdd = cc.cassandraTable("test_spark", "test")

  // Print all entries in the database
  rdd.foreach(println)

  // Print all entries that have even IDs
  rdd.filter(_.getInt("id") % 2 == 0).foreach(println)
  cc.stop
}
