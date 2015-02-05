package semester1st.lab1.graph

class MatrixGraph(vCount: Int, build: (Graph[Array[Array[Int]]]) => Unit) extends Graph[Array[Array[Int]]](vCount) {

  private val array = Array.ofDim[Int](vCount, vCount)

  override def buildGraph() = build(this)

  override def printAdjacencyList(): Unit = {
    List.range(0, vertexCount).foreach(i => println(array(i).map(String.valueOf).reduce((x, y) => x + " " + y)))
    println()
  }

  override def createEdge(x: Int, y: Int): Unit = {
    array(x)(y) = 1
  }

  override def represent(): Array[Array[Int]] = array
}