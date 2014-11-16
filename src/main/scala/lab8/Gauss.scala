package lab8

object Gauss {
  def gauss(in: Array[Array[Double]], b: Array[Double]): Array[Double] = {
    val n: Int = b.length
    val x: Array[Double] = Array.ofDim(n)
    val a: Array[Array[Double]] = copy(in)

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

  private def copy(arr: Array[Array[Double]]): Array[Array[Double]] = {
    val nArr: Array[Array[Double]] = Array.ofDim(arr.size, arr(0).size)
    for(i <- 0 until arr.size; j <- 0 until arr(0).size){
      nArr(i)(j) = arr(i)(j)
    }
    nArr
  }
}