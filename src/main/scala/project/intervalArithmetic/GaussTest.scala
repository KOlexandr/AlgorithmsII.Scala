package project.intervalArithmetic

object GaussTest {

  /**
   * x - y = -5
   * 2x + y = -7
   *
   * x = -4; y = 1
   */
  def simple() = {
    println("Simple")
    val a: Array[Array[Double]] = Array(Array(1.0,-1),Array(2.0,1))
    val b: Array[Double] = Array(-5.0,-7)
    val gauss: Array[Double] = Gauss.gauss(a, b)
    println("Simple system")
    println(gauss.map(String.valueOf).reduce(_ + "; " + _))

    val aInterval: Array[Array[Interval]] = Array(
      Array(new Interval(1, 1), new Interval(-1, -1)),
      Array(new Interval(2, 2), new Interval(1, 1))
    )
    val bInterval: Array[Interval] = Array(new Interval(-5, -5), new Interval(-7, -7))
    val gaussInterval: Array[Interval] = Gauss.gauss(aInterval, bInterval)
    println("Same system with Intervals")
    println(gaussInterval.map(String.valueOf).reduce(_ + "; " + _))
  }

  /**
   * 3x + 2y - 5z = -1
   * 2x - y + 3z = 13
   * x + 2y - z = 9
   *
   * x = 3; y = 5; z = 4
   */
  def example() = {
    println("Example")
    val a: Array[Array[Double]] = Array(Array(3.0,2,-5),Array(2.0,-1,3),Array(1.0,2,-1))
    val b: Array[Double] = Array(-1.0,13,9)
    val gauss: Array[Double] = Gauss.gauss(a, b)
    println("Simple system")
    println(gauss.map(String.valueOf).reduce(_ + "; " + _))

    val aInterval: Array[Array[Interval]] = Array(
      Array(new Interval(3, 3), new Interval(2, 2), new Interval(-5, -5)),
      Array(new Interval(2, 2), new Interval(-1, -1), new Interval(3, 3)),
      Array(new Interval(1, 1), new Interval(2, 2), new Interval(-1, -1))
    )
    val bInterval: Array[Interval] = Array(new Interval(-1, -1), new Interval(13, 13), new Interval(9, 9))
    val gaussInterval: Array[Interval] = Gauss.gauss(aInterval, bInterval)
    println("Same system with Intervals")
    println(gaussInterval.map(String.valueOf).reduce(_ + "; " + _))
  }

  /**
   * [3,4]*x + [1,2]*y = [2,4]
   * [0,1]*x + [7,8]*y = [-1,1]
   *
   * x = 0.6231; y = -0.0191 if we will use median function on each step of calculation
   */
  def exampleInterval1() = {
    println("Example Interval #1")
    val aInterval: Array[Array[Interval]] = Array(
      Array(new Interval(3, 4), new Interval(1, 2)),
      Array(new Interval(0, 1), new Interval(7, 8))
    )
    val bInterval: Array[Interval] = Array(new Interval(2, 4), new Interval(-1, 1))
    val gaussInterval: Array[Interval] = Gauss.gauss(aInterval, bInterval)
    println("Same system with Intervals")
    println("Interval results: " + gaussInterval.map(String.valueOf).reduce(_ + "; " + _))
    println("Median of last step: " + gaussInterval.map(v => String.valueOf(v.median)).reduce(_ + "; " + _))
  }

  /**
   * [2,4]*x + [-2,1]*y = [-2,2]
   * [-1,2]*x + [2,4]*y = [-2,2]
   */
  def exampleInterval2() = {
    println("Example Interval #2")
    val aInterval: Array[Array[Interval]] = Array(
      Array(new Interval(2, 4), new Interval(-2, 1)),
      Array(new Interval(-1, 2), new Interval(2, 4))
    )
    val bInterval: Array[Interval] = Array(new Interval(-2, 2), new Interval(-2, 2))
    val gaussInterval: Array[Interval] = Gauss.gauss(aInterval, bInterval)
    println("Same system with Intervals")
    println("Interval results: " + gaussInterval.map(String.valueOf).reduce(_ + "; " + _))
    println("Median of last step: " + gaussInterval.map(v => String.valueOf(v.median)).reduce(_ + "; " + _))
  }

  /**
   * [4,4]*x + [1,3]*y = [5,7]
   * [0,2]*x + [4,4]*y = [4,4]
   */
  def exampleInterval3() = {
    println("Example Interval #3")
    val aInterval: Array[Array[Interval]] = Array(
      Array(new Interval(4, 4), new Interval(1, 3)),
      Array(new Interval(-0, 2), new Interval(4, 4))
    )
    val bInterval: Array[Interval] = Array(new Interval(5, 7), new Interval(4, 4))
    val gaussInterval: Array[Interval] = Gauss.gauss(aInterval, bInterval)
    println("Same system with Intervals")
    println("Interval results: " + gaussInterval.map(String.valueOf).reduce(_ + "; " + _))
    println("Median of last step: " + gaussInterval.map(v => String.valueOf(v.median)).reduce(_ + "; " + _))
  }

  def main(args: Array[String]) {
    simple()
    println()
    example()
    println()

    exampleInterval1()
    println()
    exampleInterval2()
    println()
    exampleInterval3()
  }
}
