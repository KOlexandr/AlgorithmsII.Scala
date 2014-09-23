package lab1.topologicalSort

import lab1.graph.Graph

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.4 Topological Sort
  * adjacency-matrix representation
*/
class MatrixTopologicalSort(g: Graph[Array[Array[Int]]]) extends TopologicalSort[Array[Array[Int]]](g) {

  override protected def dfsVisit(u: Int) {
    if(color(u) != BLACK) {
      color(u) = GRAY
      time += 1
      d(u) = time
      List.range(0, graph.vertexCount).foreach(v => {
        if (graph.represent()(u)(v) == 1 && color(v) == WHITE) {
          pi(v) = u
          dfsVisit(v)
        }
      })
      sorted = sorted ::: List(u)
      color(u) = BLACK
      time += 1
      f(u) = time
    }
  }
}