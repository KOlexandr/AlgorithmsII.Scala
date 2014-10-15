package lab4.topoligical

object DagTest {

  def main(args: Array[String]) {
    val graph = new Graph(6)
    graph.buildGraph()
    graph.printAdjacencyList()

    val way = DagShortestPaths.solve(graph, 1)
    println(way.map(e => e.toString).reduce(_ + " => " + _))
  }
}