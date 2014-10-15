package lab4.topoligical

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 22. Elementary Graph Algorithms
 * 22.4 Topological Sort
 * adjacency-list representation
*/
class TopologicalSort(val graph: Graph) {

  private var sorted: List[Int] = List()

  private def GRAY: Int = 1
  private def WHITE: Int = 0
  private def BLACK: Int = 2

  protected var color: Array[Int] = Array.ofDim(graph.vertexCount)

  def sort(): List[Int] = {
    sorted = List()
    List.range(0, graph.vertexCount).foreach(i => color(i) = WHITE)
    List.range(0, graph.vertexCount).foreach(i => if(color(i) == WHITE) dfsVisit(i))
    sorted.reverse
  }

  private def dfsVisit(u: Int): Unit = {
    if(color(u) != BLACK) {
      color(u) = GRAY
      if (null != graph.representVertex(u)) {
        graph.representVertex(u).filter(v => color(v) == WHITE).foreach(v => dfsVisit(v))
      }
      sorted = sorted ::: List(u)
      color(u) = BLACK
    }
  }
}

class Graph(val vertexCount: Int) {

  private var edges: List[Edge] = List()
  private var list: List[List[Int]] = makeList(List(), vertexCount)

  def buildGraph() = {
    println("Figure 24.5")
    /**
     * s -- 6 -> x;
     * x -- 1 -> z;
     * r -- 5 -> s;
     * r -- 3 -> t;
     * t -- 7 -> x;
     * t -- 2 -> z;
     * x -- -1 -> y;
     * y -- -2 -> z;
     * s -- 2 -> t;
     * t -- 4 -> y;
     */
    println("s ---> x ---> z")      //1 ---> 3 ---> 5
    println("^  \\   ^ \\ %  ^")    //^  \   ^ \  % ^
    println("|    % | /  % |")      //|    % | /  % |
    println("r ---> t ---> y")      //0 ---> 2 ---> 4

    createEdge(1, 3, 6)
    createEdge(3, 5, 1)
    createEdge(0, 1, 5)
    createEdge(0, 2, 3)
    createEdge(2, 3, 7)
    createEdge(2, 5, 2)
    createEdge(3, 4, -1)
    createEdge(4, 5, -2)
    createEdge(1, 2, 2)
    createEdge(2, 4, 4)
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
    list = list.updated(x, list(x) ::: List(y))
    edges = edges ::: List(new Edge(x, y, weight))
  }

  def representEdges: List[Edge] = edges

  def representVertex: List[List[Int]] = list

  private def makeList(list: List[List[Int]], size: Int): List[List[Int]] = if (size > 0) makeList(list ++: List(List()), size - 1) else list
}

class Edge(val x: Int, val y: Int, val weight: Int){
  override def toString: String = x + " -> " + weight + " -> " + y
}