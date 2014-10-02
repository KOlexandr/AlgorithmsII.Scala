package project.marriageProblem

/**
 * Problem description
 *
 * Given an equal number of men and women to be paired for marriage,
 * each man ranks all the women in order of his preference and each women ranks all the men in order of her preference.
 *
 * A stable set of engagements for marriage is one where no man prefers a women over the one he is engaged to,
 * where that other woman also prefers that man over the one she is engaged to. I.e. with consulting marriages,
 * there would be no reason for the engagements between the people to change.
 *
 * @author Oleksandr KOL Kucher
 */
object Marriage {

  /**
   * solves Marriage problem
   * @param boyPrefers - map of all Boys and their prefers
   * @param girlPrefers - map of all Girls and their prefers
   * @return list of couples
   */
  def solve(boyPrefers: Map[String, Array[String]], girlPrefers: Map[String, Array[String]]): List[Couple] = {
    val (boys, girls) = mapsToPersons(boyPrefers, girlPrefers)
    searchCouples(boys, girls)
  }

  /**
   * verifies if created couples stable
   * @param boyPrefers - map of all Boys and their prefers
   * @param girlPrefers - map of all Girls and their prefers
   * @param couples - list of created couples
   * @return true or false
   */
  def isStable(boyPrefers: Map[String, Array[String]], girlPrefers: Map[String, Array[String]], couples: List[Couple]): Boolean = {
    val (boys, girls) = mapsToPersons(boyPrefers, girlPrefers)
    val boyCouple: Map[String, Girl] = couples.map(c => c.boy.name -> c.girl).toMap
    val girlCouple: Map[String, Boy] = couples.map(c => c.girl.name -> c.boy).toMap

    boys.foreach(b => b.fiance = boyCouple(b.name))
    girls.foreach(g => g.fiance = girlCouple(g.name))

    for(boy <- boys){
      for(girl <- girls){
        if(boy.isPrefers(girl) && girl.isPrefers(boy)){
          return false
        }
      }
    }
    true
  }

  /**
   * gets two maps with names and prefers (one for boys and one for girls) 
   * and create for each person his/her own object with his/her own prefers
   * 
   * @param boyPrefers - map with all boys' prefers
   * @param girlPrefers - map with all girls' prefers
   * @return tuple with list of Boy and list of Girl that have all their prefers
   */
  private def mapsToPersons(boyPrefers: Map[String, Array[String]], girlPrefers: Map[String, Array[String]]): (List[Boy], List[Girl]) = {
    val (boyNames, girlNames) = (boyPrefers.keySet.toList, girlPrefers.keySet.toList)
    val (boysMap, girlsMap) = (boyNames.map(name=> name -> new Boy(name)).toMap, girlNames.map(name => name -> new Girl(name)).toMap)

    boyNames.foreach(name => boysMap(name).prefers = boyPrefers(name).map(gName => girlsMap(gName)))
    girlNames.foreach(name => girlsMap(name).prefers = girlPrefers(name).map(gName => boysMap(gName)))
    
    (boysMap.values.toList, girlsMap.values.toList)
  }

  /**
   * Searches all couples and return list of them
   * @param boys - list of all Boys
   * @param girls - list of all Girls
   */
  private def searchCouples(boys: List[Boy], girls: List[Girl]): List[Couple] = {
    /**
     * makes couples from Boys and Girls with using Girls' boyfriends
     * @param girls - list of Girls
     * @param couples - list of created couples
     */
    def toCouples(girls: List[Girl], couples: List[Couple]): List[Couple] = {
      if(girls.isEmpty) couples
      else toCouples(girls.tail, couples ::: List(new Couple(girls.head.fiance, girls.head)))
    }
    iterateBoys(boys)
    toCouples(girls, List())
  }

  /**
   * gets all free boys and runs for each of them function `@link project.marriageProblem.Marriage.iterateBoyPrefers`
   * @param freeBoys - list of Boys which has not a couple yet
   */
  private def iterateBoys(freeBoys: List[Boy]): Unit = {
    if(freeBoys.nonEmpty) iterateBoys(iterateBoyPrefers(freeBoys.head, freeBoys.head.prefers, freeBoys.tail))
  }

  /**
    *
    * @param boy - current free Boy
    * @param girls - prefers of current free Boy
    * @param freeBoys - rest free Boys
    * @return new list of free boys
   *         it can be:
   *         1. empty
   *         2. contains rest free boys
   *         3. contains rest free boys with one boy which has couple and was replaced by current boy
    */
  private def iterateBoyPrefers(boy: Boy, girls: Array[Girl], freeBoys: List[Boy]): List[Boy] = {
    if(girls.isEmpty) freeBoys
    else if(null == girls.head.fiance){
      girls.head.fiance = boy
      boy.fiance = girls.head
      boy.prefers = girls.tail
      freeBoys
    } else if(girls.head.isPrefers(boy)){
      val boyfriend: Boy = girls.head.fiance
      girls.head.fiance = boy
      boy.fiance = girls.head
      boy.prefers = girls.tail
      freeBoys ::: List(boyfriend)
    } else {
      boy.prefers = girls.tail
      iterateBoyPrefers(boy, girls.tail, freeBoys)
    }
  }
}