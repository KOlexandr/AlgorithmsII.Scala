package lab8

object Gauss {
  def gauss(a: Array[Array[Double]], b: Array[Double]): Array[Double] = {
    val n: Int = b.length
    val x: Array[Double] = Array.ofDim(n)
    for(i <- 0 until n){
      for(j <- i+1 until n){
        val c: Double = -a(j)(i)/a(i)(i)
        for(l <- i until n){
          a(j)(l) = a(j)(l) + c*a(i)(l)
        }
        b(j) += + c*b(i)
      }
    }
    x(n-1) = b(n-1)/a(n-1)(n-1)
    for(i <- n-2 to 0 by -1){
      for(j <- i+1 until n){
        b(i) -= a(i)(j) * x(j)
      }
      x(i) = b(i)/a(i)(i)
    }
    x
  }
}
