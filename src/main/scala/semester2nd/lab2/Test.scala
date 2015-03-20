package semester2nd.lab2

object Test {

  def main(args: Array[String]) {
    sfTest()
  }

  private def sfTest(): Unit = {
    val in = Array(
      "" -> "", "baa" -> "100", "baba" -> "0101",
      "aaaaaaab" -> "00000001", "aaaaaaabb" -> "000000011",
      "caaaaaaabb" -> "1100000001010", "caaaqaaaabb" -> "101000110000100100",
      "aaaaaaabbbbbbbcccccdddddfffaa" -> "00000000000000010101010101011001001001001001011011011011011111110000"
    )

    print("ShannonFano Encoding Test:")
    in.foreach(str => println(str._1 + " = " + ShannonFano.encode(str._1)))
    print("ShannonFano Decoding Test:")
    in.foreach(str => println(str._2 + " = " + ShannonFano.decode(str._2, ShannonFano.toBitAlphabet(str._1))))
  }
}
