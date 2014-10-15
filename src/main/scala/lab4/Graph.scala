package lab4

class Graph(val vertexCount: Int) {

  private var edges: List[Edge] = List()
  private var list: List[List[Int]] = makeList(List(), vertexCount)

  def buildGraph() = {
    println("Figure 24.4")
      /**
       * s -- 6 -> t;
       * t -- 5 -> x;
       * x -- -2 -> t;
       * s -- 7 -> y;
       * t -- 8 -> y;
       * y -- 9 -> z;
       * z -- 7 -> x;
       * z -- 2 -> s;
       * y -- -3 -> x;
       * t -- -4 -> z;
       */
    println("   -> t <---> x")     //   -> 1 <---> 2
    println(" /    | \\   % ^")    //  /   | \   % ^
    println("s     |   \\   |")    // 0    |   \   |
    println("^\\    v /   % |")   // ^\   v /   % |
    println("|  -> y ----> z")     // | -> 4 ----> 3
    println("-<----------<--")     // -<---------<--

    createEdge(0, 1, 6)
    createEdge(1, 2, 5)
    createEdge(2, 1, -2)
    createEdge(0, 4, 7)
    createEdge(1, 4, 8)
    createEdge(4, 3, 9)
    createEdge(3, 2, 7)
    createEdge(3, 0, 2)
    createEdge(4, 2, -3)
    createEdge(1, 3, -4)
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