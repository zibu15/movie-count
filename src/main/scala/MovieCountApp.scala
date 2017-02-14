import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

/**
 *  Simple Spark application to count how many movies per year
 *
 *  To run this app
 *   sbt package
 *   ./bin/spark-submit --class "MovieCountApp" --master local ../applications/movie-count/target/scala-2.10/movie-count-project_2.10-1.0.jar ../data/movie-data/movies
 */
object MovieCountApp {
  def main(args: Array[String]) {

  	val movieFile = args(0) // Should be some file on your system
  	println("movieFile: " + movieFile)
    //val movieFile = "../data/movie-data/movies" // Should be some file on your system
    val conf = new SparkConf().setAppName("Movie Count Application")
    val sc = new SparkContext(conf)

    val movies = sc.textFile(movieFile, 1)
    val valid_movies = movies.map(_.split("\t")).filter(_.length == 3)
    val movie_year = valid_movies.map(m => (m(2).toInt, 1))

    val movie_year_count = movie_year.reduceByKey(_ + _)

    val movie_titles = valid_movies.map(m => (m(2).toInt, m(1)))

    val movies_per_year = movie_titles.distinct.countByKey

    var sortedByYear =  scala.collection.immutable.TreeMap[Int,Long]()
    
    movies_per_year foreach (x => {sortedByYear += (x._1 -> x._2) })

    sortedByYear.foreach(println)

    //println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
  }
}
