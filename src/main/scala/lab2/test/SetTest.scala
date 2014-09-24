package lab2.test

import lab2.StronglyConnectedComponents
import lab2.sets.disjointSet
import lab2.sets.disjointSet.exercise.ListGraph
import lab2.sets.disjointSet.{labProc, labFunc}
import lab2.sets.disjointSet.optional.ArrayGraph

object SetTest extends App {

  println("DS Exercise Linked List")
  val gle = new ListGraph(VERTEX_COUNT, buildDirectedGraph)
  gle.buildGraph()
  gle.printAdjacencyList()
  StronglyConnectedComponents.find(gle)

  println("DS Linked List Function")
  val glf = new labFunc.ListGraph(VERTEX_COUNT, buildDirectedGraph)
  glf.buildGraph()
  glf.printAdjacencyList()
  StronglyConnectedComponents.find(glf)

  println("DS Exercise Linked List Procedure")
  val glp = new labProc.ListGraph(VERTEX_COUNT, buildDirectedGraph)
  glp.buildGraph()
  glp.printAdjacencyList()
  StronglyConnectedComponents.find(glp)
  
  println("DS Array")
  val ga = new ArrayGraph(VERTEX_COUNT, buildDirectedGraph)
  ga.buildGraph()
  ga.printAdjacencyList()
  StronglyConnectedComponents.find(ga)
}