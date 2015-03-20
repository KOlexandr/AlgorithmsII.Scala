package semester2nd.lab6

import semester2nd.lab1.RLE
import semester2nd.lab3.Huffman
import semester2nd.lab4.ArithmeticEncoding
import semester2nd.lab5.LZW

object Test {
  def main(args: Array[String]) {
    test()

//    arithmetic()
//    lzw()
//    rle()
//    huffman()
  }

  private def test(): Unit = {
    val in = Array(
      "a" -> ("a", 0),
      "encoding" -> ("nogndeic", 2),
      "aaaaaaaaa" -> ("aaaaaaaaa", 0),
      "scala rules" -> ("alcslau ser", 8),
      "abracadabra" -> ("rdarcaaaabb", 2),
      "code - decode" -> ("e- eeoo dddcc", 3),
      "abacabadabacabae" -> ("edccbbbbaaaaaaaa", 0),
      "better then huffman" -> ("nrmnhtbuft feaet eh", 3),
      "TOBEORNOTTOBEORTOBEORNOT" -> ("OOOBBBRRTTTEEENNOOORTTOO", 20)
    )

    println("BWT Transform Test:")
    in.foreach(str => println(str._1 + " = " + BWT.bwt(str._1)))
    println("BWT Inverse Transform Test:")
    in.foreach(str => println(str._1 + " = " + BWT.ibwt(str._2._1, str._2._2)))
  }

  private def arithmetic(): Unit = {
    val in = Array("aaaabaaaaa", "encoding", "java", "abacabadabacabae", "better then huffman", "sunday the best", "hello")

    println("Arithmetic & BWT Encoding Test:")
    in.foreach(str => {
      val simple: BigDecimal = ArithmeticEncoding.encode(str, ArithmeticEncoding.makeSegment(str))
      val bwt: (String, Int) = BWT.bwt(str)
      val withBWT: BigDecimal = ArithmeticEncoding.encode(bwt._1, ArithmeticEncoding.makeSegment(bwt._1))
      println(str)
      println("\tArithmetic only = " + simple + " [" + simple.toString().size + "]")
      println("\tArithmetic & BWT = " + withBWT + " [" + withBWT.toString().size + "]")
      println("\tdecode = " + BWT.ibwt(ArithmeticEncoding.decode(withBWT, ArithmeticEncoding.makeSegment(bwt._1), bwt._1.size), bwt._2))
    })
  }

  private def lzw(): Unit = {
    val in = Array("aaaabaaaaa", "encoding", "java", "scala rules", "hello world", "abacabadabacabae", "qwertyqwerty")

    println("LZW & BWT Encoding Test:")
    in.foreach(str => {
      val simple: String = LZW.encode(str, str.toSet.toList.sorted).mkString
      val bwt: (String, Int) = BWT.bwt(str)
      val withBWT: String = LZW.encode(bwt._1, str.toSet.toList.sorted).mkString
      println(str)
      println("\tLZW only = " + simple + " [" + simple.size + "]")
      println("\tLZW & BWT = " + withBWT + " [" + withBWT.size + "]")
      println("\tdecode = " + BWT.ibwt(LZW.decode(withBWT.toList.map(c => BigInt(c.toString).toInt), str.toSet.toList.sorted), bwt._2))
    })
  }

  private def rle(): Unit = {
    val in = Array(
      "aaaabaaaaa", "encoding", "java", "scala rules", "hello world",
      "abacabadabacabae", "better then huffman", "sunday the best", "qwertyqwerty"
    )

    println("RLE & BWT Encoding Test:")
    in.foreach(str => {
      val simple: String = RLE.encode(str)
      val bwt: (String, Int) = BWT.bwt(str)
      val withBWT: String = RLE.encode(bwt._1)
      println(str)
      println("\tRLE only = " + simple + " [" + simple.size + "]")
      println("\tRLE & BWT = " + withBWT + " [" + withBWT.size + "]")
      println("\tdecode = " + BWT.ibwt(RLE.decode(bwt._1), bwt._2))
    })
  }

  private def huffman(): Unit = {
    val in = Array(
      "aaaabaaaaa", "encoding", "java", "scala rules", "hello world",
      "abacabadabacabae", "better then huffman", "sunday the best", "qwertyqwerty"
    )

    println("Huffman & BWT Encoding Test:")
    in.foreach(str => {
      val simple: String = Huffman.encode(Huffman.createCodeTree(str.toList))(str.toList).mkString
      val bwt: (String, Int) = BWT.bwt(simple)
      val rleHuffman: String = RLE.encode(bwt._1)
      println(str)
      println("\tHuffman only = " + simple + " [" + simple.size + "]")
      println("\tHuffman & BWT = " + rleHuffman + " [" + rleHuffman.size + "]")
      println("\tdecode = " + Huffman.decode(Huffman.createCodeTree(str.toList), BWT.ibwt(RLE.decode(rleHuffman), bwt._2).toList.map(c => BigInt(c.toString).toInt)).mkString)
    })
  }
}
