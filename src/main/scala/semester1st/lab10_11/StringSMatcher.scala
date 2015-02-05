package semester1st.lab10_11

/** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
 * Chapter 32. String Matching
 */
object StringSMatcher {

  /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
   * Chapter 32.1. The naive string-matching algorithm
   */
  def naive(src: String, find: String): Int = {
    def search(src: String, find: String, idx: Int): Int = {
      if(src.isEmpty || src.length < find.length) -1
      else if(includes(src, find)) idx
      else search(src.tail, find, idx+1)
    }
    if(find.length > src.length) -1 else search(src, find, 0)
  }

  /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
   * Chapter 32.2. The Rabin-Karp algorithm
   */
  def rabinKarp(src: String, find: String, d: Int, q: Int): Int = {
    val n: Int = src.length
    val m: Int = find.length
    val h: Int = math.pow(d, m - 1).toInt % q

    def prepare(str: String, i: Int, v: Int): Int = if(i == m) v else prepare(str, i+1, (d * v + str(i)) % q)
    def search(i: Int, t: Int, p: Int): Int = {
      if(i > n - m) -1
      else if(p == t && includes(src, find, i)) i
      else if(i == n - m) -1
      else {
        val tmp: Int = (d * (t - src(i) * h) + src(i + m)) % q
        if(tmp < 0) search(i + 1, tmp + q, p)
        else search(i + 1, tmp, p)
      }
    }

    if(find.length > src.length) -1 else search(0, prepare(src, 0, 0), prepare(find, 0, 0))
  }

  private def includes(src: String, find: String): Boolean = {
    if(find.isEmpty) true
    else if(src.isEmpty || src.head != find.head) false
    else includes(src.tail, find.tail)
  }

  private def includes(src: String, find: String, startIdx: Int): Boolean = {
    def inc(i: Int): Boolean = {
      if(i == find.length) true
      else if(src.charAt(i + startIdx) != find.charAt(i)) false
      else inc(i + 1)
    }
    if(src.length < find.length) false else inc(0)
  }

  /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
   * Chapter 32.4. The Knuth-Morris-Pratt algorithm
   */
  def kmp(src: String, find: String): Int = {
    def search(i: Int, q: Int, pi: Array[Int]): Int = {
      if(q == find.length - 1) i - q - 1
      else if(i >= src.length) -1
      else if(q >= 0 && find.charAt(q + 1) != src.charAt(i)) search(i, pi(q), pi)
      else if(find.charAt(q + 1) == src.charAt(i)) search(i + 1, q + 1, pi)
      else search(i + 1, q, pi)
    }
    if(find.length > src.length) -1 else search(0, -1, computePrefixFunction(find))
  }

  private def computePrefixFunction(str: String): Array[Int] = {
    val m: Int = str.length
    def compute(q: Int, k: Int, pi: Array[Int]): Array[Int] = {
      if(q >= m) pi
      else if(k >= 0 && str.charAt(k + 1) != str.charAt(q)) compute(q, pi(k), pi)
      else if(str.charAt(k + 1) == str.charAt(q)) compute(q + 1, k + 1, pi.updated(q, k + 1))
      else compute(q + 1, k, pi.updated(q, k))
    }
    compute(1, -1, Array.ofDim(m).updated(0, -1))
  }

  /** Cormen, Leiserson, Rivest, Stein. Introduction to Algorithms, 2nd Ed.
   * Chapter 32.3. String matching with ﬁnite automata
   */
  def finiteAutomaton(src: String, find: String): Int = {
    val alphabet: Map[Char, Int] = (src concat find).toSet.toList.sorted.zipWithIndex.toMap
    finiteAutomateMatcher(src, computeTransactionFunction(find, alphabet.keys.toList.sorted), find.length, alphabet)
  }

  private def finiteAutomateMatcher(src: String, sigma: Array[Array[Int]], m: Int, alpha: Map[Char, Int]): Int = {
    def inner(i: Int, q: Int, n: Int): Int = {
      if(q == m) i - m
      else if(i == n) -1
      else inner(i + 1, sigma(q)(alpha(src(i))), n)
    }
    inner(0, 0, src.length)
  }

  private def computeTransactionFunction(str: String, alphabet: List[Char]): Array[Array[Int]] = {
    def count(str: String, pqa: String, k: Int): Int = if(isSuffix(str, k, pqa)) k else count(str, pqa, k - 1)
    val m: Int = str.length
    val sigma: Array[Array[Int]] = Array.fill(m + 1, alphabet.size)(0)
    for(q <- 0 to m; i <- 0 until alphabet.size) {
      sigma(q)(i) = count(str, substring(str, 0, q) + alphabet(i), math.min(m + 1, q + 2) - 1)
    }
    sigma
  }

  private def isSuffix(str: String, k: Int, pqa: String): Boolean = {
    def inner(j: Int, m: Int): Boolean = if(j < k && str(j) == pqa(m - k + j)) inner(j + 1, m) else j == k
    inner(0, pqa.length)
  }

  private def substring(str: String, start: Int, end: Int): String =
    if(start == end) "" else str(start) + substring(str, start + 1, end)
}