package semester1st.lab3

object Test extends App {
  val graph = new Graph(9)
  graph.buildGraph()
  graph.printAdjacencyList()

  println("Search Minimum Spanning Tree (Kruskal)")
  val kruskal: List[Edge] = MST.kruskal(graph)
  kruskal.foreach(println)

  println("Search Minimum Spanning Tree (Prim)")
  val prim: List[Edge] = MST.prim(graph, 0)
  prim.foreach(println)
}