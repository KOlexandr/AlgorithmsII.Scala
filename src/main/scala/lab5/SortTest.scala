package lab5

import java.util.Random

import lab5.bitonicSort.BitonicSort
import lab5.oddEvenSort.OddEvenSort

object SortTest {

  def main(args: Array[String]) {
    val length: Int = 1024
    val bitonic: Array[Int] = newIntRandomArray(length)
    val oddEven: Array[Int] = bitonic.clone()
    println("Before Sort: ")
    println(bitonic.mkString(", "))
    println("Is same sequences: " + (bitonic.mkString(", ") == oddEven.mkString(", ")))

    BitonicSort.sort(bitonic)
    println("Sorted Array (Bitonic): ")
    println(bitonic.mkString(", "))

    OddEvenSort.sort(oddEven)
    println("Sorted Array (OddEven): ")
    println(oddEven.mkString(", "))

    println("Is same sequences: " + (bitonic.mkString(", ") == oddEven.mkString(", ")))
  }

  private def newIntRandomArray(length: Int, maxValue: Int): Array[Int] = {
    val random: Random = new Random
    Array.fill(length)(random.nextInt(maxValue) + 1)
  }

  private def newIntRandomArray(length: Int): Array[Int] = newIntRandomArray(length, 1024)
}
