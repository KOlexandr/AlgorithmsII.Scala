package semester2nd.lab1

object RLE {
  val SPECIAL_CHARACTER: String = "@"
  val COUNT_ENCODED_CHARS: Int = 3

  def encode(str: String): String = {
    def same(src: List[Char], char: Char, count: Int, tmp: String): (List[Char], String) = {
      if(src.isEmpty && count > 3) (src, SPECIAL_CHARACTER + count + char)
      else if(src.isEmpty) (src, tmp)
      else if(src.head == char) same(src.tail, char, count + 1, tmp + src.head)
      else if(count > 3) (src, SPECIAL_CHARACTER + count + char)
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
    inner(str.toList, "")
  }

  def decode(str: String): String = {
    def same(num: Int, char: String, str: String): String = {
      if(num == 0) str
      else same(num-1, char, str + char)
    }

    def inner(src: List[String], newStr: String): String = {
      if(src.isEmpty) newStr
      else if(src.head == SPECIAL_CHARACTER)
        inner(src.slice(COUNT_ENCODED_CHARS, src.size), newStr + same(Integer.parseInt(src(1).toString), src(2), ""))
      else inner(src.tail, newStr + src.head)
    }
    inner(str.split("").toList, "")
  }
}