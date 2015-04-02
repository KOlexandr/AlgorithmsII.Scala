package semester2nd.lab7

import java.awt.image.BufferedImage
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

object TestDCT {
  def main(args: Array[String]) {
    testDCT("src/main/resources/", "lena.bmp")
  }

  def testDCT(path: String, name: String): Unit = {
    val dct: BufferedImage = DCT.dctOptimized(readImage(path + name))
    saveImage(path + name + "_DCT_.jpg", "jpg", dct)

    val idct: BufferedImage = DCT.idctOptimized(dct)
    saveImage(path + name + "_IDCT_.jpg", "jpg", idct)
  }

  def readImage(path: String): BufferedImage = ImageIO.read(Files.newInputStream(Paths.get(path)))
  def saveImage(path: String, format: String, image: BufferedImage): Unit = ImageIO.write(image, format, Files.newOutputStream(Paths.get(path)))
}
