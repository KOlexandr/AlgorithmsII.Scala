package lab8

object Test {
  def main(args: Array[String]) {
    println("Gauss method for solving Linear Systems of equations:")
    val a: Array[Array[Double]] = Array(Array(2.0, 4, 1), Array(3, 4, 1), Array(5, 0, 8))
    println("A = [" + a.map("[" + _.mkString(", ") + "]").mkString(";\n") + "]")

    val b: Array[Double] = Array(2, 5, 9)
    println("b = [" + b.mkString(", ") + "]")

    val x: Array[Double] = Gauss.gauss(a, b)
    println("x = [" + x.mkString(", ") + "]\n")

    println("Lowest Squares Method:")
    //y = 2 * x - 5 + random()
    val lsmX: Array[Double] = Array(1,2,3,4,5,6,7,8,9,10)
    println("x = [" + lsmX.mkString(", ") + "]")
    val lsmY: Array[Double] = Array(-2.3443,-0.9643,1.8491,3.9340,5.6787,7.7577,9.7431,11.3922,13.6555, 15.1712)
    println("y = [" + lsmY.mkString(", ") + "]")
    val mlsRes: Array[Double] = MLS.lsm(lsmX, lsmY, 1)
    println("coefficients = [" + mlsRes.mkString(", ") + "]")
  }
}
