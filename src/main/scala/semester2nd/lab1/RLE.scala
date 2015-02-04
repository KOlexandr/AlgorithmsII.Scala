package semester2nd.lab1

object RLE {
  def main(args: Array[String]) {
    println(" = " + encode(""))
    println("baa = " + encode("baa"))
    println("baba = " + encode("baba"))
    println("aaaaaaab = " + encode("aaaaaaab"))
    println("aaaaaaabb = " + encode("aaaaaaabb"))
    println("caaaaaaabb = " + encode("caaaaaaabb"))
    println("caaaqaaaabb = " + encode("caaaqaaaabb"))
  }


  def encode(str: String): String = {
    def same(src: List[Char], char: Char, count: Int, tmp: String): (List[Char], String) = {
      if(src.isEmpty && count > 3) (src, "@" + count + char)
      else if(src.isEmpty) (src, tmp)
      else if(src.head == char) same(src.tail, char, count + 1, tmp + src.head)
      else if(count > 3) (src, "@" + count + char)
      else (src, tmp)
    }

    def inner(src: List[Char], newStr: String): String = {
      if(src.isEmpty) newStr
      else if(src.tail.isEmpty) newStr + src.head
      else if(src.head == src.tail.head) {
        val s: (List[Char], String) = same(src, src.head, 0, "")
        inner(s._1, newStr + s._2)
      }
      else inner(src.tail, newStr + src.head)
    }
    inner(str.toList, "").toString
  }
}