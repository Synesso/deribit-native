scalaVersion := "2.11.12"

// Set to false or remove if you want to show stubs as linking errors
nativeLinkStubs := true

//libraryDependencies += "org.json4s" %%% "json4s-native" % "3.6.9"
libraryDependencies ++= List(
  "com.lihaoyi" %%% "upickle" % "1.2.0",
  "com.lihaoyi" %%% "ujson" % "1.2.0",
  "com.softwaremill.sttp.client" %%% "core" % "2.2.6",
  "com.lihaoyi" %%% "utest" % "0.7.4" % "test"
)

testFrameworks += new TestFramework("utest.runner.Framework")

enablePlugins(ScalaNativePlugin)
