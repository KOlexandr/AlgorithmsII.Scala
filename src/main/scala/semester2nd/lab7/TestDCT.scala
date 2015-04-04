package semester2nd.lab7

import java.awt.image.BufferedImage
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

object TestDCT {
  def main(args: Array[String]) {
//    testDCTArray()
    testDCT("src/main/resources/", "lena.bmp")
  }

  def testDCT(path: String, name: String): Unit = {
    val dct: BufferedImage = DCT.dct(readImage(path + name))
    saveImage(path + name + "_DCT_.jpg", "jpg", dct)

    val idct: BufferedImage = DCT.idct(dct)
    saveImage(path + name + "_IDCT_.jpg", "jpg", idct)
  }

  def testDCTArray(): Unit = {
    val dct = DCT.dct(Array(
      Array(1D, 1, 1, 1, 9, 9, 9, 9),
      Array(2D, 2, 2, 2, 8, 8, 8, 8),
      Array(3D, 3, 3, 3, 7, 7, 7, 7),
      Array(5D, 5, 5, 5, 5, 5, 5, 5),
      Array(4D, 4, 4, 4, 6, 6, 6, 6),
      Array(6D, 6, 6, 6, 4, 4, 4, 4),
      Array(7D, 7, 7, 7, 3, 3, 3, 3),
      Array(8D, 8, 8, 8, 2, 2, 2, 2)
    ))
    println(RGBImage.toInt(dct).map(q => q.mkString(" ")).mkString("\n") + "\n")

    val idct = DCT.idct(dct)
    println(RGBImage.toInt(idct).map(q => q.mkString(" ")).mkString("\n"))
  }

  def readImage(path: String): BufferedImage = ImageIO.read(Files.newInputStream(Paths.get(path)))

  def saveImage(path: String, format: String, image: BufferedImage): Unit =
    ImageIO.write(image, format, Files.newOutputStream(Paths.get(path)))
}
