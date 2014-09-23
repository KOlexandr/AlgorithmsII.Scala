package lab1.test

import lab1.dfs.{MatrixDFS, ListDFS}
import lab1.graph.{MatrixGraph, ListGraph}

object DFSTest extends App {
  println("Directed List Graph")
  val lGraph = new ListGraph(_root_.test.VERTEX_COUNT, _root_.test.buildDirectedGraph)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  val lDfs = new ListDFS(lGraph)
  lDfs.dfs()
  println()

  println("Directed Matrix Graph")
  val mGraph = new MatrixGraph(_root_.test.VERTEX_COUNT, _root_.test.buildDirectedGraph)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()

  val mDfs = new MatrixDFS(mGraph)
  mDfs.dfs()
  println()
}