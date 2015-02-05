package semester1st.lab2.sets.disjointSet.labProc

class INode[T](v: T, h: INode[T], n: INode[T]) {
   var value: T = v
   var head: INode[T] = h
   var next: INode[T] = n

   def this(v: T){
     this(v, null, null)
     head = this
   }

   def ++(node: INode[T]) = {
     this.tail.next = node
     changeHead(node, head)
   }

   def setHead(head: INode[T]) = {
     this.head = head
   }

   def changeHead(h: INode[T], head: INode[T]): Unit = {
     if(null != h){
       h.setHead(head)
       changeHead(h.next, head)
     }
   }

  def tail: INode[T] = {
    def it(h: INode[T]): INode[T] = if(null == h.next) h else it(h.next)
    it(head)
  }
 }