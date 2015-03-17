package semester2nd.lab5

object Test {
  def main(args: Array[String]) {
    test()
  }

  private def test(): Unit = {
    val in = Array(
      "" -> List(),
      "a" -> List(0),
      "aaaaaaaaaa" -> List(0, 1, 2, 3),
      "encoding" -> List(2, 5, 0, 6, 1, 4, 5, 3),
      "code - decode" -> List(2, 5, 3, 4, 0, 1, 0, 8, 6, 8),
      "hello world" -> List(3, 2, 4, 4, 5, 0, 7, 5, 6, 4, 1),
      "abacabadabacabae" -> List(0, 1, 0, 2, 5, 0, 3, 9, 8, 6, 4),
      "TOBEORNOTTOBEORTOBEORNOT" -> List(5, 3, 0, 1, 3, 4, 2, 3, 5, 6, 8, 10, 15, 9, 11, 13),
      "better then huffman" -> List(2, 3, 9, 9, 3, 8, 0, 9, 5, 3, 7, 0, 5, 10, 4, 4, 6, 1, 7)
    )

    println("LZW Encoding Test:")
    in.foreach(str => println(str._1 + " = " + LZW.encode(str._1, str._1.toSet.toList.sorted)))
    println("LZW Decoding Test:")
    in.foreach(str => println(str._2 + " = " + LZW.decode(str._2, str._1.toSet.toList.sorted)))
  }
}
