package lab2.test

import lab2.StronglyConnectedComponents
import lab2.sets.disjointSet
import lab2.sets.disjointSet.exercise.ListGraph
import lab2.sets.disjointSet.lab
import lab2.sets.disjointSet.optional.ArrayGraph

object SetTest extends App {

  println("DS Exercise Linked List")
  val gle = new ListGraph(VERTEX_COUNT, buildDirectedGraph)
  gle.buildGraph()
  gle.printAdjacencyList()
  StronglyConnectedComponents.find(gle)

  println("DS Exercise Linked List")
  val gl = new lab.ListGraph(VERTEX_COUNT, buildDirectedGraph)
  gl.buildGraph()
  gl.printAdjacencyList()
  StronglyConnectedComponents.find(gl)

  println("DS Array")
  val ga = new ArrayGraph(VERTEX_COUNT, buildDirectedGraph)
  ga.buildGraph()
  ga.printAdjacencyList()
  StronglyConnectedComponents.find(ga)
}