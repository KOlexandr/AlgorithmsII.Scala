package semester1st.lab9

object Test {
  def main(args: Array[String]) {
    val dftArray: Array[Double] = Array[Double](2, 3, 4, 5)
    val idftArray: Array[Complex] = Array[Complex](C(14), C(-2, 2), C(-2), C(-2, -2))

    val fft: Array[Complex] = FourierTransform.fft(dftArray)
    println("\nfft: " + fft.mkString("; "))

    val ifft: Array[Complex] = FourierTransform.ifft(idftArray)
    println("\nifft: " + ifft.mkString("; "))

    val dft: Array[Complex] = FourierTransform.dft(dftArray)
    println("dft: " + dft.mkString("; "))

    val dftC: Array[Complex] = FourierTransform.dftC(dftArray)
    println("\ndftC: " + dftC.mkString("; "))

    val idftC: Array[Complex] = FourierTransform.idftC(idftArray)
    println("\nidftC: " + idftC.mkString("; "))

    val iterativeFFT: Array[Complex] = FourierTransform.iterativeFFT(dftArray)
    println("\niterativeFFT: " + iterativeFFT.mkString("; "))
  }
}