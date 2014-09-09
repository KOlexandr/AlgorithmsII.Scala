package graph

abstract class Graph[T](vCount: Int) {

   var vertexCount: Int = vCount

   def printAdjacencyList(): Unit
   def represent(): T
   def buildGraph(): Unit
   def createEdge(x: Int, y: Int): Unit

   protected def makeList(list: List[List[Int]], size: Int): List[List[Int]] = if (size > 0) makeList(list ++: List(List()), size - 1) else list
 }