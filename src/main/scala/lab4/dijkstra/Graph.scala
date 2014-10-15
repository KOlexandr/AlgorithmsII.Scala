package lab4.dijkstra

class Graph(val vertexCount: Int) {

  private var edges: List[Edge] = List()
  private var list: List[List[Int]] = makeList(List(), vertexCount)

  def buildGraph() = {
    println("Figure 24.6")
    /**
     * s -- 10 -> t;
     * t -- 1 -> x;
     * s -- 5 -> y;
     * t -- 2 -> y;
     * y -- 3 -> t;
     * z -- 6 -> x;
     * x -- 4 -> z;
     * y -- 9 -> x;
     * z -- 7 -> s;
     * y -- 2 -> z;
     */
    println("   -> t ----> x")     //   -> 1 ----> 2
    println(" /    ^     % ^")     //  /   ^     % ^
    println("s     |   /   |")     // 0    |   /   |
    println("^\\    v /    v")     // ^\   v /     v
    println("|  -> y ----> z")     // | -> 4 ----> 3
    println("-<----------<--")     // -<---------<--

    createEdge(0, 1, 10)
    createEdge(1, 2, 1)
    createEdge(0, 4, 5)
    createEdge(1, 4, 2)
    createEdge(4, 1, 3)
    createEdge(2, 3, 4)
    createEdge(3, 2, 6)
    createEdge(4, 2, 9)
    createEdge(3, 0, 7)
    createEdge(4, 3, 2)
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
  override def toString: String = x + " -- " + weight + " -> " + y
}