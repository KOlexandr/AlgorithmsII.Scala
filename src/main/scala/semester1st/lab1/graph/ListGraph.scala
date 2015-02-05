package semester1st.lab1.graph

class ListGraph(vCount: Int, build: (Graph[List[List[Int]]]) => Unit) extends Graph[List[List[Int]]](vCount) {

  private var list: List[List[Int]] = makeList(List(), vCount)

  override def buildGraph() = build(this)

  override def printAdjacencyList(): Unit = {
    List.range(0, list.size).foreach(i => {
      print(i + ": ")
      if(list(i).nonEmpty) println(list(i).map(String.valueOf).reduce((x, y) => x + "," + y))
      else println()
    })
    println()
  }

  override def createEdge(x: Int, y: Int): Unit = {
    list = list.updated(x, list(x) ::: List(y))
  }

  override def represent(): List[List[Int]] = list
}