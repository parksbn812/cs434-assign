package recfun
import common._

object Main {
  def main(args: Array[String]) {
    println("Pascal's Triangle")
    for (row <- 0 to 10) {
      for (col <- 0 to row)
        print(pascal(col, row) + " ")
      println()
    }
    print(balance("())(".toList))
    println
  }

  /**
   * Exercise 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c==0 || c==r) 1
    else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2
   */
  def balance(chars: List[Char]): Boolean = {
    def balanceJudgeWithCount(chars:List[Char], openedCount:Int):Boolean = {
      if(openedCount < 0) {
        false
      }
      else if(chars.isEmpty) openedCount == 0
      else if (chars.head == '(') balanceJudgeWithCount(chars.tail, openedCount + 1)
      else if (chars.head == ')') balanceJudgeWithCount(chars.tail, openedCount - 1)
      else balanceJudgeWithCount(chars.tail, openedCount)
    }
    balanceJudgeWithCount(chars, 0)
  }

  /**
   * Exercise 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}