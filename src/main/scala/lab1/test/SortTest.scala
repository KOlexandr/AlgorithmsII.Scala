package lab1.test

import lab1.graph.{MatrixGraph, ListGraph}
import lab1.topologicalSort.{MatrixTopologicalSort, ListTopologicalSort}

object SortTest extends App {
  println("Directed List Graph")
  val lGraph = new ListGraph(_root_.test.VERTEX_COUNT_TOP_SORT, _root_.test.buildDirectedGraphTopSort)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  val lSort = new ListTopologicalSort(lGraph)
  println(lSort.sort().map(String.valueOf).reduce((x, y) => x + " -> " + y))

  println("Directed Matrix Graph")
  val mGraph = new MatrixGraph(_root_.test.VERTEX_COUNT_TOP_SORT, _root_.test.buildDirectedGraphTopSort)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()

  val mSort = new MatrixTopologicalSort(mGraph)
  println(mSort.sort().map(String.valueOf).reduce((x, y) => x + " -> " + y))
}