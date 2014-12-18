package lab14

class Point(val x: Int, val y: Int) {
  override def toString: String = s"($x, $y)"

  def distance(p: Point): Double = math.sqrt((x - p.x)*(x - p.x) + (y - p.y)*(y - p.y))
}

object P {
  def apply(x: Int, y: Int) = new Point(x, y)
  def apply(p: (Int, Int)) = new Point(p._1, p._2)
}