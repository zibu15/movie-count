# package
sbt package

# submit - need to be in SPARK_HOME directory 

export SPARK_LOCAL_IP=127.0.0.1

$SPARK_HOME/bin/spark-submit --class "MovieCountApp" --master local ../target/scala-2.10/movie-count-project_2.10-1.0.jar <path to movie data directory>


