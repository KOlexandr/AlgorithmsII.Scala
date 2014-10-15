package lab4.bellmanFord

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 24. Single-Source Shortest Paths
 */
object ShortestPaths {

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

  /**
   * The Bellman-Ford algorithm
   * @param graph - graph for searching shortest path
   * @param s - number ov start vertex
   */
  def bellmanFord(graph: Graph, s: Int): (Boolean, List[Edge]) = {
    val (d, p) = initializeSingleSource(graph, s)
    def searchCycle(edges: List[Edge]): Boolean = {
      if(edges.isEmpty) true
      else if(d(edges.head.y) > d(edges.head.x) + edges.head.weight) false
      else searchCycle(edges.tail)
    }
    def getEdgesWay(start: Int, rest: List[Int], edges: List[Edge]): List[Edge] = {
      if(rest.isEmpty) edges
      else getEdgesWay(rest.head, rest.tail, findEdge(start, rest.head, graph.representEdges) :: edges)
    }
    def getVertexesWay(start: Int): List[Int] = {
      def next(idx: Int, n: Int): Int = {
        if(idx >= p.length) n
        else if(n == p(idx)) idx
        else next(idx+1, n)
      }
      def findWay(cur: Int, n: Int): List[Int] = {
        if(cur == n) List()
        else n :: findWay(n, next(0, n))
      }
      findWay(-1, start)
    }

    def findEdge(x: Int, y: Int, edges: List[Edge]): Edge = {
      if(edges.head.x == x && edges.head.y == y) edges.head
      else findEdge(x, y, edges.tail)
    }

    val edges = graph.representEdges
    0.until(graph.vertexCount-1).foreach(i => edges.foreach(e => relax(e, d, p)))

    val vertexWay = getVertexesWay(s)
    if(searchCycle(edges)) (true, getEdgesWay(vertexWay.head, vertexWay.tail, List()).reverse)
    else (false, List())
  }
}
