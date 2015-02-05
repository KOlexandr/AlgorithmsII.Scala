package semester1st.lab1.test

import semester1st.lab1.graph.{ListGraph, MatrixGraph}

object GraphTest extends App {

  println("Directed List Graph Representation")
  val lGraph = new ListGraph(_root_.test.VERTEX_COUNT, _root_.test.buildDirectedGraph)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  println("Directed Matrix Graph Representation")
  val mGraph = new MatrixGraph(_root_.test.VERTEX_COUNT, _root_.test.buildDirectedGraph)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()
}