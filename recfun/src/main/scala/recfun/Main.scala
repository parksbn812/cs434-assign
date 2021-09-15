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
   * Exercise 1: Pascal's Triangle
   */
  def pascal(c: Int, r: Int): Int = {
    if (c==0 || c==r) 1
    else pascal(c-1, r-1) + pascal(c, r-1)
  }

  /**
   * Exercise 2: Parentheses Balancing
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
   * Exercise 3: Counting Change
   */
  def countChange(money: Int, coins: List[Int]): Int = {
    if(money < 0 || coins.isEmpty) 0
    else if (money == 0) 1
    else (countChange(money - coins.head, coins) + countChange(money, coins.tail))
  }
}