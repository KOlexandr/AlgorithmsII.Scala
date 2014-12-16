package lab12

object Test {

  def main(args: Array[String]) {
//    segmentIntersect()
    anySegmentsIntersect()
  }

  def segmentIntersect(): Unit = {
    val line1 = L(0, 5, 4, 4)
    //    val line1 = Line(0, 5, 14, 3)
    val line2 = L(1, 1, 15, 8)
    if (SegmentIntersect.isIntersect(line1, line2)) {
      println("Yes")
    } else {
      println("No")
    }
  }

  def anySegmentsIntersect(): Unit = {
    val lines = Array(
      L(5, 25, 25, 31),
      L(10, 3, 80, 30),
      L(15, 12, 35, 18),
      L(20, 25, 77, 15),
      L(30, 27, 75, 21),
      L(40, 12, 65, 3)
    )
    if (SegmentIntersect.anySegmentsIntersect(lines)) {
      println("Yes")
    } else {
      println("No")
    }
  }
}