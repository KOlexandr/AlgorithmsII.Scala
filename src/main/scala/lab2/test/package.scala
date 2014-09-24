package lab2

import lab2.sets.disjointSet
import lab2.sets.disjointSet.exercise.ListGraph
import lab2.sets.disjointSet.lab
import lab2.sets.disjointSet.optional.ArrayGraph

package object test {
  def VERTEX_COUNT: Int = 10

  def buildDirectedGraph(graph: ListGraph) {
    println("Figure 21.1")
    println("a --- b     e --- f     h     j     0 --- 1     4 --- 5     7     9")
    println("|    /|     |           |           |    /|     |           |      ")
    println("|  /  |     |           |           |  /  |     |           |      ")
    println("|/    |     |           |           |/    |     |           |      ")
    println("c     d     g           i           2     3     6           8      ")

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }

  def buildDirectedGraph(graph: lab.ListGraph) {
    println("Figure 21.1")
    println("a --- b     e --- f     h     j     0 --- 1     4 --- 5     7     9")
    println("|    /|     |           |           |    /|     |           |      ")
    println("|  /  |     |           |           |  /  |     |           |      ")
    println("|/    |     |           |           |/    |     |           |      ")
    println("c     d     g           i           2     3     6           8      ")

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }

  def buildDirectedGraph(graph: ArrayGraph) {
    println("Figure 21.1")
    println("a --- b     e --- f     h     j     0 --- 1     4 --- 5     7     9")
    println("|    /|     |           |           |    /|     |           |      ")
    println("|  /  |     |           |           |  /  |     |           |      ")
    println("|/    |     |           |           |/    |     |           |      ")
    println("c     d     g           i           2     3     6           8      ")

    graph.createEdge(0, 1)
    graph.createEdge(0, 2)
    graph.createEdge(1, 2)
    graph.createEdge(1, 3)

    graph.createEdge(4, 5)
    graph.createEdge(4, 6)

    graph.createEdge(7, 8)
  }
}