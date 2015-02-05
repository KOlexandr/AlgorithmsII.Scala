package semester1st.lab1.stronglyConnectedComponents.exercises

import semester1st.lab1.graph.{ListGraph, Graph}
import semester1st.lab1.topologicalSort.ListTopologicalSort

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
  * Chapter 22. Elementary Graph Algorithms
  * 22.5 Strongly Connected Component. Exercises 5
  * adjacency-list representation
  */
class BuildStronglyComponentsGraph(g: Graph[List[List[Int]]]) {
  protected def GRAY: Int = 1
  protected def WHITE: Int = 0
  protected def BLACK: Int = 2

  protected var graph: Graph[List[List[Int]]] = g
  protected var color: Array[Int] = Array.ofDim(graph.vertexCount)
  protected var mapVertex: Map[Int, String] = _
  protected var newGraph: Graph[List[List[Int]]] = _

  def build(): Unit = {
    val originalDfs: List[Int] = new ListTopologicalSort(graph).sort()
    val stronglyComponents: List[List[Int]] = dfs(transpose(graph.represent()), originalDfs)
    val newGraphRepresenting: List[List[Int]] = stronglyComponents.map(list => {
      var set: Set[Int] = Set()
      list.foreach(item => {
        set = set ++ graph.represent()(item).filter(i => !list.contains(i))
      })
      set = set.map(vertex => {
        var idx: Int = -1
        List.range(0, stronglyComponents.size).foreach(i => {
          if(stronglyComponents(i).contains(vertex)){
            idx = i
          }
        })
        idx
      })
      set.toList
    })

    mapVertex = Map()
    for(i <- List.range(0, stronglyComponents.size)){
      mapVertex = mapVertex + (i -> ("[" + stronglyComponents(i).sorted.map(String.valueOf).reduce((a, b) => a + "," + b) + "]"))
    }

    newGraph = new ListGraph(stronglyComponents.size, graph => {
      for(x <- List.range(0, newGraphRepresenting.size)){
        newGraphRepresenting(x).foreach(y => {graph.createEdge(x, y)})
      }
    })
    newGraph.buildGraph()
  }

  def printGraph(): Unit = {
    List.range(0, newGraph.represent().size).foreach(i => {
      print(mapVertex(i) + ": ")
      if(newGraph.represent()(i).nonEmpty) println(newGraph.represent()(i).map(String.valueOf).reduce((x, y) => x + "," + y))
      else println()
    })
    println()
  }

  private def dfs(g: List[List[Int]], list: List[Int]): List[List[Int]] = {
    var components: List[List[Int]] = List()
    list.foreach(i => { color(i) = WHITE })
    list.foreach(i => {
      if(color(i) == WHITE) {
        components = components ::: List(dfsVisit(g, i, List()))
      }
    })
    components
  }

  private def dfsVisit(g: List[List[Int]], u: Int, stronglyComponents: List[Int]): List[Int] = {
    var sc: List[Int] = stronglyComponents
    if (color(u) != BLACK) {
      color(u) = GRAY
      sc = sc ::: List(u)
      if (null != g(u)) {
        g(u).filter(v => color(v) == WHITE).foreach(v => {
          sc = sc ::: dfsVisit(g, v, stronglyComponents)
        })
      }
      color(u) = BLACK
//      sc = sc ::: List(u)
    }
    sc
  }

  private def transpose(g: List[List[Int]]): List[List[Int]] = {
    var list: List[List[Int]] = makeList(List(), graph.vertexCount)
    List.range(0, graph.vertexCount).foreach(rowId => {
      g(rowId).foreach(vertex => {
        list = list.updated(vertex, list(vertex) ::: List(rowId))
      })
    })
    list
  }

  private def makeList(list: List[List[Int]], size: Int): List[List[Int]] = if (size > 0) makeList(list ++: List(List()), size - 1) else list
}