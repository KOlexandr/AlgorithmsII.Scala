package week1

object Test extends App {
  def testQuickFind() = {
    val qf = new QuickFind(10)
    qf.union(9,3)
    qf.union(8,5)
    qf.union(0,2)
    qf.union(4,1)
    qf.union(0,5)
    qf.union(7,5)

    println(qf.id.map(String.valueOf).reduce((a,b) => a + " " + b))
  }

  def testQuickUnion() = {
    val qu = new QuickUnion(10)
    qu.union(9,7)
    qu.union(1,2)
    qu.union(7,1)
    qu.union(3,8)
    qu.union(5,8)
    qu.union(0,5)
    qu.union(8,6)
    qu.union(2,8)
    qu.union(9,4)
    println(qu.id.map(String.valueOf).reduce((a,b) => a + " " + b))
  }

//  testQuickUnion()
  testQuickFind()
}
