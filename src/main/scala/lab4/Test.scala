package lab4

import lab4.bellmanFord.{ShortestPaths, Graph}

object Test {

  def main(args: Array[String]) {
    val graph = new Graph(5)
    graph.buildGraph()
    graph.printAdjacencyList()

    val (status, way) = ShortestPaths.bellmanFord(graph, 0)
    if(status) println("Ok: " + way.map(e => e.toString).reduce(_ + " => " + _))
    else println("Error")
  }
}
