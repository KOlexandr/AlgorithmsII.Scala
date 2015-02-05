package semester1st.lab1.topologicalSort

import semester1st.lab1.graph.Graph

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.4 Topological Sort
  * adjacency-list representation
*/
class ListTopologicalSort(g: Graph[List[List[Int]]]) extends TopologicalSort[List[List[Int]]](g) {

  override protected def dfsVisit(u: Int): Unit = {
    if(color(u) != BLACK) {
      color(u) = GRAY
      time += 1
      d(u) = time
      if (null != graph.represent()(u)) {
        graph.represent()(u).filter(v => color(v) == WHITE).foreach(v => {
          pi(v) = u
          dfsVisit(v)
        })
      }
      sorted = sorted ::: List(u)
      color(u) = BLACK
      time += 1
      f(u) = time
    }
  }
}