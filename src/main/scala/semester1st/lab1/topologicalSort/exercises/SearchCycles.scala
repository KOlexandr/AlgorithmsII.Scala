package semester1st.lab1.topologicalSort.exercises

import semester1st.lab1.graph.Graph

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.4 Topological Sort. Exercises
  * adjacency-matrix representation
*/
object SearchCycles {

  protected def GRAY: Int = 1
  protected def WHITE: Int = 0
  protected def BLACK: Int = 2

  /**
   * Exercise 22.4-3
   */
  def countCycles(graph: Graph[List[List[Int]]]) : Int = {
    var count: Int = 0
    val color: Array[Int] = Array.fill[Int](graph.vertexCount)(WHITE)

    def dfsVisit(u: Int, g: List[List[Int]]): Unit = {
      color(u) = GRAY
      if (null != g(u)) {
        g(u).foreach(v => {
          if(color(v) == WHITE) {
            dfsVisit(v, g)
          } else if(color(v) == GRAY){
            count += 1
          }
        })
      }
      color(u) = BLACK
    }

    List.range(0, graph.vertexCount).foreach(i => {
      if(color(i) == WHITE) {
        dfsVisit(i, graph.represent())
      }
    })
    count
  }

  def existCycles(graph: Graph[List[List[Int]]]): Boolean = {
    countCycles(graph) > 0
  }

  /**
   * Exercise 22.4-2
   */
  def countPaths(graph: Graph[List[List[Int]]], start: Int, end: Int): Int = {
    var count: Int = 0

    def dfsVisit(u: Int, g: List[List[Int]], path: String): Unit = {
      if (null != g(u)) {
        g(u).foreach(v => {
          if(v == end) {
            dfsVisit(v, g, path)
            count += 1
            println(path + " -> " + end)
          } else {
            dfsVisit(v, g, path + " -> " + v)
          }
        })
      }
    }

    dfsVisit(start, graph.represent(), String.valueOf(start))
    count
  }
}
