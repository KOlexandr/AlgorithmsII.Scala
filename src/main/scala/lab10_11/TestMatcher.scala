package lab10_11

object TestMatcher {
  def main(args: Array[String]) {
    val srcLetters = "acaabc"
    val findLetters = "aab"
    println("Test #1: Found = 2")
    println("Scala Naive Matcher: \"" + srcLetters + "\".indexOf(\"" + findLetters + "\") = " + StringSMatcher.naive(srcLetters, findLetters))
    println("Java Naive Matcher: \"" + srcLetters + "\".indexOf(\"" + findLetters + "\") = " + StringJMatcher.naive(srcLetters, findLetters))

    println("\nScala Rabin Karp Matcher: \"" + srcLetters + "\".indexOf(\"" + findLetters + "\") = " + StringSMatcher.rabinKarp(srcLetters, findLetters, 9, 13))
    println("Java Rabin Karp Matcher: \"" + srcLetters + "\".indexOf(\"" + findLetters + "\") = " + StringJMatcher.rabinKarp(srcLetters, findLetters, 9, 13))

    println("\nJava Knuth Morris Pratt Matcher: \"" + srcLetters + "\".indexOf(\"" + findLetters + "\") = " + StringJMatcher.kmp(srcLetters, findLetters))
    println("Scala Knuth Morris Pratt Matcher: \"" + srcLetters + "\".indexOf(\"" + findLetters + "\") = " + StringSMatcher.kmp(srcLetters, findLetters))

    val srcNumbers = "2359023141526739921"
    val findNumbers = "67399"
    println("\nTest #2: Found = 12")
    println("Scala Naive Matcher: \"" + srcNumbers + "\".indexOf(\"" + findNumbers + "\") = " + StringSMatcher.naive(srcNumbers, findNumbers))
    println("Java Naive Matcher: \"" + srcNumbers + "\".indexOf(\"" + findNumbers + "\") = " + StringJMatcher.naive(srcNumbers, findNumbers))

    println("\nScala Rabin Karp Matcher: \"" + srcNumbers + "\".indexOf(\"" + findNumbers + "\") = " + StringSMatcher.rabinKarp(srcNumbers, findNumbers, 9, 13))
    println("Java Rabin Karp Matcher: \"" + srcNumbers + "\".indexOf(\"" + findNumbers + "\") = " + StringJMatcher.rabinKarp(srcNumbers, findNumbers, 9, 13))

    println("\nJava Knuth Morris Pratt Matcher: \"" + srcNumbers + "\".indexOf(\"" + findNumbers + "\") = " + StringJMatcher.kmp(srcNumbers, findNumbers))
    println("Scala Knuth Morris Pratt Matcher: \"" + srcNumbers + "\".indexOf(\"" + findNumbers + "\") = " + StringSMatcher.kmp(srcNumbers, findNumbers))

    val srcNotFound = "23590231415267399218"
    val findNotFound = "8"
    println("\nTest #3: Found")
    println("Scala Naive Matcher: \"" + srcNotFound + "\".indexOf(\"" + findNotFound + "\") = " + StringSMatcher.naive(srcNotFound, findNotFound))
    println("Java Naive Matcher: \"" + srcNotFound + "\".indexOf(\"" + findNotFound + "\") = " + StringJMatcher.naive(srcNotFound, findNotFound))

    println("\nScala Rabin Karp Matcher: \"" + srcNotFound + "\".indexOf(\"" + findNotFound + "\") = " + StringSMatcher.rabinKarp(srcNotFound, findNotFound, 9, 13))
    println("Java Rabin Karp Matcher: \"" + srcNotFound + "\".indexOf(\"" + findNotFound + "\") = " + StringJMatcher.rabinKarp(srcNotFound, findNotFound, 9, 13))

    println("\nJava Knuth Morris Pratt Matcher: \"" + srcNotFound + "\".indexOf(\"" + findNotFound + "\") = " + StringJMatcher.kmp(srcNotFound, findNotFound))
    println("Scala Knuth Morris Pratt Matcher: \"" + srcNotFound + "\".indexOf(\"" + findNotFound + "\") = " + StringSMatcher.kmp(srcNotFound, findNotFound))

    val srcKMP = "bacbababaabcbab"
    val findKMP = "ababababca"
    println("\nTest #4: Not Found")
    println("Scala Naive Matcher: \"" + srcKMP + "\".indexOf(\"" + findKMP + "\") = " + StringSMatcher.naive(srcKMP, findKMP))
    println("Java Naive Matcher: \"" + srcKMP + "\".indexOf(\"" + findKMP + "\") = " + StringJMatcher.naive(srcKMP, findKMP))

    println("\nScala Rabin Karp Matcher: \"" + srcKMP + "\".indexOf(\"" + findKMP + "\") = " + StringSMatcher.rabinKarp(srcKMP, findKMP, 9, 13))
    println("Java Rabin Karp Matcher: \"" + srcKMP + "\".indexOf(\"" + findKMP + "\") = " + StringJMatcher.rabinKarp(srcKMP, findKMP, 9, 13))

    println("\nJava Knuth Morris Pratt Matcher: \"" + srcKMP + "\".indexOf(\"" + findKMP + "\") = " + StringJMatcher.kmp(srcKMP, findKMP))
    println("Scala Knuth Morris Pratt Matcher: \"" + srcKMP + "\".indexOf(\"" + findKMP + "\") = " + StringSMatcher.kmp(srcKMP, findKMP))
  }
}