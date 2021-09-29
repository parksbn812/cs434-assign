package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5)
    val t2 = Fork(Fork(Leaf('a',2), Leaf('b',3), List('a','b'), 5), Leaf('d',4), List('a','b','d'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a','b','d'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e',1), Leaf('t',2), Leaf('x',3)))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e',1),Leaf('t',2),List('e', 't'),3), Leaf('x',4)))
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("times"){
    val t1 = List('a','b','c','a','b')
    val tt = times(t1)
    println(tt)
    assert(tt.length == 3)
    assert(tt(1)._1 == 'b')
    assert(tt(1)._2 == 2)

  }

  test("decodedSecret test") {
    assert(decodedSecret.mkString("") == "huffmanestcool")
  }

  test("singleton test") {
    val fork1 = makeCodeTree(Leaf ('e', 1), Leaf('d', 2))
    val fork2 = makeCodeTree(Leaf('a', 2), fork1)

    assert(singleton(List() )== false)
    assert(singleton(List(fork1)) == true)
    assert(singleton(List(fork1, fork2)) == false)
  }

  test("until test") {
    val leaflist = List(Leaf('a', 1), Leaf('b', 2), Leaf('c', 3),Leaf('d', 4))
    assert(until(singleton, combine)(leaflist) ===
      Fork(Fork(Fork(Leaf('a',1),Leaf('b',2),List('a', 'b'),3),Leaf('c',3),List('a', 'b', 'c'),6),Leaf('d',4),List('a', 'b', 'c', 'd'),10))
  }

  test("createCodeTree test") {
    val testData = string2Chars("abbcddcddc")
    val expected = Fork(Fork(Fork(Leaf('a',1),Leaf('b',2),List('a', 'b'),3),Leaf('c',3),List('a', 'b', 'c'),6),Leaf('d',4),List('a', 'b', 'c', 'd'),10)
    assert(createCodeTree(testData) === expected)
  }

  test("encode test") {
    assert(encode(frenchCode)(string2Chars("huffmanestcool")) === secret)
  }

  test("quickEncode test") {
    assert(quickEncode(frenchCode)(string2Chars("huffmanestcool")) === secret)
  }

}
