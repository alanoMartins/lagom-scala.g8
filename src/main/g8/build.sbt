organization in ThisBuild := "$organization$"
version in ThisBuild := "$version$"



// the Scala version that will be used for cross-compiled libraries
scalaVersion in ThisBuild := "2.13.0"

val dottComponentOrganization = "dott.roast.component"
val dottServiceVersion = "$roastVersion"

val macwire = "com.softwaremill.macwire" %% "macros" % "2.3.3" % "provided"
val scalaTest = "org.scalatest" %% "scalatest" % "3.1.1" % Test

lazy val `$name;format="norm"$` = (project in file("."))
  .aggregate(`$name;format="norm"$-api`, `$name;format="norm"$-impl`, `$name;format="normalize"$-stream-api`, `$name;format="normalize"$-stream-impl`)

lazy val `$name;format="norm"$-api` = (project in file("api"))
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslApi
    )
  )

lazy val `$name;format="norm"$-impl` = (project in file("impl"))
  .enablePlugins(LagomScala)
  .settings(
    libraryDependencies ++= Seq(
      lagomScaladslPersistenceCassandra,
      lagomScaladslKafkaBroker,
      lagomScaladslTestKit,
      macwire,
      scalaTest
    )
  )
  .settings(lagomForkedTestSettings)
  .dependsOn(`$name;format="norm"$-api`)


