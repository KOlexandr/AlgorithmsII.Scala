package semester1st.lab14

object Test {

  def main(args: Array[String]) {
    val points: Array[Point] = Array( // input set Q of point
      P(0, 4),                        //  |
      P(4, 4),                        //  0               1
      P(2, 3),                        //  |
      P(1, 2),                        //  +       2
      P(3, 2),                        //  |
      P(2, 1),                        //  +   3       4
      P(0, 0),                        //  |
      P(1, 0)                         //  +       5
    )                                 //  |
                                      //  6---+---+---+---7---+----

    println("Brute-Force Search:")
    val bf: (Point, Point) = NearestPoints.bruteForce(points)
    println(bf + " -> " + bf._1.distance(bf._2))

    println("Decomposition Search:")
    val d: (Point, Point) = NearestPoints.decomposition(points)
    println(d + " -> " + d._1.distance(d._2))
  }
}