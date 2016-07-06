import sbtsparksubmit.SparkSubmitPlugin.autoImport._

object SparkSubmit {
  lazy val settings =
    SparkSubmitSetting("CassandraSparkApp", Seq("--class", "CassandraSparkApp", "--master", "local[0]"))
}
