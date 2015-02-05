package semester1st.lab1.test

import semester1st.lab1.graph.{MatrixGraph, ListGraph}
import semester1st.lab1.stronglyConnectedComponents.{MatrixSCC, ListSCC}

object SCCTest extends App {
  println("Directed List Graph")
  val lGraph = new ListGraph(_root_.test.VERTEX_COUNT_SCC, _root_.test.buildDirectedGraphSCC)
  lGraph.buildGraph()
  lGraph.printAdjacencyList()

  val lSCC = new ListSCC(lGraph)
  lSCC.find()

  println("Directed Matrix Graph")
  val mGraph = new MatrixGraph(_root_.test.VERTEX_COUNT_SCC, _root_.test.buildDirectedGraphSCC)
  mGraph.buildGraph()
  mGraph.printAdjacencyList()

  val mSCC = new MatrixSCC(mGraph)
  mSCC.find()
}