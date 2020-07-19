package com.kristenlittle.checkout

import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class CheckoutSpec extends AnyFlatSpec with Matchers {
val underTest = new Checkout()

  "CheckoutCalc" should "return with a price for one item in items list and sku list" in {
    underTest.CheckoutCalc(List("a"), List(StockKeepingUnit("a", 50, None))) should equal(50)
  }

 it should "return with a price for multiple items in items list, but only one in sku list" in {
   underTest.CheckoutCalc(List("a", "a", "a", "a"), List(StockKeepingUnit("a", 50, None))) should equal(200)
 }

  it should "return with a price for multiple items in items list and multiple in sku list" in {
    underTest.CheckoutCalc(List("a", "b", "c", "d"), List(StockKeepingUnit("a", 50, None), StockKeepingUnit("b", 30, None),
      StockKeepingUnit("c", 20, None), StockKeepingUnit("d", 15, None))) should equal(115)
  }

  it should "return with an offer for consecutive items" in {
    underTest.CheckoutCalc(List("a", "a", "a"), List(StockKeepingUnit("a", 50, Option(SkuOffer(3, 130))))) should equal(130)
  }

  it should "return with a price that is double the offer" in {
    underTest.CheckoutCalc(List("a", "a", "a", "a", "a", "a"), List(StockKeepingUnit("a", 50, Option(SkuOffer(3, 130))))) should equal(260)
  }

  it should "return with amount for total of offer and the remaining item with consecutive items" in {
    underTest.CheckoutCalc(List("a", "a", "a", "a"), List(StockKeepingUnit("a", 50, Option(SkuOffer(3, 130))))) should equal(180)
  }

  it should "return multiple offers for consecutive items" in {
    underTest.CheckoutCalc(List("a", "a", "a", "b", "b","c", "d"), List(StockKeepingUnit("a", 50, Option(SkuOffer(3, 130))), StockKeepingUnit("b", 30, Option(SkuOffer(2, 45))),
      StockKeepingUnit("c", 20, None), StockKeepingUnit("d", 15, None))) should equal(210)
  }

  it should "return with multiple offers for non-consecutive items" in {
    underTest.CheckoutCalc(List("b","d", "a","b", "a", "c","a"), List(StockKeepingUnit("a", 50, Option(SkuOffer(3, 130))), StockKeepingUnit("b", 30, Option(SkuOffer(2, 45))),
      StockKeepingUnit("c", 20, None), StockKeepingUnit("d", 15, None))) should equal(210)
  }

  it should "return with 0 for empty lists" in {
    underTest.CheckoutCalc(List(), List()) should equal(0)
  }


}
