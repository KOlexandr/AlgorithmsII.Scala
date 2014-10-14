package project.intervalArithmetic

object Gauss {

  def gauss(a: Array[Array[Double]], b: Array[Double]): Array[Double] = {
    val n: Int = a.length
    val x: Array[Double] = Array.ofDim(n)

    0.until(n-1).foreach(i => {
      (i+1).until(n).foreach(j => {
        val c: Double = -a(j)(i) / a(i)(i)
        i.until(n).foreach(l => a(j)(l) = a(j)(l) + c * a(i)(l))
        b(j) = b(j) + c*b(i)
      })
    })
    x(n-1) = b(n-1) / a(n-1)(n-1)
    (n-2).to(0, -1).foreach(i => {
      (i+1).until(n).foreach(j => b(i) = b(i) - a(i)(j)*x(j))
      x(i) = b(i) / a(i)(i)
    })
    x
  }

  def gauss(a: Array[Array[Interval]], b: Array[Interval]): Array[Interval] = {
    val n: Int = a.length
    val x: Array[Interval] = Array.ofDim(n)

    0.until(n-1).foreach(i => {
      (i+1).until(n).foreach(j => {
        val c: Interval = -a(j)(i) / a(i)(i)
        i.until(n).foreach(l => a(j)(l) = a(j)(l) + (c * a(i)(l)))
        b(j) = b(j) + c*b(i)
      })
    })
    x(n-1) = b(n-1) / a(n-1)(n-1)
    (n-2).to(0, -1).foreach(i => {
      (i+1).until(n).foreach(j => b(i) = b(i) - (a(i)(j)*x(j)))
      x(i) = b(i) / a(i)(i)
    })
    x
  }
}
