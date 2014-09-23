package lab1.dfs

import lab1.graph.Graph

abstract class DFS[T](g: Graph[T]) {

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

  protected def dfsVisit(u: Int): Unit

  def dfs() : Unit = {
    List.range(0, graph.vertexCount).foreach(i => {
      color(i) = WHITE
      pi(i) = NIL
    })
    time = 0
    List.range(0, graph.vertexCount).foreach(i => {
      if(color(i) == WHITE) {
        dfsVisit(i)
      }
    })
  }
}