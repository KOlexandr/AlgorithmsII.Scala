import graph.Graph

package object test {
  def VERTEX_COUNT: Int = 6

  def buildDirectedGraph[T](graph: Graph[T]) {
    println("Figure 22.4")
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
    println("Figure 22.7")
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

  def VERTEX_COUNT_SCC: Int = 8

  def buildDirectedGraphSCC[T](graph: Graph[T]) {
    println("Figure 22.9")
    println("a --> b --> c <-> d      0 --> 1 --> 2 <-> 3   ")
    println("^    /|     |     |      ^    /|     |     |   ")
    println("|  /  |     |     |      |  /  |     |     |   ")
    println("|%    v     v     v      |%    v     v     v   ")
    println("e --> f <-> g --> h <-   4 --> 5 <-> 6 --> 7 <-")
    println("                  | __|                    | __|")

    graph.createEdge(0, 1)
    graph.createEdge(1, 4)
    graph.createEdge(1, 5)
    graph.createEdge(1, 2)
    graph.createEdge(2, 6)
    graph.createEdge(2, 3)
    graph.createEdge(3, 2)
    graph.createEdge(3, 7)
    graph.createEdge(4, 0)
    graph.createEdge(4, 5)
    graph.createEdge(5, 6)
    graph.createEdge(6, 5)
    graph.createEdge(6, 7)
    graph.createEdge(7, 7)
  }

  def VERTEX_COUNT_EX22_4_2: Int = 14

  def buildDirectedGraphEx22_4_2[T](graph: Graph[T]) {
    println("Figure 22.8")
    println("0     1     2     3 ")
    println("   4     5     6    ")
    println("7     8     9     10")
    println("   11    12    13   ")

    graph.createEdge(0, 4)
    graph.createEdge(0, 5)
    graph.createEdge(0, 11)

    graph.createEdge(1, 2)
    graph.createEdge(1, 4)
    graph.createEdge(1, 8)

    graph.createEdge(2, 5)
    graph.createEdge(2, 6)
    graph.createEdge(2, 9)

    graph.createEdge(3, 2)
    graph.createEdge(3, 6)
    graph.createEdge(3, 13)

    graph.createEdge(4, 7)

    graph.createEdge(5, 8)
    graph.createEdge(5, 12)

    graph.createEdge(6, 5)

    graph.createEdge(9, 10)

    graph.createEdge(10, 13)

    graph.createEdge(12, 9)
  }
}
