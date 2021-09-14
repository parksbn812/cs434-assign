package greeter
object Hello extends App{
  def greet(name: String): Unit = {
    println(s"Hello, $name")
  }
  greet(name = "Anna")
  println("Hello, World!")
}