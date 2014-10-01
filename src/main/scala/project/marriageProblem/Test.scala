package project.marriageProblem

object Test extends App {
  val boyPrefers: Map[String, Array[String]] = Map(
    "abe" -> Array("abi", "eve", "cath", "ivy", "jan", "dee", "fay", "bea", "hope", "gay"),
    "bob" -> Array("cath", "hope", "abi", "dee", "eve", "fay", "bea", "jan", "ivy", "gay"),
    "col" -> Array("hope", "eve", "abi", "dee", "bea", "fay", "ivy", "gay", "cath", "jan"),
    "dan" -> Array("ivy", "fay", "dee", "gay", "hope", "eve", "jan", "bea", "cath", "abi"),
    "ed" -> Array("jan", "dee", "bea", "cath", "fay", "eve", "abi", "ivy", "hope", "gay"),
    "fred" -> Array("bea", "abi", "dee", "gay", "eve", "ivy", "cath", "jan", "hope", "fay"),
    "gav" -> Array("gay", "eve", "ivy", "bea", "cath", "abi", "dee", "hope", "jan", "fay"),
    "hal" -> Array("abi", "eve", "hope", "fay", "ivy", "cath", "jan", "bea", "gay", "dee"),
    "ian" -> Array("hope", "cath", "dee", "gay", "bea", "abi", "fay", "ivy", "jan", "eve"),
    "jon" -> Array("abi", "fay", "jan", "gay", "eve", "bea", "dee", "cath", "ivy", "hope")
  )
  val girlPrefers: Map[String, Array[String]] = Map(
    "abi" -> Array("bob", "fred", "jon", "gav", "ian", "abe", "dan", "ed", "col", "hal"),
    "bea" -> Array("bob", "abe", "col", "fred", "gav", "dan", "ian", "ed", "jon", "hal"),
    "cath" -> Array("fred", "bob", "ed", "gav", "hal", "col", "ian", "abe", "dan", "jon"),
    "dee" -> Array("fred", "jon", "col", "abe", "ian", "hal", "gav", "dan", "bob", "ed"),
    "eve" -> Array("jon", "hal", "fred", "dan", "abe", "gav", "col", "ed", "ian", "bob"),
    "fay" -> Array("bob", "abe", "ed", "ian", "jon", "dan", "fred", "gav", "col", "hal"),
    "gay" -> Array("jon", "gav", "hal", "fred", "bob", "abe", "col", "ed", "dan", "ian"),
    "hope" -> Array("gav", "jon", "bob", "abe", "ian", "dan", "hal", "ed", "col", "fred"),
    "ivy" -> Array("ian", "col", "hal", "gav", "fred", "bob", "abe", "ed", "jon", "dan"),
    "jan" -> Array("ed", "hal", "gav", "abe", "bob", "jon", "col", "ian", "fred", "dan")
  )

  println("Test Solving Marriage Problem:")
  val couples: List[Couple] = Marriage.solve(boyPrefers, girlPrefers).sortBy(_.boy.name)
  couples.foreach(println)
  println("Stable = " + Marriage.isStable(boyPrefers, girlPrefers, couples) + "\n")

  println("Switching fred & jon's partners")
  val (fred, jon) = (couples(5), couples(9))
  val switchedCouples: List[Couple] = couples.updated(5, new Couple(fred.boy, jon.girl)).updated(9, new Couple(jon.boy, fred.girl))
  switchedCouples.foreach(println)
  println("Stable = " + Marriage.isStable(boyPrefers, girlPrefers, switchedCouples))
}