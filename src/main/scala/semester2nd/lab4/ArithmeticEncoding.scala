package semester2nd.lab4

object ArithmeticEncoding {

  private val EMPTY_CHAR: Char = '○'

  private def probabilities(str: String): List[(Char, BigDecimal)] =
    str.toSet.map((c: Char) => (c, str.count(_ == c) / BigDecimal(str.size))).toList.sortWith(_._2 > _._2)

  private def findChar(code: BigDecimal, segment: List[(Char, CharRange)]): Char =
    if (segment.isEmpty) EMPTY_CHAR
    else if(segment.head._2.left <= code && segment.head._2.right >= code) segment.head._1
    else findChar(code, segment.tail)

  private def newEncodeRange(left: BigDecimal, right: BigDecimal, cb: BigDecimal): BigDecimal = left + (right - left) * cb

  private def newDecodeCode(code: BigDecimal, range: CharRange): BigDecimal = (code - range.left) / (range.right - range.left)

  def makeSegment(src: String): Map[Char, CharRange] = {
    val sortedProbabilities: List[(Char, BigDecimal)] = probabilities(src)
    def inner(list: List[(Char, BigDecimal)], map: Map[Char, CharRange], lower: BigDecimal): Map[Char, CharRange] = {
      if(list.isEmpty) map
      else if(list.tail.isEmpty) inner(list.tail, map + (list.head._1 -> CR(lower, 1)), 1)
      else inner(list.tail, map + (list.head._1 -> CR(lower, lower + list.head._2)), lower + list.head._2)
    }

    inner(sortedProbabilities, Map(), 0)
  }

  def encode(src: String, segment: Map[Char, CharRange]): BigDecimal = {
    def inner(chars: List[Char], left: BigDecimal, right: BigDecimal): BigDecimal = {
      if(chars.isEmpty) right
      else inner(chars.tail, newEncodeRange(left, right, segment(chars.head).left), newEncodeRange(left, right, segment(chars.head).right))
    }
    inner(src.toList, 0, 1)
  }

  def decode(code: BigDecimal, segment: Map[Char, CharRange], size: Int): String = {
    val orderedSegment: List[(Char, CharRange)] = segment.toList.sortWith(_._2.left < _._2.left)

    def inner(code: BigDecimal, str: String): String = {
      val char: Char = findChar(code, orderedSegment)
      if(char == EMPTY_CHAR || str.size == size) str
      else inner(newDecodeCode(code, segment(char)), str + char)
    }

    inner(code, "")
  }
}

class CharRange(val left: BigDecimal, val right: BigDecimal) {
  override def toString: String =  s"$left ... $right"
}

object CR {
  def apply(left: BigDecimal, right: BigDecimal): CharRange = new CharRange(left, right)
}