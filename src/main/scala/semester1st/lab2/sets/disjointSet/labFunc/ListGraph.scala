package semester1st.lab2.sets.disjointSet.labFunc

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 21. Data Structures for Disjoint Sets
  * 21.2 Linked-list representation of disjoint sets
  * linked list representing
  * i used functional programming principles: all values is immutable
  */
class ListGraph(vCount: Int, build: (ListGraph) => Unit) {

   private var edges: List[(Int, Int)] = List()
   private val vertexes: DSLinkedList = new DSLinkedList()

   def printAdjacencyList(): Unit = {
     0.until(vertexCount).foreach(i => println(i + ": " + vertexes.findSet(i).toString))
     println()
   }

   def createEdge(x: Int, y: Int) = {
     edges = edges ::: List((x, y))
     if(vertexes.findSet(x) != vertexes.findSet(y)) vertexes.union(x, y)
   }

   def vertexCount: Int = vCount

   def representVertex: DSLinkedList = vertexes

   def buildGraph(): Unit = {
     0.until(vCount).foreach(vertexes.makeSet)
     build(this)
   }

   def representEdge: List[(Int, Int)] = edges

   def sameComponent(x: Int, y: Int): Boolean = vertexes.findSet(x).head == vertexes.findSet(y).head
 }