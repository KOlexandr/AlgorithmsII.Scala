package lab2

import lab2.graph.{ArrayGraph, ListGraph}
import lab2.sets.Node

/**
* StronglyConnectedComponents algorithm with using disjoint sets based on linked lists and arrays
*/
object StronglyConnectedComponents {

  def find(g: ListGraph): Unit = {
    def eachComponents(head: List[Node[Int]]): Unit = {
      if(head.isEmpty) {
        println()
      } else {
        println("[" + eachComponentVertexes(head.head, List()).map(String.valueOf).reduce(_ + ", " + _) + "]")
        eachComponents(head.tail)
      }
    }
    def eachComponentVertexes(head: Node[Int], list: List[Int]): List[Int] = if(null == head) list else eachComponentVertexes(head.next, list ::: List(head.value))

    println("Strongly Connected Components: ")
    eachComponents(g.representVertex.represent)
  }

  def find(g: ArrayGraph): Unit = {
    var set: Set[Int] = Set()
    val represent: Array[Int] = g.representVertex.represent
    represent.foreach(i => set = set + i)
    println("Strongly Connected Components: ")
    set.foreach(i => {
      var list: List[Int] = List()
      0.until(represent.size).foreach(v => {
        if(i == represent(v)) list = list ::: List(v)
      })
      println(list.map(String.valueOf).reduce(_ + "," + _))
    })
  }
}