package semester2nd.lab9

import java.awt.image.BufferedImage
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

object TestFractal {
  def main(args: Array[String]) {
    test("src/main/resources/", "lena.png")
//    test()
  }

  def test(path: String, name: String): Unit = {
    val image: BufferedImage = readImage(path + name)
    val compress: (Array[BestRatio], Int, Int) = FractalTransform.compress(image)
    val decompress: BufferedImage = FractalTransform.decompress(compress._1, compress._2, compress._3, 1000)
    saveImage(path + name + "_FRACTAL_.jpg", "jpg", decompress)
  }

  def test(): Unit = {
    val array: Array[Array[Double]] = Array(
      Array(1,2,3,4,5,6,7,8,9,0,11,12,13,14,15,16),
      Array(1,2,3,4,5,6,7,8,9,0,11,12,13,14,15,16),
      Array(1,2,3,4,5,6,7,8,9,0,11,12,13,14,15,16),
      Array(1,2,3,4,5,6,7,8,9,0,11,12,13,14,15,16),
      Array(2,3,4,5,6,7,8,9,0,11,12,13,14,15,16,1),
      Array(2,3,4,5,6,7,8,9,0,11,12,13,14,15,16,1),
      Array(2,3,4,5,6,7,8,9,0,11,12,13,14,15,16,1),
      Array(2,3,4,5,6,7,8,9,0,11,12,13,14,15,16,1),
      Array(3,4,5,6,7,8,9,0,11,12,13,14,15,16,1,2),
      Array(3,4,5,6,7,8,9,0,11,12,13,14,15,16,1,2),
      Array(3,4,5,6,7,8,9,0,11,12,13,14,15,16,1,2),
      Array(3,4,5,6,7,8,9,0,11,12,13,14,15,16,1,2),
      Array(5,6,7,8,9,0,11,12,13,14,15,16,1,2,3,4),
      Array(5,6,7,8,9,0,11,12,13,14,15,16,1,2,3,4),
      Array(5,6,7,8,9,0,11,12,13,14,15,16,1,2,3,4),
      Array(5,6,7,8,9,0,11,12,13,14,15,16,1,2,3,4)
    )
    val compress: Array[BestRatio] = FractalTransform.compress(array)
    val decompressArray: Array[Array[Double]] = FractalTransform.decompressArray(compress, 16, 16, 30)
    val i: Int = 0
  }

  def readImage(path: String): BufferedImage = ImageIO.read(Files.newInputStream(Paths.get(path)))

  def saveImage(path: String, format: String, image: BufferedImage): Unit =
    ImageIO.write(image, format, Files.newOutputStream(Paths.get(path)))
}
