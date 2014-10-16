package project.intervalArithmetic

class Interval(val left: Double, val right: Double) {
  require(left <= right, "Left border of interval must be less or equals then right.")

  def + (that: Interval) = new Interval(this.left + that.left, this.right + that.right)

  def - (that: Interval) = new Interval(this.left - that.right, this.right - that.left)

  def * (that: Interval) = {
    val products: List[Double] =
        List(this.left * that.left) ::: List(this.left * that.right) :::
        List(this.right * that.left) ::: List(this.right * that.right)
    new Interval(products.min, products.max)
  }

  def / (that: Interval) =
    if(that.left == 0 || that.right == 0) throw new IllegalArgumentException("Divider can not by 0")
    else this * new Interval(math.min(1/that.left, 1/that.right), math.max(1/that.left, 1/that.right))

  def < (that: Interval) = this.right < that.left

  def > (that: Interval) = that < this

  def <= (that: Interval) = this < that || this == that

  def >= (that: Interval) = that <= this

  def == (that: Interval) = this.left == that.left && this.right == that.right

  def != (that: Interval) = !(this == that)

  def unary_- = new Interval(math.min(-this.left, -this.right), math.max(-this.left, -this.right))

  def intersection (that: Interval) =
    if(this < that || that < this) null
    else new Interval(math.max(this.left, that.left), math.min(this.right, that.right))

  def width = right - left

  def median = (left + right) / 2

  def abs = math.max(math.abs(left), math.abs(right))

  def distance(that: Interval) = math.max(this.left - that.left, this.right - that.right)

  def isSymmetric = -left == right

  def inf = left

  def sup = right

  override def toString: String = "[" + left + ", " + right + "]"
}

object I{
  def apply(left: Double, right: Double) = new Interval(left, right)
}