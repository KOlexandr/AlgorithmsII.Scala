package lab4.topoligical

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 24.2 Single-Source Shortest Paths
 * Single-source shortest paths in directed acyclic graphs
 */
object DagShortestPaths {

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
  def solve(graph: Graph, s: Int): List[Edge] = {
    val sorted: List[Int] = new TopologicalSort(graph).sort()
    val (d, p) = initializeSingleSource(graph, s)

    sorted.foreach(v => getEdges(v, graph.representEdges).foreach(e => relax(e, d, p)))
    getEdgesWay(p, graph).reverse
  }
}
