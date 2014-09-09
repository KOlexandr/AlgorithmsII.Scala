import graph.Graph

package object test {
  def VERTEX_COUNT: Int = 6

  def buildDirectedGraph[T](graph: Graph[T]) {
    println("Figure 22.4                    ")
    println("u --> v     w     0 --> 1     2")
    println("|   % |   / |     |   % |   / |")
    println("|  /  |  /  |     |  /  |  /  |")
    println("v /   v %   v     v /   v %   v")
    println("x <-- y     z<    3 <-- 4     5<")
    println("            |_|               |_|\n")

    graph.createEdge(0, 1)
    graph.createEdge(0, 3)
    graph.createEdge(1, 4)
    graph.createEdge(2, 4)
    graph.createEdge(2, 5)
    graph.createEdge(3, 1)
    graph.createEdge(4, 3)
    graph.createEdge(5, 5)
  }

  def VERTEX_COUNT_TOP_SORT: Int = 9

  def buildDirectedGraphTopSort[T](graph: Graph[T]) {
    println("Figure 22.7            ")
    println("UN --> TR --> ST --> JA   W | 0 --> 1 --> 4 --> 7   8")
    println("|     /       ^      ^      | |   /       ^     ^    ")
    println("|   /         |      |      | |  /        |     |    ")
    println("v %           |      |      | v %         |     |    ")
    println("SH <-- SO     TS --> TI     | 3 <-- 2     5 --> 6    ")

    graph.createEdge(0, 1)
    graph.createEdge(0, 3)
    graph.createEdge(1, 3)
    graph.createEdge(2, 3)
    graph.createEdge(1, 4)
    graph.createEdge(4, 7)
    graph.createEdge(5, 4)
    graph.createEdge(5, 6)
    graph.createEdge(6, 7)
  }
}
