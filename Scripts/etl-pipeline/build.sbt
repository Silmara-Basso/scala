ThisBuild / scalaVersion := "3.4.2"

lazy val root = (project in file("."))
  .settings(
    name := "Pipeline ETL",
    version := "1.0",
    libraryDependencies ++= Seq(
      "com.github.tototoshi" %% "scala-csv" % "1.3.10",
      "org.xerial" % "sqlite-jdbc" % "3.36.0.3"
    )
  )
