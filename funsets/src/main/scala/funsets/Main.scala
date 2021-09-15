package funsets

object Main extends App {
  import FunSets._
  def f(x:Int):Int = {
    x*10
  }

  val s = union(singletonSet(4), singletonSet(5))


  println(contains(singletonSet(1), 1))
  printSet(map(s, f))
}
