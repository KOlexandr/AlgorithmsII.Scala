package semester1st.lab2

import semester1st.lab2.sets.disjointSet.exercise.ListGraph
import semester1st.lab2.sets.disjointSet.{labProc, labFunc}
import semester1st.lab2.sets.disjointSet.optional.ArrayGraph

package object test {
  def VERTEX_COUNT: Int = 10

  def printGraph() = {
    println("Figure 21.1")
    println("a --- b     e --- f     h     j     0 --- 1     4 --- 5     7     9")
    println("|    /|     |           |           |    /|     |           |      ")
    println("|  /  |     |           |           |  /  |     |           |      ")
    println("|/    |     |           |           |/    |     |           |      ")
    println("c     d     g           i           2     3     6           8      ")
  }

  def buildDirectedGraph(graph: ListGraph) {
    printGraph()

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }

  def buildDirectedGraph(graph: labFunc.ListGraph) {
    printGraph()

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }

  def buildDirectedGraph(graph: labProc.ListGraph) {
    printGraph()

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }

  def buildDirectedGraph(graph: ArrayGraph) {
    printGraph()

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }
}