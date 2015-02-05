package semester1st.project.intervalArithmetic

object Methods {

  def gauss(a: Array[Array[Double]], b: Array[Double]): Array[Double] = {
    val n: Int = a.length
    val x: Array[Double] = Array.ofDim(n)

    0.until(n - 1).foreach(i => {
      (i + 1).until(n).foreach(j => {
        val c: Double = -a(j)(i) / a(i)(i)
        i.until(n).foreach(l => a(j)(l) = a(j)(l) + c * a(i)(l))
        b(j) = b(j) + c * b(i)
      })
    })
    x(n - 1) = b(n - 1) / a(n - 1)(n - 1)
    (n - 2).to(0, -1).foreach(i => {
      (i + 1).until(n).foreach(j => b(i) = b(i) - a(i)(j) * x(j))
      x(i) = b(i) / a(i)(i)
    })
    x
  }

  def gauss(a: Array[Array[Interval]], b: Array[Interval]): Array[Interval] = {
    val n: Int = a.length
    val x: Array[Interval] = Array.ofDim(n)

    0.until(n - 1).foreach(i => {
      (i + 1).until(n).foreach(j => {
        val c: Interval = -a(j)(i) / a(i)(i)
        i.until(n).foreach(l => a(j)(l) = a(j)(l) + (c * a(i)(l)))
        b(j) = b(j) + c * b(i)
      })
    })
    x(n - 1) = b(n - 1) / a(n - 1)(n - 1)
    (n - 2).to(0, -1).foreach(i => {
      (i + 1).until(n).foreach(j => b(i) = b(i) - (a(i)(j) * x(j)))
      x(i) = b(i) / a(i)(i)
    })
    x
  }

  def gaussMedian(a: Array[Array[Interval]], b: Array[Interval]): Array[Double] = {
    val n: Int = a.length
    val x: Array[Double] = Array.ofDim(n)
    val nb: Array[Double] = b.map(i => i.median)
    val na: Array[Array[Double]] = a.map(i => i.map(j => j.median))

    0.until(n - 1).foreach(i => {
      (i + 1).until(n).foreach(j => {
        val c: Double = -na(j)(i) / na(i)(i)
        i.until(n).foreach(l => na(j)(l) = na(j)(l) + (c * na(i)(l)))
        nb(j) = nb(j) + c * nb(i)
      })
    })
    x(n - 1) = nb(n - 1) / na(n - 1)(n - 1)
    (n - 2).to(0, -1).foreach(i => {
      (i + 1).until(n).foreach(j => nb(i) = nb(i) - (na(i)(j) * x(j)))
      x(i) = nb(i) / na(i)(i)
    })
    x
  }

  def newton(a: Double, b: Double, e: Double, f: Double => Double, diff: Double => Double): Double = {
    def inner(x0: Double, x1: Double): Double = {
      if(Math.abs(x1 - x0) < e) x1
      else inner(x1, x1 - f(x1) / diff(x1))
    }
    if(f(a) == 0) a
    else if(f(b) == 0) b
    else inner((a + b) / 2, ((a + b) / 2) - f((a + b) / 2) / diff((a + b) / 2))
  }

  def newtonInterval(a: Interval, b: Interval, e: Double, f: Interval => Interval, diff: Interval => Interval): Interval = {
    def inner(x0: Interval, x1: Interval): Interval = {
      if(math.abs((x1 - x0).median) < e) x1
      else inner(x1, x1 - f(x1) / diff(x1))
    }
    if(f(a) == I(0,0)) a
    else if(f(b) == I(0,0)) b
    else inner((a + b) / I(2,2), ((a + b) / I(2,2)) - f((a + b) / I(2,2)) / diff((a + b) / I(2,2)))
  }
}
