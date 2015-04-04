package semester2nd.lab7

import java.awt.image.BufferedImage

object DCT {
  private val blockSize: Int = 8
  private val denominator: Double = 2.0 * blockSize

  def dct(x: Array[Array[Double]]): Array[Array[Double]] = {
    val (height, width) = (x.length, x(0).length)
    val (blocksHeight, blocksWidth) = (height / blockSize, width / blockSize)

    val result: Array[Array[Double]] = Array.ofDim(height, width)
    for(b1: Int <- 0 until blocksHeight; b2: Int <- 0 until blocksWidth) {
      for (p: Int <- 0 until blockSize; q: Int <- 0 until blockSize) {
        var sum = 0.0
        for (m: Int <- 0 until blockSize; n: Int <- 0 until blockSize) {
          sum += x(b1 * blockSize + m)(b2 * blockSize + n) *
            Math.cos((Math.PI * (2 * m + 1) * p) / denominator) *
            Math.cos((Math.PI * (2 * n + 1) * q) / denominator)
        }
        result(blockSize * b1 + p)(blockSize * b2 + q) = 2.0 / blockSize * c(q) * c(p) * sum
      }
    }
    result
  }

  def dct(image: BufferedImage): BufferedImage = {
    val rgbImage: RGBImage = RGBImage.fromBufferedImage(image)
    val result: Array[Array[Double]] = dct(RGBImage.toDouble(rgbImage.content))
    RGBImage(RGBImage.toInt(result), rgbImage.width, rgbImage.height).toBufferedImage
  }

  def idct(in: Array[Array[Double]]): Array[Array[Double]] = {
    val (height, width) = (in.length, in(0).length)
    val (blocksHeight, blocksWidth) = (height / blockSize, width / blockSize)

    val result: Array[Array[Double]] = Array.ofDim(height, width)
    for(b1: Int <- 0 until blocksHeight; b2: Int <- 0 until blocksWidth) {
      val (p, q) = (b1 * blockSize, b2 * blockSize)
      for (x <- p until p + blockSize; y <- q until q + blockSize) {
        var sum = 0.0
        for (i: Int <- 0 until blockSize; j: Int <- 0 until blockSize) {
          sum += c(i) * c(j) * in(p + i)(q + j) *
            Math.cos((Math.PI * (2 * x + 1) * i) / denominator) *
            Math.cos((Math.PI * (2 * y + 1) * j) / denominator)
        }
        result(idx(b1, x))(idx(b2, y)) = 1.0 / math.sqrt(2.0 * blockSize) * sum
      }
    }
    result
  }

  def idct(image: BufferedImage): BufferedImage = {
    val rgbImage: RGBImage = RGBImage.fromBufferedImage(image)
    val result: Array[Array[Double]] = idct(RGBImage.toDouble(rgbImage.content))
    RGBImage(RGBImage.toInt(result), rgbImage.width, rgbImage.height).toBufferedImage
  }

  private def c(i: Int): Double = if(i == 0) 1.0/math.sqrt(2) else 1.0

  private def idx(b: Int, i: Int): Int = if(b % 2 == 0) i else b * blockSize + ((b + 1) * blockSize) - i - 1
}

class RGBImage(val content: Array[Array[Int]], val width: Int, val height: Int) {
  /**
   * creates an image from arrays with the RGB pixels
   * @return the BufferedImage represented by this object
   */
  def toBufferedImage: BufferedImage = {
    val img: BufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
    for(i <- 0 until width; j <- 0 until height) img.setRGB(i, j, content(i)(j))
    img
  }
}

object RGBImage {
  def apply(content: Array[Array[Int]], width: Int, height: Int): RGBImage = new RGBImage(content, width, height)
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
    new RGBImage(rgb, width, height)
  }

  def toInt(array: Array[Array[Double]]): Array[Array[Int]] = array.map(v => v.map(math.round(_).toInt))

  def toDouble(array: Array[Array[Int]]): Array[Array[Double]] = array.map(v => v.map(_.toDouble))
}