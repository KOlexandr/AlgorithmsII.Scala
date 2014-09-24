package lab2.sets.disjointSetForest

/**
* disjoint set forest union
*/
class DSLinkedList(vCount: Int) {

  val parent: Array[Int] = Array.ofDim(vCount)
  val rank: Array[Int] = Array.ofDim(vCount)

  def makeSet(x: Int) = {
    parent(x) = x
    rank(x) = 0
  }

  def union(x: Int, y: Int) = link(findSet(x), findSet(y))

  def link(x: Int, y: Int) = {
    if(rank(x) > rank(y)){
      parent(y) = x
    } else {
      parent(x) = y
      if(rank(x) == rank(y)) rank(y) = rank(y) + 1
    }
  }

  def findSet(x: Int): Int = {
    if(x != parent(x)) parent(x) = findSet(parent(x))
    parent(x)
  }
}