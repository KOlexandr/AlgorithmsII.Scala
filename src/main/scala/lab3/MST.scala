package lab3

import scala.collection.immutable.Queue

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

    def inner(q: List[Int], edgesList: List[Edge], tree: List[Edge]): List[Edge] = {
      if(q.nonEmpty){
        val (e, edges) = extractMin(q, edgesList)
        if(e.weight < INF) {
          val u: Int = if (q.contains(e.x)) e.y else e.x
          for (v: Int <- graph.representVertex(u)) {
            val weight: Int = w(u, v)
            if (weight < key(v) && q.contains(v)) {
              key(v) = weight
              pi(v) = u
            }
          }
          inner(u :: q, edges.sortBy(_.weight), e :: tree)
        } else {
          tree
        }
      } else {
        tree
      }
    }

    def extractMin(q: List[Int], edges: List[Edge]): (Edge, List[Edge]) = {
      def findCrossEdge(e: List[Edge], min: Edge): Edge = {
        if(e.isEmpty) min
        else if(q.contains(e.head.x) && !q.contains(e.head.y) && e.head.weight < min.weight) findCrossEdge(e.tail, e.head)
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

    def w(x: Int, y: Int): Int = {
      def edgeWeight(edges: List[Edge]): Int = {
        if(edges.isEmpty) INF
        else if(edges.head.x == x && edges.head.y == y) edges.head.weight
        else edgeWeight(edges.tail)
      }
      edgeWeight(graph.representEdges)
    }
    inner(List(r), graph.representEdges.sortBy(_.weight), List())
  }
}