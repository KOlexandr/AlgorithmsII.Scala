package semester1st.lab2.sets.disjointSet.labProc

import semester1st.lab2.sets.disjointSet.DisjointSet

class DSLinkedList(vCount: Int) extends DisjointSet[List[SNode[Int]], SNode[Int]] {

   override protected var set: List[SNode[Int]] = List[SNode[Int]]()
   private val represents: Array[SNode[Int]] = Array.ofDim(vCount)

   override def makeSet(x: Int): Unit = {
     val xs: SNode[Int] = new SNode[Int](new INode[Int](x), 1)
     set = set ::: List[SNode[Int]](xs)
     represents(x) = xs
   }

   override def union(x: Int, y: Int): Unit = {
     val a: SNode[Int] = findSet(x)
     val b: SNode[Int] = findSet(y)
     if(a.head != b.head){
       set = set.filter(el => el.head != a.head && el.head != b.head)
       if(a.size < b.size) a.++(b) else b.++(a)
       represents(x) = a
       represents(y) = a
       set = set ::: List[SNode[Int]](a)
     }
   }

   override def findSet(x: Int): SNode[Int] = represents(x)

   override def size: Int = set.size
 }
