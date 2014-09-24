package lab2.sets.disjointSet.exercise

class Node[T](v: T, n: Node[T]) {

  val value: T = v
  val next: Node[T] = n

  def this(v: T){
    this(v, null)
  }

  def tail(head: Node[T]): Node[T] = {
    def innerTail(head: Node[T]): Node[T] = if(null == head.next) head else innerTail(head.next)
    if(null == head) null else innerTail(head)
  }

  def ++(node: Node[T]): Node[T] = new Node[T](node.value, new Node[T](this.value, this.next))

  def --(node: Node[T]): Node[T] = {
    def del(h: Node[T], nh: Node[T]): Node[T] = if(null == h) nh else if(h.value == node.value) del(h.next, nh) else del(h.next, nh ++ h)
    if(this.value == node.value) this.next else del(this, this.next)
  }

  def size(head: Node[T]): Int = {
    def innerSize(s: Int, head: Node[T]): Int = if(null == head) s else innerSize(s+1, head.next)
    innerSize(0, head)
  }

  override def toString: String = {
    def innerTail(head: Node[T], list: List[T]): List[T] = if(null == head.next) list ::: List[T](head.value) else innerTail(head.next, list ::: List[T](head.value))
    innerTail(this, List[T]()).map(String.valueOf).reduce(_ + "," + _)
  }
}