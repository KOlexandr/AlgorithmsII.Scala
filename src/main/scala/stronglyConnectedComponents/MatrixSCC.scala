package stronglyConnectedComponents

import graph.Graph
import topologicalSort.{MatrixTopologicalSort, ListTopologicalSort}

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.5 Strongly Connected Component
  * adjacency-matrix representation
*/
class MatrixSCC(g: Graph[Array[Array[Int]]]) extends StronglyConnectedComponents[Array[Array[Int]]](g) {

  override protected def dfsVisit(g: Array[Array[Int]], u: Int): Unit = {
    if(color(u) != BLACK) {
      color(u) = GRAY
      time += 1
      d(u) = time
      print("(" + u + " ")
      List.range(0, g.length).foreach(v => {
        if (g(u)(v) == 1 && color(v) == WHITE) {
          pi(v) = u
          dfsVisit(g, v)
        }
      })
      color(u) = BLACK
      time += 1
      f(u) = time
      print(u + ") ")
    }
  }

  override protected def transpose(g: Array[Array[Int]]): Array[Array[Int]] = {
    val array: Array[Array[Int]] = Array.ofDim(graph.vertexCount, graph.vertexCount)
    List.range(0, graph.vertexCount).foreach(i => {
      List.range(0, graph.vertexCount).foreach(j => {
        array(i)(j) = g(j)(i)
      })
    })
    array
  }

  override def find(): Unit = {
    val originalDfs: List[Int] = new MatrixTopologicalSort(graph).sort()
    dfs(transpose(graph.represent()), originalDfs)
  }
}