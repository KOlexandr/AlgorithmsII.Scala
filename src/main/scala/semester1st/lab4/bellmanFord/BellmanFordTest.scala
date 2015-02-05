package semester1st.lab4.bellmanFord

object BellmanFordTest {

  def main(args: Array[String]) {
    val graph = new Graph(5)
    graph.buildGraph()
    graph.printAdjacencyList()

    val (status, way) = BellmanFord.solve(graph, 0)
    if(status) println("Ok: " + way.map(e => e.toString).reduce(_ + " => " + _))
    else println("Error")
  }
}
