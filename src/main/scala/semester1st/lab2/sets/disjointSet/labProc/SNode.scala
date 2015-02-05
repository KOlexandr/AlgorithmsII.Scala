package semester1st.lab2.sets.disjointSet.labProc

class SNode[T](h: INode[T], t: INode[T], s: Int) {
   var head: INode[T] = h
   var tail: INode[T] = t
   var size: Int = s

   def this(h: INode[T], s: Int){
     this(h, h, s)
   }

   def ++(node: SNode[T]) = {
     this.head.++(node.head)
     this.tail = node.head.tail
     this.size += node.size
   }

   override def toString: String = {
     def innerTail(head: INode[T], list: List[T]): List[T] =
       if(null == head.next) list ::: List[T](head.value)
       else innerTail(head.next, list ::: List[T](head.value))
     innerTail(this.head, List[T]()).map(String.valueOf).reduce(_ + "," + _)
   }
 }