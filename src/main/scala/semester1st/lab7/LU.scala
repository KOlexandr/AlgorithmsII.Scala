package semester1st.lab7

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 28.1-2 Matrix Operations
 * Solving systems of linear equations, Inverting Matrices
 */
object LU {

  def lupSolve(l: Array[Array[Double]], u: Array[Array[Double]], pi: Array[Array[Int]], b: Array[Double]): Array[Double] = {
    val n = l.size
    val p: Array[Int] = Array.ofDim(n)
    for(i <- 0 until n){
      p(i) = pi(i).indexWhere(_ == 1)
    }
    lupSolve(l, u, p, b)
  }

  def lupSolve(l: Array[Array[Double]], u: Array[Array[Double]], pi: Array[Int], b: Array[Double]): Array[Double] = {
    val n = l.size

    val x: Array[Double] = Array.ofDim(n)
    val y: Array[Double] = Array.ofDim(n)
    for(i <- 0 until n) {
      y(i) = b(pi(i)) - (for(j <- 0 until i) yield l(i)(j) * y(j)).sum
    }
    for(i <- n-1 to 0 by -1){
      x(i) = (y(i) - (for(j <- i+1 until n) yield u(i)(j) * x(j)).sum)/u(i)(i)
    }
    x
  }

  def luDecomposition(in: Array[Array[Double]]): (Array[Array[Double]], Array[Array[Double]]) = {
    val n: Int = in.size
    val a: Array[Array[Double]] = copy(in)

    val l: Array[Array[Double]] = Array.fill(n, n)(0)
    val u: Array[Array[Double]] = Array.fill(n, n)(0)
    for(i <- 0 until n){
      l(i)(i) = 1
    }

    for(k <- 0 until n){
      u(k)(k) = a(k)(k)
      for(i <- k + 1 until n){
        l(i)(k) = a(i)(k) / u(k)(k)
        u(k)(i) = a(k)(i)
      }
      for(i <- k + 1 until n; j <- k + 1 until n){
        a(i)(j) -= l(i)(k) * u(k)(j)
      }
    }
    (l, u)
  }

  def lupDecomposition(in: Array[Array[Double]]): (Array[Array[Double]], Array[Array[Double]], Array[Int]) = {
    val n: Int = in.size
    val a: Array[Array[Double]] = copy(in)

    val pi: Array[Int] = Array.ofDim(n)
    val l: Array[Array[Double]] = Array.fill(n, n)(0)
    val u: Array[Array[Double]] = Array.fill(n, n)(0)
    for(i <- 0 until n){
      pi(i) = i
    }

    for(k <- 0 until n){
      var p: Double = 0
      var ks: Int = 0
      for(i <- k until n){
        if(math.abs(a(i)(k)) > p){
          p = math.abs(a(i)(k))
          ks = i
        }
      }
      if(p == 0) throw new Error("Degenerate Matrix")
      val tmp: Int = pi(k)
      pi(k) = pi(ks)
      pi(ks) = tmp

      for(i <- 0 until n) {
        val tmp1: Double = a(k)(i)
        a(k)(i) = a(ks)(i)
        a(ks)(i) = tmp1
      }
      for(i <- k+1 until n){
        a(i)(k) /= a(k)(k)
        for(j <- k+1 until n){
          a(i)(j) -= a(i)(k)*a(k)(j)
        }
      }
    }

    for(i <- 0 until n){
      l(i)(i) = 1
    }
    for(i <- 0 until n; j <- 0 until n){
      if(i > j){
        l(i)(j) = a(i)(j)
      } else {
        u(i)(j) = a(i)(j)
      }
    }
    (l, u, pi)
  }

  def solveWithLU(a: Array[Array[Double]], b: Array[Double]): Array[Double] = {
    //L*y = b
    val n: Int = a.size
    val (l, u) = luDecomposition(a)
    val y: Array[Double] = Array.ofDim(n)
    val x: Array[Double] = Array.ofDim(n)

    y(0) = b(0)
    for(i <- 1 until n){
      y(i) = b(i)
      for(j <- 0 until i){
        y(i) -= l(i)(j) * y(j)
      }
    }
    //U * x = y
    x(n-1) = y(n-1)/u(n-1)(n-1)
    for(i <- n-2 to 0 by -1){
      for(j <- i+1 until n){
        y(i) -= u(i)(j) * x(j)
      }
      x(i) = y(i)/u(i)(i)
    }
    x
  }

  def solveWithLUP(a: Array[Array[Double]], b: Array[Double]): Array[Double] = {
    val (l, u, p) = lupDecomposition(a)
    lupSolve(l, u, p, b)
  }

  def inverseMatrix(in: Array[Array[Double]]): Array[Array[Double]] = {
    val size: Int = in.size
    if(in(0).size != size) throw new Error("Matrix should be square")
    val a: Array[Array[Double]] = copy(in)
    val inverse: Array[Array[Double]] = Array.ofDim(size, size)

    for(i <- 0 until size){
      val vec: Array[Double] = (for (j <- 0 until size) yield if(j == i) 1.0 else 0.0).toArray
      val cols: Array[Double] = solveWithLUP(a, vec)
      for (j <- 0 until size) {
        inverse(j)(i) = cols(j)
      }
    }
    inverse
  }

  private def copy(arr: Array[Array[Double]]): Array[Array[Double]] = {
    val nArr: Array[Array[Double]] = Array.ofDim(arr.size, arr(0).size)
    for(i <- 0 until arr.size; j <- 0 until arr(0).size){
      nArr(i)(j) = arr(i)(j)
    }
    nArr
  }
}