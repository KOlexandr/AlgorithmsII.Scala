package lab6

object Test {
  def testMultiplicationWithPrint(size: Int){
    val first: Array[Array[Double]] = Array.ofDim(size, size)
    val second: Array[Array[Double]] = Array.ofDim(size, size)

    for {i <- 0 until size; j <- 0 until size} first(i)(j) = i + 1
    for {i <- 0 until size; j <- 0 until size} second(i)(j) = j + 1

    println("A")
    println(matrixToString(first))

    println("B")
    println(matrixToString(second))

    val result: Array[Array[Double]] = SM.multiply(first, second)
    println("Result")
    println(matrixToString(result))
  }

  def matrixToString(matrix: Array[Array[Double]]): String = "[" + matrix.map("[" + _.mkString(",") + "]").mkString("\n") + "]"

  def main(args: Array[String]) {
    testMultiplicationWithPrint(8)
  }
}
