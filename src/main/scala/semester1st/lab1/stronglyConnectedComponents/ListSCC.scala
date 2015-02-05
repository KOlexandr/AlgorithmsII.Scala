package semester1st.lab1.stronglyConnectedComponents

import semester1st.lab1.graph.Graph
import semester1st.lab1.topologicalSort.ListTopologicalSort

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.5 Strongly Connected Component
  * adjacency-list representation
*/
class ListSCC(g: Graph[List[List[Int]]]) extends StronglyConnectedComponents[List[List[Int]]](g) {

  override protected def dfsVisit(g: List[List[Int]], u: Int): Unit = {
    if (color(u) != BLACK) {
      color(u) = GRAY
      time += 1
      d(u) = time
      print("(" + u + " ")
      if (null != g(u)) {
        g(u).filter(v => color(v) == WHITE).foreach(v => {
          pi(v) = u
          dfsVisit(g, v)
        })
      }
      color(u) = BLACK
      time += 1
      f(u) = time
      print(u + ") ")
    }
  }

  override protected def transpose(g: List[List[Int]]): List[List[Int]] = {
    var list: List[List[Int]] = makeList(List(), graph.vertexCount)
    List.range(0, graph.vertexCount).foreach(rowId => {
      g(rowId).foreach(vertex => {
        list = list.updated(vertex, list(vertex) ::: List(rowId))
      })
    })
    list
  }

  override def find(): Unit = {
    val originalDfs: List[Int] = new ListTopologicalSort(graph).sort()
    dfs(transpose(graph.represent()), originalDfs)
  }
}