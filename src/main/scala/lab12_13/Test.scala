package lab12_13

import scala.collection.immutable.Stack

object Test {

  def main(args: Array[String]) {
//    segmentIntersect()
//    anySegmentsIntersect()
    grahamScan()
  }

  def segmentIntersect(): Unit = {
    val line1 = L(0, 5, 4, 4) // Figure 33.3 a) b)
    //    val line1 = Line(0, 5, 14, 3)
    val line2 = L(1, 1, 15, 8)
    if (SegmentIntersect.isIntersect(line1, line2)) {
      println("Yes")
    } else {
      println("No")
    }
  }

  def anySegmentsIntersect(): Unit = {
    val lines = Array(    // Figure 33.5
      L(5, 25, 25, 31),   //a
      L(10, 3, 80, 30),   //b
      L(15, 12, 35, 18),  //c
      L(20, 25, 77, 15),  //d
      L(30, 27, 75, 21),  //e
      L(40, 12, 65, 3)    //f
    )
    if (SegmentIntersect.anySegmentsIntersect(lines)) {
      println("Yes")
    } else {
      println("No")
    }
  }

  def grahamScan(): Unit = {
    val points: Array[Point] = Array( // input set Q of point
      P(0, 4),                        //  |
      P(4, 4),                        //  0               1
      P(2, 3),                        //  |
      P(1, 2),                        //  +       2
      P(3, 2),                        //  |
      P(2, 1),                        //  +   3       4
      P(0, 0),                        //  |
      P(4, 0)                         //  +       5
    )                                 //  |
                                      //  6---+---+---+---7---+----

    println("Graham Scan:")
    println(ConvexHull.grahamScan(points).mkString(" -> "))

    println("Jarvis March:")
    println(ConvexHull.jarvisMarch(points).mkString(" -> "))
  }
}