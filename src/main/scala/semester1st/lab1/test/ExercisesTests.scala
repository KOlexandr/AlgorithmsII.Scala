package semester1st.lab1.test

import semester1st.lab1.graph.ListGraph
import semester1st.lab1.stronglyConnectedComponents.exercises.BuildStronglyComponentsGraph
import semester1st.lab1.topologicalSort.exercises.SearchCycles

object ExercisesTests extends App {

  def ex22_4_3Test(): Unit = {
    println("0 cycles test")
    var lGraph = new ListGraph(_root_.test.VERTEX_COUNT_TOP_SORT, _root_.test.buildDirectedGraphTopSort)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countCycles(lGraph) + " cycles\n")

    println("4 cycles test")
    lGraph = new ListGraph(_root_.test.VERTEX_COUNT_TOP_SORT, _root_.test.buildDirectedGraphSCC)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countCycles(lGraph) + " cycles\n")

    println("2 cycles test")
    lGraph = new ListGraph(_root_.test.VERTEX_COUNT_TOP_SORT, _root_.test.buildDirectedGraph)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countCycles(lGraph) + " cycles\n")
  }

  def ex22_4_2Test(): Unit = {
    println("Test 1")
    val lGraph = new ListGraph(_root_.test.VERTEX_COUNT_EX22_4_2, _root_.test.buildDirectedGraphEx22_4_2)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()
    println("There are: " + SearchCycles.countPaths(lGraph, 3, 9) + " paths\n")
  }

  def ex22_5_5Test(): Unit = {
    println("Directed List Graph")
    val lGraph = new ListGraph(_root_.test.VERTEX_COUNT_SCC, _root_.test.buildDirectedGraphSCC)
    lGraph.buildGraph()
    lGraph.printAdjacencyList()

    val lSCC = new BuildStronglyComponentsGraph(lGraph)
    lSCC.build()
    lSCC.printGraph()
  }

//  ex22_4_3Test()
//  ex22_4_2Test()
  ex22_5_5Test()
}