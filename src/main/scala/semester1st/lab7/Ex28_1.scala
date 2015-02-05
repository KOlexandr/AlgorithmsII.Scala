package semester1st.lab7

object Ex28_1 {
  def main(args: Array[String]) {
    val a: Array[Array[Double]] = Array(
      Array(2, -1, 0, 0, 0),
      Array(-1, 2, -1, 0, 0),
      Array(0, -1, 2, -1, 0),
      Array(0, 0, -1, 2, -1),
      Array(0, 0, 0, -1, 2)
    )
    val l: Array[Array[Double]] = Array(
      Array(1, 0, 0, 0, 0),
      Array(-0.5, 1, 0, 0, 0),
      Array(0, -0.6667, 1, 0, 0),
      Array(0, 0, -0.75, 1, 0),
      Array(0, 0, 0, -0.8, 1)
    )
    val u: Array[Array[Double]] = Array(
      Array(2, -1, 0, 0, 0),
      Array(0, 1.5, -1, 0, 0),
      Array(0, 0, 1.333, -1, 0),
      Array(0, 0, 0, 1.25, -1),
      Array(0, 0, 0, 0, 1.2)
    )
    val inverse: Array[Array[Double]] = Array(
      Array(0.8333,0.6667,0.5000,0.3333,0.1667),
      Array(0.6667,1.3333,1.0000,0.6667,0.3333),
      Array(0.5000,1.0000,1.5000,1.0000,0.5000),
      Array(0.3333,0.6667,1.0000,1.3333,0.6667),
      Array(0.1667,0.3333,0.5000,0.6667,0.8333)
    )
    val b: Array[Double] = Array(1, 1, 1, 1, 1)
    val x: Array[Double] = Array(2.5, 4, 4.5, 4, 2.5)

    println("Exercise 28-1, page 865")
    println("Input data:")
    println("A = \n[" + a.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("b = \n[" + b.mkString(", ") + "]\n")

    println("Data for verification:")
    println("L = \n[" + l.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("U = \n[" + u.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("Inverse Matrix = \n[" + inverse.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("x = \n[" + x.mkString(", ") + "]\n")

    println("Solving:")
    val (lS, uS): (Array[Array[Double]], Array[Array[Double]]) = LU.luDecomposition(a)
    println("l = \n[" + lS.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")
    println("u = \n[" + uS.map("[" + _.mkString(", ") + "]").mkString("\n") + "];\n")

    val xLU: Array[Double] = LU.solveWithLU(a, b)
    println("Solved with LU = \n[" + xLU.mkString(", ") + "]\n")

    val xLUP: Array[Double] = LU.solveWithLUP(a, b)
    println("Solved with LUP = \n[" + xLUP.mkString(", ") + "]\n")

    val inv: Array[Array[Double]] = LU.inverseMatrix(a)
    println("Inverse Matrix with LUP = \n[" + inv.map("[" + _.mkString(", ") + "]").mkString("\n") + "];")
  }
}
