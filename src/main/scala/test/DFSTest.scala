package test

import dfs.{MatrixDFS, ListDFS}
import graph.{MatrixGraph, ListGraph}

object DFSTest extends App {
  println("Directed List Graph")
  val lGraph = new ListGraph(VERTEX_COUNT, buildDirectedGraph)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  val lDfs = new ListDFS(lGraph)
  lDfs.dfs()
  println()

  println("Directed Matrix Graph")
  val mGraph = new MatrixGraph(VERTEX_COUNT, buildDirectedGraph)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()

  val mDfs = new MatrixDFS(mGraph)
  mDfs.dfs()
  println()
}