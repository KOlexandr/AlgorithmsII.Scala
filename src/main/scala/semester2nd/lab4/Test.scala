package semester2nd.lab4

import java.math.{MathContext, RoundingMode}

object Test {
  def main(args: Array[String]) {
    test()
  }

  private def test(): Unit = {
    val in = Array(
      "" -> bd("1"),
      "aaaaaaaaaaaa" -> bd("1"),
      "arithmetic" -> bd("0.862298032"),
      "encoding" -> bd("0.2654206752777099609375"),
      "hello world" -> bd("0.975472501797722825013051004597974661025864"),
      "code - decode" -> bd("0.7399580600289731935699612737027311636991039"),
      "better then huffmannn" -> bd("0.821229636859791585265784556865678891736343045564847747")
    )

    println("Arithmetic Encoding Test:")
    in.foreach(str => println(str._1 + " = " + ArithmeticEncoding.encode(str._1, ArithmeticEncoding.makeSegment(str._1))))
    println("Arithmetic Decoding Test:")
    in.foreach(str => println(str._2 + " = " + ArithmeticEncoding.decode(str._2, ArithmeticEncoding.makeSegment(str._1), str._1.size)))
  }

  private def bd(str: String): BigDecimal = BigDecimal(str, new MathContext(64, RoundingMode.HALF_EVEN))
}
