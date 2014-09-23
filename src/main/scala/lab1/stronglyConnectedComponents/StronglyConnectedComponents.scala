package lab1.stronglyConnectedComponents

import lab1.graph.Graph

abstract class StronglyConnectedComponents[T](g: Graph[T]) {

  protected def GRAY: Int = 1
  protected def WHITE: Int = 0
  protected def BLACK: Int = 2
  protected def NIL: Int = Integer.MIN_VALUE

  protected var time: Int = 0
  protected var graph: Graph[T] = g
  protected var color: Array[Int] = Array.ofDim(graph.vertexCount)
  protected var d: Array[Int] = Array.ofDim(graph.vertexCount)
  protected var pi: Array[Int] = Array.ofDim(graph.vertexCount)
  protected var f: Array[Int] = Array.ofDim(graph.vertexCount)

  protected def dfsVisit(g: T, u: Int): Unit

  def dfs(g: T): Unit = {
    dfs(g, List.range(0, graph.vertexCount))
  }

  def dfs(g: T, list: List[Int]): Unit = {
    list.foreach(i => {
      color(i) = WHITE
      pi(i) = NIL
    })
    time = 0
    list.foreach(i => {
      if(color(i) == WHITE) {
        dfsVisit(g, i)
        println()
      }
    })
  }

  def find() : Unit

  protected def transpose(g: T): T

  protected def makeList(list: List[List[Int]], size: Int): List[List[Int]] = if (size > 0) makeList(list ++: List(List()), size - 1) else list
}