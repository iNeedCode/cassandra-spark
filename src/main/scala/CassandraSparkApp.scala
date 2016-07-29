import com.datastax.spark.connector._
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
  val rdd = cc.cassandraTable("spark_musicdb", "tracks_by_album")


  // Print all entries that have even IDs
//    rdd.filter(_.getInt("number") > 2).foreach(println)
  println(rdd.count())

  println(rdd.map(_.getInt("number")).sum)

  val collection = cc.parallelize(Seq((3, "key3"), (4, "key4")))
  collection.saveToCassandra("spark_output", "key_value", SomeColumns("id", "name"))

  cc.stop
}
