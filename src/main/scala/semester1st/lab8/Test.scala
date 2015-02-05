package semester1st.lab8

object Test {
  def main(args: Array[String]): Unit = {
    lagrangeTest()
  }

  def lagrangeTest(): Unit = {
    def func(x: Double): Double = x * x + x - 2
    val x: Array[Double] = Array(1, 2, 3)
    val y: Array[Double] = x.map(func(_) + Math.random())

    println("Lagrange interpolation:")
    println("Test for function y = x * x + x - 2 + Math.random() [some noise]")
    for(i <- 0 until 10) {
      println("x = " + i + "; realY = " + func(i) + "; lagrangeY = " + Lagrange.solve(i, x, y))
    }
  }

  def gaussTest(): Unit = {
    println("Gauss method for solving Linear Systems of equations:")
    val a: Array[Array[Double]] = Array(Array(2.0, 4, 1), Array(3, 4, 1), Array(5, 0, 8))
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString(";\n") + "]")

    val b: Array[Double] = Array(2, 5, 9)
    println("b = [" + b.mkString(", ") + "]")

    val x: Array[Double] = Gauss.gauss(a, b)
    println("x = [" + x.mkString(", ") + "]\n")
  }

  def lsmTest(): Unit = {
    println("Lowest Squares Method:")
    //y = 2 * x - 5 + random()
    def func(x: Double): Double = 2 * x - 5
    val lsmX: Array[Double] = Array(1,2,3,4,5,6,7,8,9,10)
    val lsmY: Array[Double] = lsmX.map(func(_) + Math.random())

    println("x = [" + lsmX.mkString(", ") + "]")
    println("y = [" + lsmY.mkString(", ") + "]")

    val mlsRes: Array[Double] = MLS.lsm(lsmX, lsmY, 1)
    println("coefficients = [" + mlsRes.mkString(", ") + "]")
  }
}
