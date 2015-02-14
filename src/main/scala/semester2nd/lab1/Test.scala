package semester2nd.lab1

object Test {

  def main(args: Array[String]) {
    val in = Array("", "baa", "baba", "aaaaaaab", "aaaaaaabb", "caaaaaaabb", "caaaqaaaabb")
    val out = Array("", "baa", "baba", "@7ab", "@7abb", "c@7abb", "caaaq@4abb")

    print("Encoding Test:")
    in.foreach(str => println(str + " = " + RLE.encode(str)))
    print("Decoding Test:")
    out.foreach(str => println(str + " = " + RLE.decode(str)))
  }
}
