package lab7

object Test {
  def main(args: Array[String]) {
    solveWithLUP()
  }

  def lupSolveTest(): Unit = {
    val a: Array[Array[Double]] = Array(Array(1, 2, 0), Array(3, 4, 4), Array(5, 6, 3))
    val l: Array[Array[Double]] = Array(Array(1, 0, 0), Array(0.2, 1, 0), Array(0.6, 0.5, 1))
    val u: Array[Array[Double]] = Array(Array(5, 6, 3), Array(0, 0.8, -0.6), Array(0, 0, 2.5))
    val p: Array[Array[Int]] = Array(Array(0, 0, 1), Array(1, 0, 0), Array(0, 1, 0))

    println("LUPSolve:")
    println("P * A = L * U")
    println("P = [" + p.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("L = [" + l.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("U = [" + u.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")

    val b: Array[Double] = Array(3, 7, 8)
    val x: Array[Double] = Array(-1.4, 2.2, 0.6)
    val y: Array[Double] = Array(8, 1.4, 1.5)

    println("\nL * y = P * b")
    println("L = [" + l.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("y = [" + y.mkString(", ") + "]")
    println("P = [" + p.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("b = [" + b.mkString(", ") + "]")

    println("\nU * x = y")
    println("U = [" + u.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("y = [" + y.mkString(", ") + "]")
    println("x = [" + x.mkString(", ") + "]")

    val foundX: Array[Double] = LU.lupSolve(l, u, p, b)
    println("\nfound x = [" + foundX.mkString(", ") + "]")
  }

  def testLUDecomposition(): Unit = {
    val a: Array[Array[Double]] = Array(Array(2, 3, 1, 5), Array(6, 13, 5, 19), Array(2, 19, 10, 23), Array(4, 10, 11, 31))
    val l: Array[Array[Double]] = Array(Array(1, 0, 0, 0), Array(3, 1, 0, 0), Array(1, 4, 1, 0), Array(2, 1, 7, 1))
    val u: Array[Array[Double]] = Array(Array(2, 3, 1, 5), Array(0, 4, 2, 4), Array(0, 0, 1, 2), Array(0, 0, 0, 3))

    println("LUDecomposition:")
    println("A = L * U")
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("L = [" + l.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("U = [" + u.map("[" + _.mkString(", ") + "]").mkString("; ") + "]\n")

    val (foundL: Array[Array[Double]], foundU: Array[Array[Double]]) = LU.luDecomposition(a)

    println("found L = [" + foundL.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("found U = [" + foundU.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
  }

  def testLUPDecomposition(): Unit = {
    val a: Array[Array[Double]] = Array(Array(2, 0, 2, 0.6), Array(3, 3, 4, -2), Array(5, 5, 4, 2), Array(-1, -2, 3.4, -1))
    val l: Array[Array[Double]] = Array(Array(1, 0, 0, 0), Array(0.4, 1, 0, 0), Array(-0.2, 0.5, 1, 0), Array(0.6, 0, 0.4, 1))
    val u: Array[Array[Double]] = Array(Array(5, 5, 4, 2), Array(0, -2, 0.4, -0.2), Array(0, 0, 4, -0.5), Array(0, 0, 0, -3))
    val p: Array[Array[Int]] = Array(Array(0, 0, 1, 0), Array(1, 0, 0, 0), Array(0, 0, 0, 1), Array(0, 1, 0, 0))

    println("LUPDecomposition:")
    println("P * A = L * U")
    println("P = [" + p.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("L = [" + l.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("U = [" + u.map("[" + _.mkString(", ") + "]").mkString("; ") + "]\n")

    val (foundL: Array[Array[Double]], foundU: Array[Array[Double]], pi: Array[Int]) = LU.lupDecomposition(a)

    println("found L = [" + foundL.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("found U = [" + foundU.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("found Pi = [" + pi.mkString(", ") + "]")
  }

  def solveWithLU(): Unit = {
    val a: Array[Array[Double]] = Array(Array(1, 2, 0), Array(3, 4, 4), Array(5, 6, 3))
    val b: Array[Double] = Array(3, 7, 8)
    val x: Array[Double] = Array(-1.4, 2.2, 0.6)

    println("Solving system of equations using LUDecomposition:")
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("b = [" + b.mkString(", ") + "]")
    println("x = [" + x.mkString(", ") + "]")

    val foundX: Array[Double] = LU.solveWithLU(a, b)
    println("\nfound x = [" + foundX.mkString(", ") + "]")
  }

  def solveWithLUP(): Unit = {
    val a: Array[Array[Double]] = Array(Array(1, 2, 0), Array(3, 4, 4), Array(5, 6, 3))
    val b: Array[Double] = Array(3, 7, 8)
    val x: Array[Double] = Array(-1.4, 2.2, 0.6)

    println("Solving system of equations using LUPDecomposition:")
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString("; ") + "]")
    println("b = [" + b.mkString(", ") + "]")
    println("x = [" + x.mkString(", ") + "]")

    val foundX: Array[Double] = LU.solveWithLUP(a, b)
    println("\nfound x = [" + foundX.mkString(", ") + "]")
  }
}