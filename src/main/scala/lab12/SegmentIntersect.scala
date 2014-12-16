package lab12

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

  // computes relative orientations using the cross-product method
  //               |x1   x2|
  //  p1 x p2 = det|       | = x1y2 - x2y1
  //               |y1   y2|
  //
  //  return (pk - pj) x (pj - pi)
  def direction(pi: Point, pj: Point, pk: Point): Int =
    (pk.x - pj.x) * (pi.y - pj.y) - (pi.x - pj.x) * (pk.y - pj.y)

  /** determines whether a point known to be collinear with **/
  /** a segment lies on that segment **/
  def onSegment(pi: Point, pj: Point, pk: Point): Boolean =
    math.min(pi.x, pj.x) <= pk.x && pk.x <= math.max(pi.x, pj.x) && math.min(pi.y, pj.y) <= pk.y && pk.y <= math.max(pi.y, pj.y)

  def anySegmentsIntersect(lines: Array[Line]): Boolean = {
    val sortedLines: Array[Line] = lines.sortBy(_.p1.x)
    val indexedByLine: Map[Line, Int] = lines.zipWithIndex.map(e => e._1 -> e._2).toMap
    val indexedById: Map[Int, Line] = indexedByLine.map(e => e._2 -> e._1).toMap

    val points: Array[Point] = sortedLines.map(l => Array(l.p1, l.p2)).flatten
    val sortedPoints = points.sortBy(_.x)
    val leftBordered = lines.map(l => l.p1 -> l).toMap
    val rightBordered = lines.map(l => l.p2 -> l).toMap

    var tree: Tree = new Tree(null)

    for(p <- sortedPoints){
      if(leftBordered.contains(p)) {
        tree = tree.insert(new TNode(null, null, null, indexedByLine(leftBordered(p))))
        val predecessor: TNode = T.predecessor(tree.search(indexedByLine(leftBordered(p))))
        val successor: TNode = T.successor(tree.search(indexedByLine(leftBordered(p))))
        if ((null != predecessor && isIntersect(indexedById(predecessor.key), leftBordered(p))) ||
          null != successor && isIntersect(indexedById(successor.key), leftBordered(p))) {
          return true
        }
      }
      if(rightBordered.contains(p)) {
        tree = tree.insert(new TNode(null, null, null, indexedByLine(rightBordered(p))))
        val predecessor: TNode = T.predecessor(tree.search(indexedByLine(rightBordered(p))))
        val successor: TNode = T.successor(tree.search(indexedByLine(rightBordered(p))))
        if (null != predecessor && null != successor && isIntersect(indexedById(successor.key), indexedById(predecessor.key))) {
          return true
        }
        tree = tree.delete(tree.search(indexedByLine(rightBordered(p))))
      }
    }
    false
  }
}