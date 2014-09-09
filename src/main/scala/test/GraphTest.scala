package test

import graph.{ListGraph, MatrixGraph}

object GraphTest extends App {

  println("Directed List Graph Representation")
  val lGraph = new ListGraph(VERTEX_COUNT, buildDirectedGraph)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  println("Directed Matrix Graph Representation")
  val mGraph = new MatrixGraph(VERTEX_COUNT, buildDirectedGraph)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()
}