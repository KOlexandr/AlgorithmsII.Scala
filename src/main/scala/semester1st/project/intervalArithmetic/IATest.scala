import semester1st.project.intervalArithmetic.Interval

object IATest extends App {
  val interval_0_1: Interval = new Interval(0, 1)
  val interval_2_5: Interval = new Interval(2, 5)
  val interval_15_2: Interval = new Interval(1.5, 2)
  val interval_05_15: Interval = new Interval(0.5, 1.5)

  println(interval_0_1)

  println(interval_0_1 + " + " + interval_2_5 + " = " + (interval_0_1 + interval_2_5) + "\n")

  println(interval_0_1 + " - " + interval_2_5 + " = " + (interval_0_1 - interval_2_5) + "\n")

  println(interval_15_2 + " * " + interval_2_5 + " = " + (interval_15_2 * interval_2_5) + "\n")

  println(interval_05_15  + " / " + interval_2_5 + " = " + (interval_05_15 / interval_2_5) + "\n")

  println(interval_2_5 + " == " + interval_2_5  + " = " + (interval_2_5 == interval_2_5) + "\n")

  println(interval_05_15  + " == " + interval_2_5 + " = " + (interval_05_15 == interval_2_5) + "\n")

  println(interval_05_15 + " != " + interval_2_5 + " = " + (interval_05_15 != interval_2_5) + "\n")

  println(interval_2_5 + " != " + interval_2_5 + " = " + (interval_2_5 != interval_2_5) + "\n")

  println(interval_2_5  + " < " + interval_2_5 + " = " + (interval_2_5 < interval_2_5) + "\n")

  println(interval_2_5 + " <= " + interval_2_5 + " = " + (interval_2_5 <= interval_2_5) + "\n")

  println(interval_2_5 + " > " + interval_2_5 + " = " + (interval_2_5 > interval_2_5) + "\n")

  println(interval_2_5 + " >= " + interval_2_5 + " = " + (interval_2_5 >= interval_2_5) + "\n")

  println("-" + interval_2_5 + " = " + (-interval_2_5) + "\n")

  println(interval_0_1 + " intersection " + interval_05_15 + " = " + (interval_0_1 intersection  interval_05_15) + "\n")
}