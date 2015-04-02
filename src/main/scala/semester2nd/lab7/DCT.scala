package semester2nd.lab7

import java.awt.image.BufferedImage

import Jama.Matrix

object DCT {

  def dct(x: Array[Array[Double]]): Array[Array[Double]] = {
    val (rows, columns) = (x.length, x(0).length)
    val a = createArray(math.max(rows, columns))

    val result: Array[Array[Double]] = Array.ofDim(rows, columns)
    for (p: Int <- 0 until rows; q: Int <- 0 until columns) {
        var sum = 0.0
        for (m: Int <- 0 until rows; n: Int <- 0 until columns) {
            sum += x(m)(n) *
              Math.cos((Math.PI * (2*m + 1) * p) / (2.0 * rows)) *
              Math.cos((Math.PI * (2*n + 1) * q) / (2.0 * columns))
        }
        result(p)(q) = a(p) * a(q) * sum
    }
    result
  }

  def dct(image: BufferedImage): BufferedImage = RGBImage(dct(RGBImage.fromBufferedImage(image).content)).toBufferedImage

  def idct(x: Array[Array[Double]]): Array[Array[Double]] = {
    val (rows, columns) = (x.length, x(0).length)
    val a = createArray(math.max(rows, columns))

    val result: Array[Array[Double]] = Array.ofDim(rows, columns)
    for (m: Int <- 0 until rows; n: Int <- 0 until columns) {
      var sum = 0.0
      for (p: Int <- 0 until rows; q: Int <- 0 until columns) {
        sum += a(p) * a(q) * x(p)(q) *
          Math.cos((Math.PI * (2*m + 1) * p) / (2.0 * rows)) *
          Math.cos((Math.PI * (2*n + 1) * q) / (2.0 * columns))
      }
      result(m)(n) = sum
    }
    result
  }

  def idct(image: BufferedImage): BufferedImage = RGBImage(idct(RGBImage.fromBufferedImage(image).content)).toBufferedImage

  def dctOptimized(x: Array[Array[Double]]): Array[Array[Double]] = {
    val size = x.length
    val c: Array[Array[Double]] = Array.ofDim(size, size)
    val dSize: Int = 2 * size
    for (i: Int <- 1 until size; j: Int <- 0 until size) {
      c(i)(j) = 0.5 * math.cos(((2*j + 1) * i * math.Pi) / dSize)
    }
    for (j: Int <- 0 until size) {
      c(0)(j) = 1 / math.sqrt(size)
    }
    val matrix: Matrix = new Matrix(c)
    matrix.times(new Jama.Matrix(x).transpose()).times(matrix.transpose()).getArray
  }

  def dctOptimized(image: BufferedImage): BufferedImage = RGBImage(dctOptimized(RGBImage.fromBufferedImage(image).content)).toBufferedImage

  def idctOptimized(x: Array[Array[Double]]): Array[Array[Double]] = {
    val size = x.length
    val c: Array[Array[Double]] = Array.ofDim(size, size)
    val dSize: Int = 2 * size
    for (i: Int <- 1 until size; j: Int <- 0 until size) {
      c(i)(j) = 0.5 * math.cos(((2*j + 1) * i * math.Pi) / dSize)
    }
    for (j: Int <- 0 until size) {
      c(0)(j) = 1 / math.sqrt(size)
    }
    val matrix: Matrix = new Matrix(c)
    matrix.times(new Jama.Matrix(x).transpose()).times(matrix).getArray
  }

  def idctOptimized(image: BufferedImage): BufferedImage = RGBImage(idctOptimized(RGBImage.fromBufferedImage(image).content)).toBufferedImage

  private def createArray(size: Int): Array[Double] = (List(1.0 / math.sqrt(size)) ::: (for (i: Int <- 1 until size) yield math.sqrt(2.0/size)).toList).toArray
}

class RGBImage(val content: Array[Array[Double]], val width: Int, val height: Int) {
  /**
   * creates an image from arrays with the RGB pixels
   * @return the BufferedImage represented by this object
   */
  def toBufferedImage: BufferedImage = {
    val img: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    img.setRGB(0, 0, width, height, RGBImage.double2intArray(content.flatten), 0, width)
    img
  }

}

object RGBImage {
  def apply(content: Array[Array[Double]]): RGBImage = new RGBImage(content, content.length, content(0).length)
  /**
   * reads the pixels from a BufferedImage
   * @param image the BufferedImage to be getting the pixels from
   * @return a object RGBImage
   */
  def fromBufferedImage(image: BufferedImage): RGBImage = {
    val (width, height) = (image.getWidth, image.getHeight)
    val rgb: Array[Array[Int]] = Array.ofDim(width, height)
    for {i <- 0 until width; j <- 0 until height} {
      rgb(i)(j) = image.getRGB(i, j)
    }
    new RGBImage(int2doubleArray(rgb), width, height)
  }

  def double2intArray(in: Array[Double]): Array[Int] = in.map(_.toInt)
  def int2doubleArray(in: Array[Array[Int]]): Array[Array[Double]] = in.map(row => row.map(_.toDouble))
}