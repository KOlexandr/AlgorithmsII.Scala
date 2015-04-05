package semester2nd.lab8

import java.awt.image.BufferedImage

object WaveletTransform {
  
  class DWT(in: Array[Array[Double]], length: Int) {
    val size: Int = length
    val data: Array[Array[Double]] = in.clone()

    private def calc(data: Array[Double], size: Int): Unit = {
      (0 until size).foreach(i => data(i) /= Math.sqrt(size))
      var j = size
      while (j >= 2) {
        step(data, j)
        j /= 2
      }
    }

    private def step(data: Array[Double], size: Int): Unit = {
      val tmp: Array[Double] = Array.ofDim(size)
      for (i <- 0 until size / 2) {
        tmp(i) = (data(2 * i) + data(2 * i + 1)) / math.sqrt(2)
        tmp(size / 2 + i) = (data(2 * i) - data(2 * i + 1)) / math.sqrt(2)
      }
      System.arraycopy(tmp, 0, data, 0, size)
    }

    def apply(): Array[Array[Double]] = {
      (0 until size).foreach(row => calc(data(row), size))
      for (col <- Range(size - 1, -1, -1)) {
        val tmp: Array[Double] = Array.ofDim(size)
        (0 until size).foreach(i => tmp(i) = data(i)(col))
        calc(tmp, size)
        (0 until size).foreach(i => data(i)(col) = tmp(i))
      }
      data
    }
  }

  class IDWT(in: Array[Array[Double]], length: Int) {
    val size: Int = length
    val data: Array[Array[Double]] = in.clone()
    
    def reconstruct(data: Array[Double], size: Int): Unit = {
      var j: Int = 2
      while (j <= size) {
        step(data, j)
        j *= 2
      }
      (0 until size).foreach(i => data(i) *= Math.sqrt(size))
    }

    def step(data: Array[Double], size: Int): Unit = {
      val tmp: Array[Double] = Array.ofDim(size)
      for (i <- 0 until size / 2) {
        tmp(2 * i) = (data(i) + data(size / 2 + i)) / Math.sqrt(2)
        tmp(2 * i + 1) = (data(i) - data(size / 2 + i)) / Math.sqrt(2)
      }
      System.arraycopy(tmp, 0, data, 0, size)
    }

    def apply(): Array[Array[Double]] = {
      for (col <- Range(size - 1, -1, -1)) {
        val tmp: Array[Double] = Array.ofDim(size)
        (0 until size).foreach(i => tmp(i) = data(i)(col))
        reconstruct(tmp, size)
        (0 until size).foreach(i => data(i)(col) = tmp(i))
      }
      (0 until size).foreach(row => reconstruct(data(row), size))
      data
    }
  }
  
  def dwt(in: Array[Array[Double]]): Array[Array[Double]] = new DWT(in, in.length).apply()
  def idwt(in: Array[Array[Double]]): Array[Array[Double]] = new IDWT(in, in.length).apply()

  def dwt(image: BufferedImage): BufferedImage = {
    val rgbImage: RGBImage = RGBImage.fromBufferedImage(image)
    val result: Array[Array[Double]] = WaveletTransform.dwt(RGBImage.toDouble(rgbImage.content))
    RGBImage(RGBImage.toInt(result), rgbImage.width, rgbImage.height).toBufferedImage
  }

  def idwt(image: BufferedImage): BufferedImage = {
    val rgbImage: RGBImage = RGBImage.fromBufferedImage(image)
    val result: Array[Array[Double]] = WaveletTransform.idwt(RGBImage.toDouble(rgbImage.content))
    RGBImage(RGBImage.toInt(result), rgbImage.width, rgbImage.height).toBufferedImage
  }
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