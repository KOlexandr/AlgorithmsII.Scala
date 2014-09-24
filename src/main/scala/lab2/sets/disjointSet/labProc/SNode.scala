package lab2.sets.disjointSet.labProc

class SNode[T](h: INode[T], t: INode[T]) {
   var head: INode[T] = h
   var tail: INode[T] = t

   def this(h: INode[T]){
     this(h, h)
   }

   def ++(node: INode[T]) = {
     this.head.++(node)
     this.tail = node.tail
   }

   def ++(node: SNode[T]) = {
     this.head.++(node.head)
     this.tail = node.head.tail
   }

   override def toString: String = {
     def innerTail(head: INode[T], list: List[T]): List[T] = if(null == head.next) list ::: List[T](head.value) else innerTail(head.next, list ::: List[T](head.value))
     innerTail(this.head, List[T]()).map(String.valueOf).reduce(_ + "," + _)
   }
 }