package semester2nd.lab1

object Test {

  def main(args: Array[String]) {
    rleTest()
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
}
