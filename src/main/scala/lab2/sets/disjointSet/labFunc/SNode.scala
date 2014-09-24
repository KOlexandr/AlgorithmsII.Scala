package lab2.sets.disjointSet.labFunc

class SNode[T](h: INode[T], t: INode[T]) {
  val head: INode[T] = h
  val tail: INode[T] = t

  def this(h: INode[T]){
    this(h, h)
  }

  def ++(node: INode[T]): SNode[T] = {
    def re(h: INode[T]): INode[T] = if(null == h.next) h ++ node else h ++ re(h.next)
    new SNode[T](re(head), node)
  }

  def ++(node: SNode[T]): SNode[T] = new SNode[T](this.head ++ node.head)

  override def toString: String = {
    def innerTail(head: INode[T], list: List[T]): List[T] = if(null == head.next) list ::: List[T](head.value) else innerTail(head.next, list ::: List[T](head.value))
    innerTail(this.head, List[T]()).map(String.valueOf).reduce(_ + "," + _)
  }
}