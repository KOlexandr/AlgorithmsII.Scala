package semester1st.lab2.sets.disjointSetForest

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 21. Data Structures for Disjoint Sets
 * 21.3 Linked-list representation of Disjoint-Set Forest
 */
class LLSet(vCount: Int) {

  var data: List[Node] = List()
  val vertexes: Array[Node] = Array.ofDim(vCount)

  def makeSet(x: Int) = {
    val xs: Node = new Node(x)
    data = data ::: List(xs)
    vertexes(x) = xs
  }

  def union(x: Int, y: Int) = link(findSet(x), findSet(y))

  def link(x: Node, y: Node) = {
    if(x.vertex != y.vertex) {
      if (x.rank > y.rank) {
        vertexes(y.vertex).parent = x
        data = data.filter(p => p.vertex != y.vertex)
      } else if (x.rank == y.rank) {
        vertexes(x.vertex).parent = y
        vertexes(y.vertex).rank = y.rank + 1
        data = data.filter(p => p.vertex != x.vertex)
      } else {
        vertexes(x.vertex).parent = y
        data = data.filter(p => p.vertex != x.vertex)
      }
    }
  }

  def findSet(x: Int): Node = if(null == vertexes(x).parent) vertexes(x) else vertexes(x).parent
}

class Node(var vertex: Int, var parent: Node, var rank: Int) {

  def this(vertex: Int) = this(vertex, null, 0)
}

/**
 * StronglyConnectedComponents algorithm with using disjoint set forest based on linked list
 */
object SCCList {

  def find(g: ListGraph): Unit = {
    val set: LLSet = new LLSet(g.vertexCount)
    0.until(g.vertexCount).foreach(v => set.makeSet(v))
    val represent: List[List[Int]] = g.represent()
    0.until(g.vertexCount).foreach(i => if (represent(i).nonEmpty) represent(i).foreach(v => set.union(i, v)))

    set.data.foreach(i => {
      var list: List[Int] = List(i.vertex)
      0.until(g.vertexCount).foreach(v => {
        if (i == set.vertexes(v).parent) list = list ::: List(v)
      })
      println(i.vertex + ": " + list.sortWith(_ < _).map(String.valueOf).reduce(_ + "," + _))
    })
  }
}