package semester1st.lab5.oddEvenSort

object OddEvenSort {
  /**
   * Sort array using odd-even sort
   * @param array - array for sort
   */
  def sort(array: Array[Int]) {
    val length: Int = array.length
    for(i <- 0 until length){
      if (i % 2 == 1) {
        for(j <- 0 until length / 2 - 1){
          compareExchange(array, 2 * j + 1, 2 * j + 2)
        }
        if (length % 2 == 1) {
          compareExchange(array, length - 2, length - 1)
        }
      } else {
        for(j <- 0 until length/2){
          compareExchange(array, 2 * j, 2 * j + 1)
        }
      }
    }
  }

  /**
   * verify if item with index i larger then item with index j, ant if its true swam them
   * @param array - array with items
   * @param i - index of first item
   * @param j - index of second item
   */
  private def compareExchange(array: Array[Int], i: Int, j: Int) {
    if (array(i) > array(j)) {
      val tmp: Int = array(i)
      array(i) = array(j)
      array(j) = tmp
    }
  }
}

