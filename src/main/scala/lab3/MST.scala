package lab3

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 23. Minimum Spanning Trees
 * 23.2 Algorithms of Kruskal and Prim
 */
object MST {

  def NIL = Int.MinValue
  def INF = Int.MaxValue

  /**
   * Algorithm of Kruskal
   * @param graph - graph
   * @return - list of edges which had included in minimum spanning tree
   */
  def kruskal(graph: Graph): List[Edge] = {
    var minimalTree: List[Edge] = List()

    val set: LLSet = new LLSet(graph.vertexCount)
    0.until(graph.vertexCount).foreach(v => set.makeSet(v))

    val edges = graph.representEdges.sortWith(_.weight < _.weight)

    edges.foreach(v => {
      if(set.findSet(v.x).vertex != set.findSet(v.y).vertex){
        minimalTree = minimalTree ::: List(v)
        set.union(v.x, v.y)
      }
    })
    minimalTree
  }

  def prim(graph: Graph, r: Int): List[Edge] = {
    val pi: Array[Int] = Array.fill(graph.vertexCount)(NIL)
    val key: Array[Int] = Array.fill(graph.vertexCount)(INF)

    key(r) = 0
    var minimalTree: List[Edge] = List()
    var vertexes: List[Int] = List()
    graph.representEdges.sortWith(_.weight < _.weight).foreach(e => {
      if(!vertexes.contains(e.x)){
        vertexes = vertexes ::: List(e.x)
      }
      if(!vertexes.contains(e.y)){
        vertexes = vertexes ::: List(e.y)
      }
    })

    def w(x: Int, y: Int): Int = {
      def edgeWeight(edges: List[Edge]): Int = {
        if(edges.isEmpty) INF
        else if(edges.head.x == x && edges.head.y == y) edges.head.weight
        else edgeWeight(edges.tail)
      }
      edgeWeight(graph.representEdges)
    }

    def inner(sv: List[Int]): Unit = {
      if(sv.nonEmpty){
        val u = sv.head
        graph.representVertex(u).foreach(v => {
          if(sv.contains(v) && w(u, v) < key(v)){
            pi(v) = u
            key(v) = w(u, v)
            minimalTree = minimalTree ::: List(new Edge(u, v, key(v)))
          }
        })
        inner(sv.tail)
      }
    }
    inner(vertexes)
    minimalTree
  }
}
