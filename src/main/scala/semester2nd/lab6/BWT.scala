package semester2nd.lab6

object BWT {

  def bwt(src: String): (String, Int) = {
    if(src.isEmpty) ("", 0) else {
      val (variants, _) = src.foldLeft((List[String](), src.tail))((opt, head) => (opt._1 ::: List(opt._2 + head), (opt._2 + head).tail))
      val sorted: List[String] = variants.sorted
      (sorted.map(_.reverse.head).mkString(""), sorted.indexOf(src))
    }
  }

  def ibwt(src: String, idx: Int): String = {
    val lastChars: List[Char] = src.toList
    lastChars.foldLeft(List.fill(src.size)(""))((list, char) => lastChars.zip(list).map(s => s._1 + s._2).sorted)(idx)
  }
}