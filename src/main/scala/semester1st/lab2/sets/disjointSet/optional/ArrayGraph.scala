package semester1st.lab2.sets.disjointSet.optional

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 21. Data Structures for Disjoint Sets
  * 21.1 Disjoint-Set operations
  * array representing
*/
class ArrayGraph(vCount: Int, build: (ArrayGraph) => Unit) {

  private var edges: List[(Int, Int)] = List()
  private val vertexes: DSArray = new DSArray(vCount)

  def printAdjacencyList(): Unit = {
    0.until(vertexCount).foreach(i => println(i + ": " + vertexes.findSet(i).toString))
    println()
  }

  def createEdge(x: Int, y: Int) = {
    edges = edges ::: List((x, y))
    if(vertexes.findSet(x) != vertexes.findSet(y)) vertexes.union(x, y)
  }

  def vertexCount: Int = vCount

  def representVertex: DSArray = vertexes

  def buildGraph(): Unit = build(this)

  def representEdge: List[(Int, Int)] = edges

  def sameComponent(x: Int, y: Int): Boolean = vertexes.findSet(x) == vertexes.findSet(y)
}