package lab2.test

import lab2.StronglyConnectedComponents
import lab2.graph.{ListGraph, ArrayGraph}

object SetTest extends App {

  println("DSU Linked List")
  val gl = new ListGraph(VERTEX_COUNT, buildDirectedGraph)
  gl.buildGraph()
  gl.printAdjacencyList()
  StronglyConnectedComponents.find(gl)


  println("DSU Array")
  val ga = new ArrayGraph(VERTEX_COUNT, buildDirectedGraph)
  ga.buildGraph()
  ga.printAdjacencyList()
  StronglyConnectedComponents.find(ga)
}