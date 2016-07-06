import sbtsparksubmit.SparkSubmitPlugin.autoImport._

object SparkSubmit {
  lazy val settings =
    SparkSubmitSetting("sparkSimpleApp", Seq("--class", "SimpleApp", "--master", "local[4]"))
}
