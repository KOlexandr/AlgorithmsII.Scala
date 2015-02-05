package semester1st.lab2

import semester1st.lab2.sets.disjointSet.labFunc.{SNode, INode}
import semester1st.lab2.sets.disjointSet.{labProc, labFunc}
import semester1st.lab2.sets.disjointSet.exercise.{ListGraph, Node}
import semester1st.lab2.sets.disjointSet.optional.ArrayGraph

/**
* StronglyConnectedComponents algorithm with using disjoint sets based on linked lists and arrays
*/
object StronglyConnectedComponents {

  /**
   * Exercise 21.2-5
   */
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

    println("Connected Components: ")
    eachComponents(g.representVertex.represent)
  }

  /**
   * ex1 lab in functional style
   */
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

    println("Connected Components: ")
    eachComponents(g.representVertex.represent)
  }

  /**
   * ex1 lab in procedure style
   */
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

    println("Connected Components: ")
    eachComponents(g.representVertex.represent)
  }

  /**
   * 22.1 disjoint set based on array
   */
  def find(g: ArrayGraph): Unit = {
    var set: Set[Int] = Set()
    val represent: Array[Int] = g.representVertex.represent
    represent.foreach(i => set = set + i)
    println("Connected Components: ")
    set.foreach(i => {
      var list: List[Int] = List()
      0.until(represent.size).foreach(v => {
        if(i == represent(v)) list = list ::: List(v)
      })
      println(list.map(String.valueOf).reduce(_ + "," + _))
    })
  }
}