import org.apache.log4j.PropertyConfigurator
import org.apache.spark.SparkConf
import org.apache.spark.streaming._


object SparkStreamingApp extends App {
  PropertyConfigurator.configure("conf/log4j.properties")

  val conf = new SparkConf(true)
    .setAppName("Streaming Example")
    .setMaster("local[2]") // use 2 cores in streaming apps
    .set("spark.cassandra.connection.host", "127.0.0.1")
    .set("spark.cleaner.ttl", "3600")

  val ssc = new StreamingContext(conf, Seconds(4))

  val stream = ssc.socketTextStream("127.0.0.1", 9999)

  stream.flatMap(record => record.split(" "))
    .map(word => (word, 1))
    .reduceByKey(_ + _)
    .filter(!_._1.isEmpty)
    .map(item => item.swap)
    .transform(rdd => rdd.sortByKey(ascending = false))
    .print(25)

  ssc.start()
  ssc.awaitTermination()
}
