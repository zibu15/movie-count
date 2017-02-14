name := "Movie Count Project"

version := "1.0"

scalaVersion := "2.10.4"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.1.0" % "provided"

assemblyJarName in assembly := "movie-count-assembly.jar"

