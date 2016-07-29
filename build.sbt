
name := "Cassandra-Spark"
version := "1.0"
scalaVersion := "2.11.7"
scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Ywarn-dead-code",
  "-encoding", "UTF-8",
  "-target:jvm-1.8",
  "-feature",
  "-language:postfixOps")


val sparkV = "1.6.1"
val sparkConnectorV = "1.6.0"

lazy val sparkDependencies = Seq(
  "org.apache.spark" %% "spark-core" % sparkV,
  "org.apache.spark" %% "spark-sql" % sparkV,
  "org.apache.spark" %% "spark-streaming" % sparkV
)

lazy val dependencies = Seq(
  "com.datastax.spark" %% "spark-cassandra-connector" % sparkConnectorV
)
lazy val root = (project in file("."))
  .settings(SparkSubmit.settings: _*)

libraryDependencies ++= dependencies ++ sparkDependencies.map(_ % "provided")

// Uncomment this to have something that IDEA can run. Use classpath of module Spark in the IDEA runner
// Note that this breaks the ability to create assemblies.
//lazy val spark = project.in(file("spark")).dependsOn(RootProject(file("."))).settings(
// libraryDependencies ++= sparkDependencies.map(_ % "compile")
//)

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", "io.netty.versions.properties", xs@_*) => MergeStrategy.last
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
