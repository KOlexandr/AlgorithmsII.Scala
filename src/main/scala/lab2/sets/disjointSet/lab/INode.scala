package lab2.sets.disjointSet.lab

class INode[T](v: T, h: INode[T], n: INode[T]) {
  val value: T = v
  var head: INode[T] = h
  val next: INode[T] = n

  def this(v: T){
    this(v, null, null)
    head = this
  }

  def ++(node: INode[T]): INode[T] = {
    def add(h: INode[T]): INode[T] = {
      if(null == h)
        new INode[T](node.value)
      else
        new INode[T](h.value, null, add(h.next))
    }
    val iNode: INode[T] = add(this)
    changeHead(iNode, iNode)
    iNode
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
}