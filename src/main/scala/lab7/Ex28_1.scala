package lab7

object Ex28_1 {
  def main(args: Array[String]) {
    val a: List[List[Double]] = List(
      List(2, -1, 0, 0, 0),
      List(-1, 2, -1, 0, 0),
      List(0, -1, 2, -1, 0),
      List(0, 0, -1, 2, -1),
      List(0, 0, 0, -1, 2)
    )
    val l: List[List[Double]] = List(
      List(1, 0, 0, 0, 0),
      List(-0.5, 1, 0, 0, 0),
      List(0, -0.6667, 1, 0, 0),
      List(0, 0, -0.75, 1, 0),
      List(0, 0, 0, -0.8, 1)
    )
    val u: List[List[Double]] = List(
      List(2, -1, 0, 0, 0),
      List(0, 1.5, -1, 0, 0),
      List(0, 0, 1.333, -1, 0),
      List(0, 0, 0, 1.25, -1),
      List(0, 0, 0, 0, 1.2)
    )
    val b: List[Double] = List(1, 1, 1, 1, 1)
    val x: List[Double] = List(2.5, 4, 4.5, 4, 2.5)

    println("Exercise 28-1, page 865")
    println("Input data:")
    println("A = \n[" + a.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("b = \n[" + b.mkString(", ") + "]\n")

    println("Data for verification:")
    println("L = \n[" + l.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("U = \n[" + u.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("x = \n[" + x.mkString(", ") + "]\n")

    println("Solving:")
    val (lS, uS): (Array[Array[Double]], Array[Array[Double]]) = LU.luDecomposition(a.map(_.toArray).toArray)
    println("l = \n[" + lS.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("u = \n[" + uS.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")

    val xLU: Array[Double] = LU.solveWithLU(a.map(_.toArray).toArray, b.toArray)
    println("Solved with LU = \n[" + xLU.mkString(", ") + "]\n")

    val xLUP: Array[Double] = LU.solveWithLUP(a.map(_.toArray).toArray, b.toArray)
    println("Solved with LUP = \n[" + xLUP.mkString(", ") + "]\n")
  }
}
