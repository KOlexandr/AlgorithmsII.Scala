package lab3

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

  def findSet(x: Int): Node = if(null == vertexes(x).parent) vertexes(x) else findSet(vertexes(x).parent.vertex)

  def remove(x: Int) = {
    vertexes(x).rank = 0
    vertexes(x).parent = null
    data = data ::: List(vertexes(x))
  }
}

class Node(var vertex: Int, var parent: Node, var rank: Int) {
  def this(vertex: Int) = this(vertex, null, 0)
}

class Graph(val vertexCount: Int) {

  private var edges: List[Edge] = List()
  private var list: List[List[Int]] = makeList(List(), vertexCount)

  def buildGraph() = {
    println("Figure 23.2")
    println("    - b ------ c ------ d -             - 1 ------ 2 ------ 3 -    ")
    println("  /   |     /    \\      |  \\          /   |     /    \\      |  \\   ")
    println("a     |   i        \\    |     e     0     |   8        \\    |     4")
    println("  \\   | /   \\        \\  |  /          \\   | /   \\        \\  |  /   ")
    println("    - h ------ g ------ f -             - 7 ------ 6 ------ 5 -    ")

    createEdge(0, 1, 4)
    createEdge(0, 7, 8)

    createEdge(1, 2, 8)
    createEdge(1, 7, 11)

    createEdge(7, 8, 7)
    createEdge(7, 6, 1)

    createEdge(8, 2, 2)
    createEdge(8, 6, 6)

    createEdge(2, 3, 7)
    createEdge(2, 5, 4)
    createEdge(6, 5, 2)

    createEdge(3, 5, 14)
    createEdge(3, 4, 9)
    createEdge(5, 4, 10)
  }

  def printAdjacencyList() = {
    0.until(list.size).foreach(i => {
      print(i + ": ")
      if(list(i).nonEmpty) println(list(i).map(String.valueOf).reduce((x, y) => x + "," + y))
      else println()
    })
    println()
  }

  def createEdge(x: Int, y: Int, weight: Int) = {
    list = list.updated(x, list(x) ::: List(y)).updated(y, list(y) ::: List(x))
    edges = edges ::: List(new Edge(x, y, weight))
  }

  def representEdges: List[Edge] = edges

  def representVertex: List[List[Int]] = list

  private def makeList(list: List[List[Int]], size: Int): List[List[Int]] = if (size > 0) makeList(list ++: List(List()), size - 1) else list
}

class Edge(val x: Int, val y: Int, val weight: Int){
  override def toString: String = x + " <- " + weight + " -> " + y
}