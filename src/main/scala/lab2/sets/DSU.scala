package lab2.sets

trait DSU[T, E] {
  protected var set: T

  def findSet(x: Int): E
  def makeSet(x: Int): Unit
  def union(x: Int, y: Int): Unit

  def size: Int
  def represent: T = set
}