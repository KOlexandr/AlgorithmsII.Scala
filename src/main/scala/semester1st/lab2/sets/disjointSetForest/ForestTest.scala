package semester1st.lab2.sets.disjointSetForest

import semester1st.lab2.sets.disjointSetForest

object ForestTest extends App {
  val g: ListGraph = new ListGraph(10)
  g.buildGraph()
  g.printAdjacencyList()

  println("Array Disjoint-Set Forest")
  disjointSetForest.SCCArray.find(g)

  println("List Disjoint-Set Forest")
  disjointSetForest.SCCList.find(g)
}
