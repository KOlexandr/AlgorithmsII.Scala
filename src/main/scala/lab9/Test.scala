package lab9

object Test {
  def main(args: Array[String]) {
    val fft: Array[Complex] = FourierTransform.fft(Array[Double](1, 2, 3, 4, 5, 6, 7, 8))
    println("fft: " + fft.mkString(", "))

    val ifft: Array[Complex] = FourierTransform.ifft(Array[Double](1, 2, 3, 4, 5, 6, 7, 8))
    println("ifft: " + ifft.mkString(", "))

    val dft: Array[Complex] = FourierTransform.dft(Array[Double](1, 2, 3, 4, 5, 6, 7, 8))
    println("dft: " + dft.mkString(", "))

    val dftC: Array[Complex] = FourierTransform.dftC(Array[Double](1, 2, 3, 4, 5, 6, 7, 8))
    println("dftC: " + dftC.mkString(", "))

    val idftC: Array[Complex] = FourierTransform.idftC(Array[Double](1, 2, 3, 4, 5, 6, 7, 8))
    println("idftC: " + idftC.mkString(", "))

//    val iterativeFFT: Array[Complex] = FourierTransform.iterativeFFT(Array[Double](1, 2, 3, 4, 5, 6, 7, 8))
//    println("iterativeFFT: " + iterativeFFT.mkString(", "))
  }
}
