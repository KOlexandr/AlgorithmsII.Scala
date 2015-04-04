package semester2nd.lab7

import java.awt.image.BufferedImage

object DCT {

  def dct(x: Array[Array[Int]]): Array[Array[Int]] = {
    val size = x.length

    val result: Array[Array[Int]] = Array.ofDim(size, size)
    val blockSize: Int = 8
    val blocks: Int = size / blockSize
    for(b1: Int <- 0 until blocks; b2: Int <- 0 until blocks) {
      for (p: Int <- 0 until blockSize; q: Int <- 0 until blockSize) {
        var sum = 0.0
        for (m: Int <- 0 until blockSize; n: Int <- 0 until blockSize) {
          sum += x(b1 * blockSize + m)(b2 * blockSize + n) *
            Math.cos((Math.PI * (2 * m + 1) * p) / (2.0 * blockSize)) *
            Math.cos((Math.PI * (2 * n + 1) * q) / (2.0 * blockSize))
        }
        result(blockSize * b1 + p)(blockSize * b2 + q) = (2.0 / blockSize * f(q) * f(p) * sum).toInt
      }
    }
    result
  }

  def dct(image: BufferedImage): BufferedImage = RGBImage(dct(RGBImage.fromBufferedImage(image).content)).toBufferedImage

  def idct(in: Array[Array[Int]]): Array[Array[Int]] = {
    val size = in.length

    val blockSize: Int = 8
    val blocks: Int = size / blockSize
    val result: Array[Array[Int]] = Array.ofDim(size, size)

    for(x: Int <- 0 until blocks; y: Int <- 0 until blocks) {
        var sum = 0.0
        for (p: Int <- 0 until blockSize; q: Int <- 0 until blockSize) {
          sum += f(p) * f(q) * in(x * blockSize + p)(y * blockSize + q) *
            Math.cos((Math.PI * (2 * x + 1) * p) / (2.0 * blockSize)) *
            Math.cos((Math.PI * (2 * y + 1) * q) / (2.0 * blockSize))
        result(blockSize * x + x)(blockSize * y + y) = sum.toInt
      }
    }
    result
  }

  def idct(image: BufferedImage): BufferedImage = RGBImage(idct(RGBImage.fromBufferedImage(image).content)).toBufferedImage

  private def f(i: Int): Double = if(i == 0) 1.0/math.sqrt(2) else 1
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
  def apply(content: Array[Array[Int]]): RGBImage = new RGBImage(content, content.length, content(0).length)
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
}