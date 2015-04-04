package semester2nd.lab7

import java.awt.image.BufferedImage
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

object TestDCT {
  def main(args: Array[String]) {
    testDCT("src/main/resources/", "lena.bmp")
  }

  def testDCT(path: String, name: String): Unit = {
    val dct = DCT.dct(Array(
      Array(63, 63, 63, 63, 63, 63, 63, 63),
      Array(63, 63, 63, 63, 63, 63, 63, 63),
      Array(63, 63, 255, 255, 255, 255, 63, 63),
      Array(63, 63, 255, 255, 255, 255, 63, 63),
      Array(63, 63, 255, 255, 255, 255, 63, 63),
      Array(63, 63, 255, 255, 255, 255, 63, 63),
      Array(63, 63, 63, 63, 63, 63, 63, 63),
      Array(63, 63, 63, 63, 63, 63, 63, 63)
    ))
    println(dct.map(q => q.mkString(" ")).mkString("\n") + "\n")

    val idct = DCT.idct(dct)
    println(idct.map(q => q.mkString(" ")).mkString("\n"))
  }

  def readImage(path: String): BufferedImage = ImageIO.read(Files.newInputStream(Paths.get(path)))
  def saveImage(path: String, format: String, image: BufferedImage): Unit =
    ImageIO.write(image, format, Files.newOutputStream(Paths.get(path)))
}
