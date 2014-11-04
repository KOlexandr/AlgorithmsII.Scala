package lab5.bitonicSort

object BitonicSort {
  /**
   * calls bitonicSort for array with data and use it sort array
   * @param array - array with data
   */
  def sort(array: Array[Int]) = sorter(array, 0, array.length - 1)

  /**
   * calls sorter for subArrays of current subArray of input array and merge sorted subArrays
   * @param array - array with data
   * @param p - start of subArray
   * @param r - end of subArray
   */
  private def sorter(array: Array[Int], p: Int, r: Int) {
    val q: Int = (p + r) / 2
    if (p < q) {
      sorter(array, p, q)
    }
    if (q + 1 < r) {
      sorter(array, q + 1, r)
    }
    merger(array, p, r)
  }

  /**
   * makes reverse of subArray and calls bitonicSorter for subArrays of current subArray
   * @param array - array with data
   * @param p - start of subArray
   * @param r - end of subArray
   */
  private def merger(array: Array[Int], p: Int, r: Int) {
    var i: Int = p
    var j: Int = r
    while (i < j) {
      comparator(array, i, j)
      i += 1
      j -= 1
    }
    val q: Int = (p + r) / 2
    bitonicSorter(array, p, q)
    bitonicSorter(array, q + 1, r)
  }

  /**
   * makes halfClean of subArray (recursive calls itself for halfClean smaller subArrays of input subArray)
   * @param array - array with data
   * @param p - start of subArray
   * @param r - end of subArray
   */
  private def bitonicSorter(array: Array[Int], p: Int, r: Int) {
    val q: Int = (p + r) / 2
    if (p < r) {
      halfCleaner(array, p, r)
      bitonicSorter(array, p, q)
      bitonicSorter(array, q + 1, r)
    }
  }

  /**
   * makes bitonic sequence
   * @param array - array with data
   * @param p - start of subArray
   * @param r - end of subArray
   */
  private def halfCleaner(array: Array[Int], p: Int, r: Int) {
    val q: Int = (p + r) / 2
    for (i <- 0 until (r-p+1)/2){
      comparator(array, p + i, q + 1 + i)
    }
  }

  /**
   * compare values with ids in array and swap them if first > second
   * @param array - array with values
   * @param i - first index
   * @param j - second index
   */
  private def comparator(array: Array[Int], i: Int, j: Int) {
    if (array(i) > array(j)) {
      val tmp: Int = array(i)
      array(i) = array(j)
      array(j) = tmp
    }
  }
}
