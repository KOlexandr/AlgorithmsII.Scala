package lab2.sets.disjointSet.labProc

import lab2.sets.disjointSet.DisjointSet

class DSLinkedList extends DisjointSet[List[SNode[Int]], SNode[Int]] {

   override protected var set: List[SNode[Int]] = List[SNode[Int]]()

   override def makeSet(x: Int): Unit = {
     set = set ::: List[SNode[Int]](new SNode[Int](new INode[Int](x)))
   }

   override def union(x: Int, y: Int): Unit = {
     val a: SNode[Int] = findSet(x)
     val b: SNode[Int] = findSet(y)
     if(a.head != b.head){
       set = set.filter(el => el.head != a.head && el.head != b.head)
       a.++(b)
       set = set ::: List[SNode[Int]](a)
     }
   }

   override def findSet(x: Int): SNode[Int] = {
     def find(h: List[SNode[Int]]): SNode[Int] = {
       if(h.isEmpty) null
       else if(containsValue(h.head.head)) h.head
       else find(h.tail)
     }
     def containsValue(h: INode[Int]): Boolean = {
       if(h == null) false
       else if(h.value == x) true
       else containsValue(h.next)
     }
     find(set)
   }

   override def size: Int = set.size
 }
