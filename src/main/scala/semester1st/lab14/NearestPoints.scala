package semester1st.lab14

/**
* Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
* Chapter 33.  Computational Geometry
*   33.4 Closest-pair of points problem
*     Brute-Force
*     Decomposition Algorithm
*/
object NearestPoints {

  def bruteForce(points: Seq[Point]): (Point, Point) = bruteForce(points.toList, Double.MaxValue, null)

  def decomposition(points: Seq[Point]): (Point, Point) = {
    def divideAndConquer(x: List[Point], y: List[Point]): (Point, Point) = {
      if(x.size <= 3) {
        bruteForce(x)
      } else {
        val divIndex = x.size >>> 1
        val leftOfCenter: List[Point] = x.slice(0, divIndex)
        val rightOfCenter: List[Point] = x.slice(divIndex, x.size)

        val cpl: (Point, Point) = divideAndConquer(leftOfCenter, leftOfCenter.sortBy(_.y))
        val cpr: (Point, Point) = divideAndConquer(rightOfCenter, rightOfCenter.sortBy(_.y))

        val closest = if(cpl._1.distance(cpl._2) < cpr._1.distance(cpr._2)) cpl else cpr

        val closestDistance = closest._1 distance closest._2
        val tmp: List[Point] = for(p <- y if math.abs(rightOfCenter.head.x - p.x) < closestDistance) yield p

        bruteForce(tmp, closestDistance, closest)
        /*var short = closestDistance
        var pair = closest
        for {
          i <- 0 until tmp.size - 1; p1 = tmp(i)
          j <- i+1 until tmp.size; p2 = tmp(j)
          if p2.y - p1.y < short
          distance = p1.distance(p2) if distance < pair._1.distance(pair._2)
        } {
          short = distance
          pair = (p1, p2)
        }
        pair*/
      }
    }
    divideAndConquer(points.sortBy(_.x).toList, points.sortBy(_.y).toList)
  }

  private def bruteForce(points: List[Point], minDistance: Double, min: (Point, Point)): (Point, Point) = {
    var m = min
    var md = minDistance
    for {
      i <- 0 until points.size - 1; p1 = points(i)
      j <- i + 1 until points.size; p2 = points(j)
      distance = p1.distance(p2)
      if distance < md
    } {
      m = (p1, p2)
      md = distance
    }
    m
  }
}