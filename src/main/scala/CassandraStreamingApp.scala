import com.datastax.spark.connector._
import com.datastax.spark.connector.rdd.CassandraTableScanRDD
import org.apache.log4j.PropertyConfigurator
import org.apache.spark.rdd.RDD
import org.apache.spark.{HashPartitioner, SparkConf}
import org.apache.spark.streaming._


object CassandraStreamingApp extends App {
  PropertyConfigurator.configure("conf/log4j.properties")

  val conf = new SparkConf(true)
    .setAppName("Cassandra Streaming")
    .setMaster("local[2]")
    .set("spark.cassandra.connection.host", "127.0.0.1")
    .set("spark.cleaner.ttl", "3600")

  val ssc = new StreamingContext(conf, Seconds(4))
  val stream = ssc.socketTextStream("127.0.0.1", 9999).countByValue()


  val albums_by_genre = ssc.sparkContext
    .cassandraTable("spark_musicdb", "albums_by_genre")
    .select("genre", "performer", "year", "title")
    .as((genre: String, performer: String, year: Int, title: String) => (year.toString, (title, genre, performer)))
    .partitionBy(new HashPartitioner(2 * ssc.sparkContext.defaultParallelism))
    .cache

  // stream.print() // output input of the stream

  stream.transform(rdd => rdd.join(albums_by_genre)
    .map {
      case (year, (yearOccurenceInStream, (title, genre, performer))) => (year, title, genre, performer)
    })
    .print()


  ssc.start()
  ssc.awaitTermination()
}
