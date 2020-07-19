package com.kristenlittle.checkout

class Checkout {

  def CheckoutCalc(items:List[String], sku:List[StockKeepingUnit]) : BigDecimal = {
      items.map { elem =>
       val info = sku.find(_.item == elem)
        info.get.price
      }.sum
  }


}

case class StockKeepingUnit(item:String, price: BigDecimal)
