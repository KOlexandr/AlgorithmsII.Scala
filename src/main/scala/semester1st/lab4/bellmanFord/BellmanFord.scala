package semester1st.lab4.bellmanFord

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 24.1 Single-Source Shortest Paths
 * The Bellman-Ford algorithm
 */
object BellmanFord {

  private def NIL = Int.MinValue
  private def INF = Int.MaxValue

  private def initializeSingleSource(graph: Graph, s: Int): (Array[Int], Array[Int]) = {
    val d: Array[Int] = Array.fill(graph.vertexCount)(INF)
    val p: Array[Int] = Array.fill(graph.vertexCount)(NIL)
    d(s) = 0
    (d, p)
  }

  private def relax(e: Edge, d: Array[Int], p: Array[Int]) = {
    if(d(e.y) > d(e.x) + e.weight){
      d(e.y) = d(e.x) + e.weight
      p(e.y) = e.x
    }
  }

  private def getEdgesWay(p: Array[Int], graph: Graph): List[Edge] = {
    def build(idx: Int, edges: List[Edge]): List[Edge] = {
      if(idx >= p.length) edges
      else if(p(idx) != NIL) build(idx+1, findEdge(p(idx), idx, graph.representEdges) :: edges)
      else build(idx+1, edges)
    }
    build(0, List())
  }

  private def findEdge(x: Int, y: Int, edges: List[Edge]): Edge = {
    if(edges.head.x == x && edges.head.y == y) edges.head
    else findEdge(x, y, edges.tail)
  }

  /**
   * @param graph - graph for searching shortest path
   * @param s - number of start vertex
   */
  def solve(graph: Graph, s: Int): (Boolean, List[Edge]) = {
    val (d, p) = initializeSingleSource(graph, s)
    def searchCycle(edges: List[Edge]): Boolean = {
      if(edges.isEmpty) true
      else if(d(edges.head.y) > d(edges.head.x) + edges.head.weight) false
      else searchCycle(edges.tail)
    }

    val edges = graph.representEdges
    0.until(graph.vertexCount-1).foreach(i => edges.foreach(e => relax(e, d, p)))

    if(searchCycle(edges)) (true, getEdgesWay(p, graph).reverse)
    else (false, List())
  }
}
