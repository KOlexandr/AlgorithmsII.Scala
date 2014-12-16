package lab12

class Point(val x: Int, val y: Int)
class Line(val p1: Point, val p2: Point)

object P {
  def apply(x: Int, y: Int) = new Point(x, y)
  def apply(p: (Int, Int)) = new Point(p._1, p._2)
}

object L {
  def apply(p1: Point, p2: Point) = new Line(p1, p2)
  def apply(p1: (Int, Int), p2: (Int, Int)) = new Line(P(p1), P(p2))
  def apply(x1: Int, y1: Int, x2: Int, y2: Int) = new Line(P(x1, y1), P(x2, y2))
}