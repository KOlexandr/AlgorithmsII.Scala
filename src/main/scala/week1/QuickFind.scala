package week1

class QuickFind(n: Int) {
  var id: Array[Int] = Array.range(0, n)

  def connected(p: Int, q: Int): Boolean = id(p) == id(q)

  def union(p: Int, q: Int): Unit = {
    val pid = id(p)
    val qid = id(q)
    List.range(0, id.length).foreach(i => if(id(i) == pid) id(i) = qid)
  }
}
