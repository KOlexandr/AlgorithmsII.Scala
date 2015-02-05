package semester1st.lab4.dijkstra

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 24. Single-Source Shortest Paths
 * 24.3 Dijkstra's algorithm
 */
object Dijkstra {

  private def NIL = Int.MinValue
  private def INF = 10000

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

  private def getEdges(v: Int, edges: List[Edge]): List[Edge] = {
    if(edges.isEmpty) List()
    else if(edges.head.x == v) edges.head :: getEdges(v, edges.tail)
    else getEdges(v, edges.tail)
  }

  private def extractMin(q: List[Int], edges: List[Edge]): (Edge, List[Edge]) = {
    def findCrossEdge(e: List[Edge], min: Edge): Edge = {
      if(e.isEmpty) min
      else if(q.contains(e.head.y) && !q.contains(e.head.x) && e.head.weight < min.weight) findCrossEdge(e.tail, e.head)
      else findCrossEdge(e.tail, min)
    }
    def removeEdge(edges: List[Edge], e: Edge): List[Edge] = {
      if(edges.isEmpty) List()
      else if(e.x == edges.head.x && e.y == edges.head.y) removeEdge(edges.tail, e)
      else edges.head :: removeEdge(edges.tail, e)
    }
    val crossEdge: Edge = findCrossEdge(edges, new Edge(INF, INF, INF))
    (crossEdge, removeEdge(edges, crossEdge))
  }

  private def remove(v: Int, list: List[Int]): List[Int] = {
    if(list.isEmpty) List()
    else if(v == list.head) remove(v, list.tail)
    else list.head :: remove(v, list.tail)
  }

  private def findEdge(x: Int, y: Int, edges: List[Edge]): Edge = {
    if(edges.head.x == x && edges.head.y == y) edges.head
    else findEdge(x, y, edges.tail)
  }

  private def getEdgesWay(p: Array[Int], graph: Graph): List[Edge] = {
    def build(idx: Int, edges: List[Edge]): List[Edge] = {
      if(idx >= p.length) edges
      else if(p(idx) != NIL) build(idx+1, findEdge(p(idx), idx, graph.representEdges) :: edges)
      else build(idx+1, edges)
    }
    build(0, List())
  }

  /**
   * @param graph - graph for searching shortest path
   * @param s - number of start vertex
   */
  def solve(graph: Graph, s: Int): List[Edge] = {
    val (d, p) = initializeSingleSource(graph, s)

    def inner(q: List[Int], edgesList: List[Edge]): Unit = {
      if(q.nonEmpty){
        val (e, edges) = extractMin(q, edgesList)
        val (u, v) = if (q.contains(e.x)) (e.y, e.x) else (e.x, e.y)
        getEdges(u, graph.representEdges).foreach(e => relax(e, d, p))
        inner(remove(v, q), edges.sortBy(_.weight))
      }
    }

    inner(remove(s, List.range(0, graph.vertexCount)), graph.representEdges.sortBy(_.weight))
    getEdgesWay(p, graph).reverse
  }
}