package semester2nd.lab9

import java.awt.image.BufferedImage

object FractalTransform {

  val rangeBlockSize: Int = 8
  val domainBlockSize: Int = rangeBlockSize * 2

  def compress(image: BufferedImage): (Array[BestRatio], Int, Int) = compress(RGBImage.fromBufferedImage(image))

  def compress(image: RGBImage): (Array[BestRatio], Int, Int) = (compress(RGBImage.toDouble(image.content)), image.height, image.width)

  def compress(data: Array[Array[Double]]): Array[BestRatio] = rangeBlocks(data).map(findBestRatio(_, domainBlocks(data)))

  def decompress(br: Array[BestRatio], height: Int, width: Int, steps: Int): BufferedImage =
    RGBImage(decompressArray(br, height, width, steps), width, height).toBufferedImage

  def decompressArray(br: Array[BestRatio], height: Int, width: Int, steps: Int): Array[Array[Double]] = {
    def decompressStep(image: Array[Array[Double]], step: Int): Array[Array[Double]] =
      if(step >= steps) image else decompressStep(decompress(br, image, height, width), step + 1)
    decompressStep(Array.fill(height, width)(1), 0)
  }

  def decompress(br: Array[BestRatio], oldImage: Array[Array[Double]], height: Int, width: Int): Array[Array[Double]] = {
    val newImage: Array[Array[Double]] = Array.ofDim(height, width)
    for(block <- rangeBlocks(oldImage)) {
      val bestRatio: BestRatio = find(br, block)
      val nBlock: Block = new Block(defineBlock(oldImage, bestRatio.di, bestRatio.dj, domainBlockSize), block.i, block.j)
      fromBlock(newImage, resize(bestRatio.rotation.rotate(nBlock)) * 0.75 + bestRatio.brightness, rangeBlockSize)
    }
    newImage
  }

  private def find(br: Array[BestRatio], block: Block): BestRatio = br.find(b => b.ri == block.i && b.rj == block.j).get

  private def defineBlocks(image: Array[Array[Double]], blockSize: Int): Array[Block] = {
    (for (i <- 0 until image.length / blockSize; j <- 0 until image.length / blockSize) yield new Block(defineBlock(image, i, j, blockSize), i, j)).toArray
  }

  private def defineBlock(image: Array[Array[Double]], heightNum: Int, widthNum: Int, blockSize: Int): Array[Array[Double]] = {
    val data: Array[Array[Double]] = Array.ofDim(blockSize, blockSize)
    for ((ri, i) <- Range(heightNum * blockSize, (heightNum + 1) * blockSize).zip(Range(0, blockSize))) {
      for ((rj, j) <- Range(widthNum * blockSize, (widthNum + 1) * blockSize).zip(Range(0, blockSize))) {
        data(i)(j) = image(ri)(rj)
      }
    }
    data
  }

  private def fromBlock(image: Array[Array[Double]], block: Block, blockSize: Int) = {
    val data: Array[Array[Double]] = block.data
    for ((ri, i) <- Range(block.i * blockSize, (block.i + 1) * blockSize).zip(Range(0, blockSize))) {
      for ((rj, j) <- Range(block.j * blockSize, (block.j + 1) * blockSize).zip(Range(0, blockSize))) {
        image(ri)(rj) = data(i)(j)
      }
    }
  }

  private def resize(block: Block): Block =
    new Block(reshape(defineBlocks(block.data, 2).map(b => b.flatten.sum / 4), rangeBlockSize, rangeBlockSize), block.i, block.j)

  private def reshape(array: Array[Double], height: Int, width: Int): Array[Array[Double]] = {
    (for (i <- 0 until array.length / width) yield array.slice(i * width, (i + 1) * width)).toArray
  }

  private def imgDistance(b1: Block, b2: Block): Double = b1.flatten.zip(b2.flatten).map(i => (i._1 - i._2) * (i._1 - i._2)).sum

  private def getContrastAndBrightness(d: Block, r: Block): (Double, Double) = {
    val dNorm: Double = 1.0 / (rangeBlockSize * rangeBlockSize) * d.flatten.sum
    val rNorm: Double = 1.0 / (rangeBlockSize * rangeBlockSize) * r.flatten.sum

    val alpha: Double = (d - dNorm).flatten.zip((r - rNorm).flatten).map(v => v._1 * v._2).sum
    val beta: Double = (d - dNorm).flatten.map(math.pow(_, 2)).sum
    (alpha / beta, rNorm - (alpha / beta) * dNorm)
  }

  private def findBestRatio(range: Block, domains: Array[Block]): BestRatio = {
    var minDist: Double = Double.MaxValue
    var bestContrast: Double = Double.MaxValue
    var bestBrightness: Double = Double.MaxValue
    var bestRotation: Rotations = Rotations.CLOCK0
    var i: Int = 0
    var j: Int = 0
    for(domain: Block <- domains.map(resize)) {
      for(rotation <- Rotations.values()) {
        val d: Block = rotation.rotate(domain)
        val (contrast, brightness) = getContrastAndBrightness(d, range)
        val dist: Double = imgDistance(d * contrast + brightness, range)
        if (dist < minDist) {
          minDist = dist
          bestRotation = rotation
          bestContrast = contrast
          bestBrightness = brightness
          i = domain.i
          j = domain.j
        }
      }
    }
    new BestRatio(i, j, bestRotation, bestContrast, bestBrightness, range.i, range.j)
  }

  private def rangeBlocks(image: Array[Array[Double]]): Array[Block] = defineBlocks(image, rangeBlockSize)

  private def domainBlocks(image: Array[Array[Double]]): Array[Block] = defineBlocks(image, domainBlockSize)
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
  def apply(content: Array[Array[Double]], width: Int, height: Int): RGBImage = apply(RGBImage.toInt(content), width, height)
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

class Block(val data: Array[Array[Double]], val i: Int, val j: Int) {
  def apply(i: Int, j: Int): Double = data(i)(j)

  def fliplr: Block = new Block(data.map(_.reverse), i, j)
  
  def * (v: Double): Block = new Block(data.map(l => l.map(_ * v)), i, j)
  def + (v: Double): Block = new Block(data.map(l => l.map(_ + v)), i, j)
  def - (v: Double): Block = new Block(data.map(l => l.map(_ - v)), i, j)
  def flatten: Array[Double] = data.flatten
}

class BestRatio(val di: Int, val dj: Int, val rotation: Rotations, val contrast: Double, val brightness: Double, val ri: Int, val rj: Int)