package semester2nd.lab5

object LZW {

  def encode(src: String, chars: List[Char]): List[Int] = {
    val initDictionary: Map[String, Int] = chars.map((c: Char) => (c.toString, chars.indexOf(c))).toMap

    val (fullDict, result, rest) = src.foldLeft ((initDictionary, List[Int](), "")) {
      case ((dict, list, acc), nextChar) =>
        if(dict.contains(acc + nextChar)) (dict, list, acc + nextChar)
        else (dict + (acc + nextChar -> dict.size), dict(acc) :: list, nextChar.toString)
    }
    if(rest.isEmpty) result.reverse else (fullDict(rest) :: result).reverse
  }

  def decode(codeList: List[Int], chars: List[Char]): String = {
    val initDictionary: Map[Int, String] = chars.map((c: Char) => (chars.indexOf(c), c.toString)).toMap

    val (_, result, _) = codeList.foldLeft[(Map[Int, String], List[String], Option[(Int, String)])]((initDictionary, List(), None)) {
      case ((dict, list, opt), cc) =>
        dict.get(cc) match {
          case Some(out) =>
            val (newDict, newCode) = opt match {
              case Some((code, prefix)) => (dict + (code -> (prefix + out.head)), code + 1)
              case None => (dict, dict.size)
            }
            (newDict, out :: list, Some(newCode -> out))
          case None =>
            val (code, prefix) = opt.get
            val out = prefix + prefix.head
            (dict + (code -> out), out :: list, Some(code + 1, out))
        }
    }
    result.reverse.mkString("")
  }
}
