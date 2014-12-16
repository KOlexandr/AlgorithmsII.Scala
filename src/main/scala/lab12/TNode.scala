package lab12

class Tree(val root: TNode) {
  def search(key: Int): TNode = search(root, key)

  def insert(z: TNode): Tree = new Tree(insert(root, z))

  def delete(z: TNode): Tree = new Tree(delete(root, z))

  private def insert(root: TNode, z: TNode): TNode = {
    var y: TNode = null
    var x: TNode = root

    while (x != null) {
      y = x
      if (z.key < x.key) {
        x = x.left
      } else {
        x = x.right
      }
    }

    z.parent = y
    if (null == y) {
      return z
    } else if (z.key < y.key) {
      y.left = z
    } else {
      y.right = z
    }
    root
  }

  private def delete(root: TNode, z: TNode): TNode = {
    val y = if (null == z.left || null == z.right) z else z.successor
    val x = if (null != y.left) y.left else y.right

    if (null != x) x.parent = y.parent

    if (y != z) z.key = y.key
    if (null == y.parent) {
      x
    } else {
      if (y == y.parent.left) y.parent.left = x
      else y.parent.right = x
      root
    }
  }

  private def search(x: TNode, key: Int): TNode = {
    if(null == x || key == x.key) x
    else if(key < x.key) search(x.left, key)
    else search(x.right, key)
  }
}

object T {
  def min(x: TNode): TNode = x.min

  def max(x: TNode): TNode = x.max

  def successor(x: TNode): TNode = x.successor

  def predecessor(x: TNode): TNode = x.predecessor
}

class TNode(var parent: TNode, var left: TNode, var right: TNode, var key: Int){

  def min: TNode = if(null != this.left) left.min else this

  def max: TNode = if(null != this.right) right.max else this

  def successor: TNode = if(null != right) right.min else successor(this, parent)

  def predecessor: TNode = if(null != left) left.max else predecessor(this, parent)

  private def successor(x: TNode, y: TNode): TNode = {
    if(null != y && x == y.right) successor(y, y.parent) else y
  }

  private def predecessor(x: TNode, y: TNode): TNode = {
    if(null != y && x == y.left) predecessor(y, y.parent) else y
  }
}