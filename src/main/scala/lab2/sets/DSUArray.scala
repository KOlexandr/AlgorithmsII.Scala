package lab2.sets

/**
* disjoint set union (DSU) or Union-Find using array
* @param n - size of set
*/
class DSUArray(n: Int) extends DSU[Array[Int], Int] {

  protected override var set: Array[Int] = Array.range(0, n)

  override def makeSet(x: Int) = {
    set(x) = x
  }

  override def union(x: Int, y: Int) = {
    val a = findSet(x)
    val b = findSet(y)
    if(a != b) set(b) = a
  }

  override def findSet(x: Int): Int = if(set(x) == x) x else findSet(set(x))

  override def size = set.length
}