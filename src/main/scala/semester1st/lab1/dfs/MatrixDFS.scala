package semester1st.lab1.dfs

import semester1st.lab1.graph.Graph

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.3 Depth-first search
  * adjacency-matrix representation
*/
class MatrixDFS(g: Graph[Array[Array[Int]]]) extends DFS[Array[Array[Int]]](g) {

  override protected def dfsVisit(u: Int): Unit = {
    color(u) = GRAY
    time += 1
    d(u) = time
    print("(" + u + " ")
    List.range(0, graph.vertexCount).foreach(v => {
      if(graph.represent()(u)(v) == 1){
        if(color(v) == WHITE){
          pi(v) = u
          dfsVisit(v)
        }
      }
    })
    color(u) = BLACK
    time += 1
    f(u) = time
    println(u + ") ")
  }
}
