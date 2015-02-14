package semester2nd.lab1

object ShannonFano {

  private def probabilities(str: String): Map[Char, Int] =
    str.toSet.map((c: Char) => (c, str.count(s => s == c))).toMap

  private def split(alphabet: List[Char], map: Map[Char, Int]): (List[Char], List[Char]) = {
    val halfProb: Int = alphabet.map(map(_)).sum / 2

    def inner(alphabet: List[Char], prob: Int, start: List[Char]): (List[Char], List[Char]) = {
      if(alphabet.isEmpty) (start, alphabet)
      else if(prob + map(alphabet.head) > halfProb) (start ::: List(alphabet.head), alphabet.tail)
      else inner(alphabet.tail, prob + map(alphabet.head), start ::: List(alphabet.head))
    }

    if(alphabet.size == 1) (alphabet, List())
    else if(alphabet.size == 2) (List(alphabet.head), alphabet.tail)
    else inner(alphabet, 0, List())
  }

  private def toBitAlphabet(alphabet: List[Char], probabilities: Map[Char, Int]): Map[Char, String] = {
    def inner(src: List[Char], code: String): Map[Char, String] = {
      if(src.isEmpty) Map()
      else if(src.size == 1) Map(src.head -> code)
      else {
        val tuple: (List[Char], List[Char]) = split(src, probabilities)
        inner(tuple._1, code + "0") ++ inner(tuple._2, code + "1")
      }
    }
    inner(alphabet, "")
  }

  def toBitAlphabet(str: String): Map[Char, String] = {
    val map: Map[Char, Int] = probabilities(str)
    toBitAlphabet(str.toSet.toList.sortWith(map(_) > map(_)), map)
  }

  def encode(src: String): String = {
    val bitAlphabet: Map[Char, String] = toBitAlphabet(src)
    def inner(src: List[Char], encoded: String): String =
      if(src.isEmpty) encoded else inner(src.tail, encoded + bitAlphabet(src.head))

    inner(src.toList, "")
  }

  def decode(src: String, bitAlphabet: Map[Char, String]): String = {
    def getChar(rest: List[Char], size: Int): (Char, Int) = {
      val find: Option[(Char, String)] = bitAlphabet.find(c => c._2 == rest.take(size).mkString)
      if(find.nonEmpty) (find.get._1, size)
      else getChar(rest, size+1)
    }

    def inner(src: List[Char], decoded: String): String = {
      if(src.isEmpty) decoded
      else {
        val (char, size): (Char, Int) = getChar(src, 1)
        inner(src.slice(size, src.size), decoded + char)
      }
    }
    inner(src.toList, "")
  }
}