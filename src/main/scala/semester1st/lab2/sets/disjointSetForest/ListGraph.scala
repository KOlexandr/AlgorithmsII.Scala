package semester1st.lab2.sets.disjointSetForest

class ListGraph(val vertexCount: Int) {

  private var list: List[List[Int]] = makeList(List(), vertexCount)

  def buildGraph() = {
    println("Figure 21.1")
    println("a --- b     e --- f     h     j     0 --- 1     4 --- 5     7     9")
    println("|    /|     |           |           |    /|     |           |      ")
    println("|  /  |     |           |           |  /  |     |           |      ")
    println("|/    |     |           |           |/    |     |           |      ")
    println("c     d     g           i           2     3     6           8      ")

    createEdge(0, 1)
    createEdge(0, 2)
    createEdge(1, 2)
    createEdge(1, 3)

    createEdge(4, 5)
    createEdge(4, 6)

    createEdge(7, 8)
  }

  def printAdjacencyList(): Unit = {
    0.until(list.size).foreach(i => {
      print(i + ": ")
      if(list(i).nonEmpty) println(list(i).map(String.valueOf).reduce((x, y) => x + "," + y))
      else println()
    })
    println()
  }

  def createEdge(x: Int, y: Int): Unit = {
    list = list.updated(x, list(x) ::: List(y)).updated(y, list(y) ::: List(x))
  }

  def represent(): List[List[Int]] = list

  private def makeList(list: List[List[Int]], size: Int): List[List[Int]] = if (size > 0) makeList(list ++: List(List()), size - 1) else list
}