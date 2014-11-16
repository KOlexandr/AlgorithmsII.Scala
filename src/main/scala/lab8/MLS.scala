package lab8

object MLS {
  /**
    *
    * @param x - x coordinates of each point
    * @param y - y coordinates of each point
    * @param k - number of unknown parameters (power of polynomial)
    * @return - array of coefficient of polynomial
    */
  def lsm(x: Array[Double], y: Array[Double], k: Int): Array[Double] ={
    val n: Int = x.length
    val xs: Array[Double] = Array.fill(2*k+1)(0)
    val xys: Array[Double] = Array.fill(k+1)(0)
    val matrix: Array[Array[Double]] = Array.ofDim(k+1, k+1)
    val vector: Array[Double] = Array.ofDim(k+1)

    xs(0) = n
    for(j <- 2*k to 1 by -1; i <- 0 until n){
      xs(j) = xs(j) + math.pow(x(i), j)
    }
    for(j <- k to 0 by -1; i <- 0 until n) {
      xys(j) = xys(j) + y(i) * math.pow(x(i), j)
    }
    for(j <- 0 to k; i <- 0 to k) {
      matrix(j)(i) = xs(2 * k - i - j)
    }
    for(i <- k to 0 by -1) {
      vector(k - i) = xys(i)
    }
    Gauss.gauss(matrix, vector)
  }
}
