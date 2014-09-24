package lab2.sets.disjointSet.exercise

import lab2.sets.disjointSet.DisjointSet

/**
* exercise 21.2-5
* disjoint set union (DSU) or Union-Find using LinkedList
*/
class DSLinkedList extends DisjointSet[List[Node[Int]], Node[Int]] {

  override protected var set: List[Node[Int]] = List[Node[Int]]()

  override def makeSet(x: Int) = {
    set = set ::: List[Node[Int]](new Node[Int](x))
  }

  override def union(x: Int, y: Int) = {
    def u(nl: Node[Int], ol: Node[Int]): Node[Int] = if(null == ol) nl else u(nl ++ ol, ol.next)

    val a: Node[Int] = findSet(x)
    val b: Node[Int] = findSet(y)
    if(a.value != b.value) set = set.filter(el => el.value != a.value && el.value != b.value) ::: List[Node[Int]](u(u(new Node[Int](a.value), a.next), b))
  }

  override def findSet(x: Int): Node[Int] = {
    def find(h: List[Node[Int]]): Node[Int] = {
      if(h.isEmpty) null
      else if(containsValue(h.head)) h.head
      else find(h.tail)
    }
    def containsValue(h: Node[Int]): Boolean = {
      if(h == null) false
      else if(h.value == x) true
      else containsValue(h.next)
    }
    find(set)
  }

  override def size = set.size
}