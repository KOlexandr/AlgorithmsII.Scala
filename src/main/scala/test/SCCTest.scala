package test

import graph.{MatrixGraph, ListGraph}
import stronglyConnectedComponents.{MatrixSCC, ListSCC}

object SCCTest extends App {
  println("Directed List Graph")
  val lGraph = new ListGraph(VERTEX_COUNT_SCC, buildDirectedGraphSCC)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  val lSCC = new ListSCC(lGraph)
  lSCC.find()

  println("Directed Matrix Graph")
  val mGraph = new MatrixGraph(VERTEX_COUNT_SCC, buildDirectedGraphSCC)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()

  val mSCC = new MatrixSCC(mGraph)
  mSCC.find()
}