package week1

class QuickUnion(n: Int) {
  var id: Array[Int] = Array.range(0, n)

  def root(i: Int): Int = {
    var c = i
    while(c != id(c)) c = id(c)
    c
  }

  def connected(p: Int, q: Int): Boolean = root(p) == root(q)

  def union(p: Int, q: Int): Unit = {
    val i: Int = root(p)
    val j: Int = root(q)
    id(i) = j
  }
}
