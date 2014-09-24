package lab2

import lab2.sets.disjointSet.labFunc.{SNode, INode}
import lab2.sets.disjointSet.{labProc, labFunc}
import lab2.sets.disjointSet.exercise.{ListGraph, Node}
import lab2.sets.disjointSet.optional.ArrayGraph

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

  def find(g: labFunc.ListGraph): Unit = {
    def eachComponents(head: List[SNode[Int]]): Unit = {
      if(head.isEmpty) {
        println()
      } else {
        println("[" + eachComponentVertexes(head.head.head, List()).map(String.valueOf).reduce(_ + ", " + _) + "]")
        eachComponents(head.tail)
      }
    }
    def eachComponentVertexes(head: INode[Int], list: List[Int]): List[Int] = if(null == head) list else eachComponentVertexes(head.next, list ::: List(head.value))

    println("Strongly Connected Components: ")
    eachComponents(g.representVertex.represent)
  }

  def find(g: labProc.ListGraph): Unit = {
    def eachComponents(head: List[labProc.SNode[Int]]): Unit = {
      if(head.isEmpty) {
        println()
      } else {
        println("[" + eachComponentVertexes(head.head.head, List()).map(String.valueOf).reduce(_ + ", " + _) + "]")
        eachComponents(head.tail)
      }
    }
    def eachComponentVertexes(head: labProc.INode[Int], list: List[Int]): List[Int] = if(null == head) list else eachComponentVertexes(head.next, list ::: List(head.value))

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