package semester1st.lab12_13

/**
* Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
* Chapter 33. Computational Geometry
*   33.1 Line-segment properties
*     Determining whether two line segments intersect
*   33.2 Determining whether any pair of segments intersects
*/
object SegmentIntersect {

  /** determines whether two line segments intersect **/
  def isIntersect(l1: Line, l2: Line): Boolean = {
    val d1 = direction(l2.p1, l2.p2, l1.p1)
    val d2 = direction(l2.p1, l2.p2, l1.p2)
    val d3 = direction(l1.p1, l1.p2, l2.p1)
    val d4 = direction(l1.p1, l1.p2, l2.p2)

    if(((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) {
      true
    } else if(d1 == 0 && onSegment(l2.p1, l2.p2, l1.p1)) {
      true
    } else if(d2 == 0 && onSegment(l2.p1, l2.p2, l1.p2)) {
      true
    } else if(d3 == 0 && onSegment(l1.p1, l1.p2, l2.p1)) {
      true
    } else if(d4 == 0 && onSegment(l1.p1, l1.p2, l2.p2)) {
      true
    } else {
      false
    }
  }

  /**
   * computes relative orientations using the cross-product method
   *              |x1   x2|
   * p1 x p2 = det|       | = x1y2 - x2y1
   *              |y1   y2|
   *
   * return (pk - pj) x (pj - pi)
   */
  def direction(pi: Point, pj: Point, pk: Point): Int =
    (pk.x - pj.x) * (pi.y - pj.y) - (pi.x - pj.x) * (pk.y - pj.y)

  /** determines whether a point known to be collinear with **/
  /** a segment lies on that segment **/
  def onSegment(pi: Point, pj: Point, pk: Point): Boolean =
    math.min(pi.x, pj.x) <= pk.x && pk.x <= math.max(pi.x, pj.x) && math.min(pi.y, pj.y) <= pk.y && pk.y <= math.max(pi.y, pj.y)

  /** returns true if any pair of segments in set lines intersects, and false otherwise **/
  def anySegmentsIntersect(lines: Array[Line]): Boolean = {
    val sLines: Array[Line] = lines.sortBy(_.p1.x)  //lines sorted by left points
    val lineIdx: Map[Line, Int] = lines.zipWithIndex.map(e => e._1 -> e._2).toMap //indexes of lines
    val left: Map[Point, Line] = lines.map(l => l.p1 -> l).toMap  //left border of each line
    val right: Map[Point, Line] = lines.map(l => l.p2 -> l).toMap //right border of each line

    val points: Array[Point] = sLines.map(l => Array(l.p1, l.p2)).flatten //all points (left and right borders of each line)

    def innerIntersect(tree: Tree, points: Array[Point]): Boolean = {
      if(points.isEmpty) {
        false
      } else {
        val head: Point = points.head
        if (left.contains(head)) {
          val newTree = tree.insert(lineIdx(left(head)))
          val predecessor: TNode = T.predecessor(newTree.search(lineIdx(left(head))))
          if (null != predecessor && isIntersect(sLines(predecessor.key), left(head))) {
            true
          } else {
            val successor: TNode = T.successor(newTree.search(lineIdx(left(head))))
            if (null != successor && isIntersect(sLines(successor.key), left(head))) {
              true
            } else {
              innerIntersect(newTree, points.tail)
            }
          }
        } else {
          val predecessor: TNode = T.predecessor(tree.search(lineIdx(right(head))))
          val successor: TNode = T.successor(tree.search(lineIdx(right(head))))
          if (null != predecessor && null != successor && isIntersect(sLines(successor.key), sLines(predecessor.key))) {
            true
          } else {
            innerIntersect(tree.delete(lineIdx(right(head))), points.tail)
          }
        }
      }
    }
    innerIntersect(new Tree(), points.sortBy(_.x))
  }
}