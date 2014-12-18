package lab12_13

import scala.collection.immutable.Stack

/**
* Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
* Chapter 33.  Computational Geometry
*   33.3 Build convex hull
*     Graham's scan (anyone of some three points not on the same line)
*     Jarvis's march
*/
object ConvexHull {

  /** Graham's Scan **/
  def grahamScan(points: Array[Point]): List[Point] = {
    val p0: Point = searchP0(points)
    val sorted: Array[Point] = points.filter(_ != p0).sortWith((p1, p2) => sort(p1, p2, p0))

    def scan(stack: Stack[Point], points: Array[Point]): Stack[Point] = {
      if(points.isEmpty) stack
      else if(isNonLeftTurn(stack.tail.head, stack.head, points.head)) scan(stack.tail, points)
      else scan(stack.push(points.head), points.tail)
    }
    (p0 :: scan(Stack(sorted(1), sorted(0), p0), sorted.slice(2, points.size)).toList).reverse
  }

  /** Jarvis's March **/
  def jarvisMarch(points: Array[Point]): List[Point] = {
    val p0: Point = searchP0(points)
    val pn: Point = biggestPolarAngle(p0, points)
    def march(p: Point, rest: Array[Point], path: List[Point]): List[Point] = {
      if(rest.isEmpty || pn == p) {
        path
      } else {
        val angle: Point = smallestPolarAngle(p, rest)
        march(angle, rest.filter(_ != angle), angle :: path)
      }
    }
    (p0 :: march(p0, points.filter(_ != p0), List(p0))).reverse
  }

  /** search smallest polar angle with respect to parameter p0 **/
  private def smallestPolarAngle(p0: Point, points : Array[Point]): Point = {
    def find(min: Point, points: Array[Point]): Point = {
      if(points.isEmpty) min
      else if(sort(p0, points.head, min)) find(points.head, points.tail)
      else find(min, points.tail)
    }
    find(points.head, points.tail)
  }

  /** search biggest polar angle with respect to parameter p0 **/
  private def biggestPolarAngle(p0: Point, points : Array[Point]): Point = {
    def find(max: Point, points: Array[Point]): Point = {
      if(points.isEmpty) max
      else if(!sort(p0, points.head, max)) find(points.head, points.tail)
      else find(max, points.tail)
    }
    find(points.head, points.tail)
  }

  /** polar angle p1 <= polar angle p2 in counterclockwise order around p0 ?
  * The cross product using to determine how consecutive line segments
  * p0p1 and p1p2 turn at point p1.
  * If directed segment p0p1 is clockwise relative to the directed segment p0p2,
  * the points make a left turn and polar angle p1 <= polar angle p2
  */
  private def sort(p1: Point, p2: Point, p0: Point): Boolean = SegmentIntersect.direction(p0, p1, p2) >= 0

  private def isNonLeftTurn(nextToTop: Point, top: Point, pi: Point): Boolean = SegmentIntersect.direction(nextToTop, top, pi) < 0

  /**
   * p0 is the point in Q with the minimum y-coordinate
   * or the leftmost such point in case of a tie
   */
  private def searchP0(points: Array[Point]): Point = points.filter(p => p.y == points.minBy(_.y).y).sortBy(_.x).toList(0)
}
