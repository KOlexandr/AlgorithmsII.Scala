package semester2nd.lab3

import semester2nd.lab3.Huffman.{Bit, CodeTree}

object Test {
  def main(args: Array[String]) {
    hTest()
  }

  private def hTest(): Unit = {
    val in = Array(
      "baa" -> List(0, 1, 1),
      "baba" -> List(0, 1, 0, 1),
      "aaaaaaab" -> List(1, 1, 1, 1, 1, 1, 1, 0),
      "aaaaaaabb" -> List(1, 1, 1, 1, 1, 1, 1, 0, 0),
      "caaaaaaabb" -> List(0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1),
      "caaaqaaaabb" -> List(0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 0, 1),
      "aaaaaaabbbbbbbcccccdddddfffaa" -> List(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 0, 0, 1, 0, 0, 1, 1, 1, 1)
    )

    print("Huffman Encoding Test:")
    in.foreach(str => println(str._1 + " = " + Huffman.encode(Huffman.createCodeTree(str._1.toList))(str._1.toList).mkString))
    print("Huffman Decoding Test:")
    in.foreach(str => println(str._2 + " = " + Huffman.decode(Huffman.createCodeTree(str._1.toList), str._2.toList).mkString))
  }
}
