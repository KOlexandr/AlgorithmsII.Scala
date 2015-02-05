package semester1st.lab8

object Lagrange {

  def solve(value: Double, x: Array[Double], y: Array[Double]):Double = {
    val n: Int = x.size
    var ml: Double = 0
    for(i <- 0 until n) {
      var tmp1: Double = 1
      var tmp2: Double = 1
      for (j <- 0 until n) {
        if (j != i) {
          tmp1 *= value - x(j)
          tmp2 *= x(i) - x(j)
        }
      }
      ml += tmp1 / tmp2 * y(i)
    }
    ml
  }
}