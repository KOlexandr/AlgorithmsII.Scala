package test

import graph.{MatrixGraph, ListGraph}
import topologicalSort.{MatrixTopologicalSort, ListTopologicalSort}

object SortTest extends App {
  println("Directed List Graph")
  val lGraph = new ListGraph(VERTEX_COUNT_TOP_SORT, buildDirectedGraphTopSort)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  val lSort = new ListTopologicalSort(lGraph)
  println(lSort.sort().map(String.valueOf).reduce((x, y) => x + " -> " + y))

  println("Directed Matrix Graph")
  val mGraph = new MatrixGraph(VERTEX_COUNT_TOP_SORT, buildDirectedGraphTopSort)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()

  val mSort = new MatrixTopologicalSort(mGraph)
  println(mSort.sort().map(String.valueOf).reduce((x, y) => x + " -> " + y))
}