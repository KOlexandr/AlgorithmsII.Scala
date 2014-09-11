package test

import graph.ListGraph
import topologicalSort.exercises.SearchCycles

object ExercisesTests extends App {

  def ex22_4_3Test(): Unit = {
    println("0 cycles test")
    var lGraph = new ListGraph(VERTEX_COUNT_TOP_SORT, buildDirectedGraphTopSort)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countCycles(lGraph) + " cycles\n")

    println("4 cycles test")
    lGraph = new ListGraph(VERTEX_COUNT_TOP_SORT, buildDirectedGraphSCC)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countCycles(lGraph) + " cycles\n")

    println("2 cycles test")
    lGraph = new ListGraph(VERTEX_COUNT_TOP_SORT, buildDirectedGraph)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countCycles(lGraph) + " cycles\n")
  }

  def ex22_4_2Test(): Unit = {
    println("Test 1")
    val lGraph = new ListGraph(VERTEX_COUNT_EX22_4_2, buildDirectedGraphEx22_4_2)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countPaths(lGraph, 3, 9) + " paths\n")
  }

//  ex22_4_3Test()
  ex22_4_2Test()

}
