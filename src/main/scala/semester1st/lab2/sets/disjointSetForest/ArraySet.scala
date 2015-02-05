package semester1st.lab2.sets.disjointSetForest

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 21. Data Structures for Disjoint Sets
 * 21.3 Array representation of Disjoint-Set Forest
 */
class ArraySet(vCount: Int) {

  val parent: Array[Int] = Array.ofDim(vCount)
  val rank: Array[Int] = Array.ofDim(vCount)

  def makeSet(x: Int) = {
    parent(x) = x
    rank(x) = 0
  }

  def union(x: Int, y: Int) = link(findSet(x), findSet(y))

  def link(x: Int, y: Int) = {
    if(x != y) {
      if (rank(x) > rank(y)) {
        parent(y) = x
      } else {
        parent(x) = y
      }
      if (rank(x) == rank(y)) rank(y) += 1
    }
  }

  def findSet(x: Int): Int = {
    if(x != parent(x)) parent(x) = findSet(parent(x))
    parent(x)
  }
}

/**
 * StronglyConnectedComponents algorithm with using Disjoint-Set Forest based on array
 */
object SCCArray {

  def find(g: ListGraph): Unit = {
    val set: ArraySet = new ArraySet(g.vertexCount)
    0.until(g.vertexCount).foreach(set.makeSet)
    val represent: List[List[Int]] = g.represent()
    0.until(g.vertexCount).foreach(i => if(represent(i).nonEmpty) represent(i).foreach(v => set.union(i, v)))

    set.parent.distinct.foreach(i => {
      var list: List[Int] = List()
      0.until(g.vertexCount).foreach(v => {
        if (i == set.parent(v)) list = list ::: List(v)
      })
      println(i + ": " + list.sortWith(_ < _).map(String.valueOf).reduce(_ + "," + _))
    })
  }
}