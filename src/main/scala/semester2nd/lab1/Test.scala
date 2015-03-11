package semester2nd.lab1

object Test {

  def main(args: Array[String]) {
//    rleTest()
    sfTest()
  }

  private def rleTest(): Unit = {
    val in = Array(
      "" -> "", "baa" -> "baa", "baba" -> "baba",
      "aaaaaaab" -> "@7ab", "aaaaaaabb" -> "@7abb",
      "caaaaaaabb" -> "c@7abb", "caaaqaaaabb" -> "caaaq@4abb",
      "aaaaaaabbbbbbbcccccdddddfffaa" -> "@7a@7b@5c@5dfffaa"
    )

    print("RLE Encoding Test:")
    in.foreach(str => println(str._1 + " = " + RLE.encode(str._1)))
    print("RLE Decoding Test:")
    in.foreach(str => println(str._2 + " = " + RLE.decode(str._2)))
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
