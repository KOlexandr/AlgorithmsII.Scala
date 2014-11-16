package lab6

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 28.1-2 Matrix Operations
 * Matrix multiplication by Shtrassen
 */
abstract class ShtrassenMatrix {

  def + (that: ShtrassenMatrix): ShtrassenMatrix = this match {
    case SSimple(a00, a01, a10, a11) => that match {
      case SSimple(ta00, ta01, ta10, ta11) => SSimple(a00 + ta00, a01 + ta01, a10 + ta10, a11 + ta11)
    }
    case SMatrix(a00, a01, a10, a11) => that match {
      case SMatrix(ta00, ta01, ta10, ta11) => SMatrix(a00 + ta00, a01 + ta01, a10 + ta10, a11 + ta11)
    }
  }

  def - (that: ShtrassenMatrix): ShtrassenMatrix = this match {
    case SSimple(a00, a01, a10, a11) => that match {
      case SSimple(ta00, ta01, ta10, ta11) => SSimple(a00 - ta00, a01 - ta01, a10 - ta10, a11 - ta11)
    }
    case SMatrix(a00, a01, a10, a11) => that match {
      case SMatrix(ta00, ta01, ta10, ta11) => SMatrix(a00 - ta00, a01 - ta01, a10 - ta10, a11 - ta11)
    }
  }

  def * (that: ShtrassenMatrix): ShtrassenMatrix = this match {
    case SSimple(a11, a12, a21, a22) => that match {
      case SSimple(b11, b12, b21, b22) =>
        val x1 = (a11 + a22) * (b11 + b22)
        val x2 = (a21 + a22) * b11
        val x3 = a11 * (b12 - b22)
        val x4 = a22 * (b21 - b11)
        val x5 = (a11 + a12) * b22
        val x6 = (a21 - a11) * (b11 + b12)
        val x7 = (a12 - a22) * (b21 + b22)
        SSimple(x1 + x4 - x5 + x7, x3 + x5, x2 + x4, x1 + x3 - x2 + x6)
    }
    case SMatrix(a11, a12, a21, a22) => that match {
      case SMatrix(b11, b12, b21, b22) =>
        val x1 = (a11 + a22) * (b11 + b22)
        val x2 = (a21 + a22) * b11
        val x3 = a11 * (b12 - b22)
        val x4 = a22 * (b21 - b11)
        val x5 = (a11 + a12) * b22
        val x6 = (a21 - a11) * (b11 + b12)
        val x7 = (a12 - a22) * (b21 + b22)
        SMatrix(x1 + x4 + x7 - x5, x3 + x5, x2 + x4, x1 - x2 + x3 + x6)
    }
  }

  def toMatrix: Array[Array[Double]] = this match {
    case SSimple(a00, a01, a10, a11) => Array(Array(a00, a01), Array(a10, a11))
    case SMatrix(a00, a01, a10, a11) =>
      val a: Array[Array[Double]] = a00.toMatrix
      val b: Array[Array[Double]] = a01.toMatrix
      val c: Array[Array[Double]] = a10.toMatrix
      val d: Array[Array[Double]] = a11.toMatrix
      val size = a.size
      val values: Array[Array[Double]] = Array.ofDim(size*2, size*2)

      for {
        i <- 0 until size
        j <- 0 until size
      } {
        values(i)(j) = a(i)(j)
        values(i)(j + size) = b(i)(j)
        values(i + size)(j) = c(i)(j)
        values(i + size)(j + size) = d(i)(j)
      }
      values
  }
}

case class SSimple(a00: Double, a01: Double, a10: Double, a11: Double) extends ShtrassenMatrix

case class SMatrix(a00: ShtrassenMatrix, a01: ShtrassenMatrix, a10: ShtrassenMatrix, a11: ShtrassenMatrix) extends ShtrassenMatrix

object SM {
  def toShtrassen(values: Array[Array[Double]]): ShtrassenMatrix = {
    if(values.size == 2){
      new SSimple(values(0)(0), values(0)(1), values(1)(0), values(1)(1))
    } else {
      val size: Int = values.size / 2
      val a00: Array[Array[Double]] = Array.ofDim(size, size)
      val a01: Array[Array[Double]] = Array.ofDim(size, size)
      val a10: Array[Array[Double]] = Array.ofDim(size, size)
      val a11: Array[Array[Double]] = Array.ofDim(size, size)

      for {
        i <- 0 until size
        j <- 0 until size
      } {
        a00(i)(j) = values(i)(j)
        a01(i)(j) = values(i)(j + size)
        a10(i)(j) = values(i + size)(j)
        a11(i)(j) = values(i + size)(j + size)
      }
      new SMatrix(toShtrassen(a00), toShtrassen(a01), toShtrassen(a10), toShtrassen(a11))
    }
  }

  def multiply(first: Array[Array[Double]], second: Array[Array[Double]]): Array[Array[Double]] = {
    val f: ShtrassenMatrix = toShtrassen(first)
    val s: ShtrassenMatrix = toShtrassen(second)
    (f * s).toMatrix
  }
}
