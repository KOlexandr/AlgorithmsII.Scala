package semester2nd.lab8

import java.awt.image.BufferedImage
import java.nio.file.{Paths, Files}
import javax.imageio.ImageIO

object TestDWT {

  def main(args: Array[String]) {
    testDWT("src/main/resources/", "lena.jpg")
  }

  def testDWT(path: String, name: String): Unit = {
    val dct: BufferedImage = WaveletTransform.dwt(readImage(path + name))
    saveImage(path + name + "_DWT_.jpg", "jpg", dct)

    val idct: BufferedImage = WaveletTransform.idwt(dct)
    saveImage(path + name + "_IDWT_.jpg", "jpg", idct)
  }

  def readImage(path: String): BufferedImage = ImageIO.read(Files.newInputStream(Paths.get(path)))

  def saveImage(path: String, format: String, image: BufferedImage): Unit =
    ImageIO.write(image, format, Files.newOutputStream(Paths.get(path)))
}