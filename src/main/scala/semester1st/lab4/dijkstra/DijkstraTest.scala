package semester1st.lab4.dijkstra

object DijkstraTest {

   def main(args: Array[String]) {
     val graph = new Graph(5)
     graph.buildGraph()
     graph.printAdjacencyList()

     val way = Dijkstra.solve(graph, 0)
     println(way.map(e => e.toString).reduce(_ + " => " + _))
   }
 }