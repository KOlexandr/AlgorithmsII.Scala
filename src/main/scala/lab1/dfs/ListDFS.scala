package lab1.dfs

import lab1.graph.Graph

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.3 Depth-first search
  * adjacency-list representation
*/
class ListDFS(g: Graph[List[List[Int]]]) extends DFS[List[List[Int]]](g) {

  override protected def dfsVisit(u: Int): Unit = {
    color(u) = GRAY
    time += 1
    d(u) = time
    print("(" + u + " ")
    if(null != graph.represent()(u)) {
      graph.represent()(u).filter(v => color(v) == WHITE).foreach(v => {
        pi(v) = u
        dfsVisit(v)
      })
    }
    color(u) = BLACK
    time += 1
    f(u) = time
    println(u + ") ")
  }
}
