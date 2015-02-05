package semester1st.project.marriageProblem

/**
 * Class represents Girl
 * @param name - name of Girl
 * @param fiance - Girl's boyfriend
 * @param prefers - Girl's prefers
 */
class Girl(val name: String, var fiance: Boy, var prefers: Array[Boy]) {

  def this(name: String, prefers: Array[Boy]) = this(name, null, prefers)

  def this(name: String) = this(name, null, null)

  override def toString: String = name

  def isPrefers(other: Boy): Boolean =
    prefers.indexWhere(p => p.name == other.name) < prefers.indexWhere(p => p.name == fiance.name)
}

/**
 * Class represents Boy
 * @param name - name of Boy
 * @param fiance - Boy's girlfriend
 * @param prefers - Boy's prefers
 */
class Boy(val name: String, var fiance: Girl, var prefers: Array[Girl]) {

  def this(name: String, prefers: Array[Girl]) = this(name, null, prefers)

  def this(name: String) = this(name, null)

  override def toString: String = name

  def isPrefers(other: Girl): Boolean =
    prefers.indexWhere(p => p.name == other.name) < prefers.indexWhere(p => p.name == fiance.name)
}

class Couple(val boy: Boy, val girl: Girl){
  override def toString: String = boy.name + " -> " + girl.name
}