package com.kristenlittle.checkout

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {
val underTest = new Checkout()

  "CheckoutCalc" should "return with a price for one item in items list and sku list" in {
    underTest.CheckoutCalc(List("a"), List(StockKeepingUnit("a", 50))) should equal(50)
  }

 it should "return with a price for multiple items in items list, but only one in sku list" in {
   underTest.CheckoutCalc(List("a", "a", "a", "a"), List(StockKeepingUnit("a", 50))) should equal(200)
 }

  it should "return with a price for multiple items in items list and multiple in sku list" in {
    underTest.CheckoutCalc(List("a", "b", "c", "d"), List(StockKeepingUnit("a", 50), StockKeepingUnit("b", 30),
      StockKeepingUnit("c", 20), StockKeepingUnit("d", 15))) should equal(115)
  }

}
