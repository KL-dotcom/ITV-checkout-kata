package com.kristenlittle.checkout

class Checkout {

  def CheckoutCalc(items:List[String], sku:List[StockKeepingUnit]) : BigDecimal = {

    def calculateTotalForItem(itemDetails: StockKeepingUnit) : BigDecimal = {
      val itemAmount = items.count(_== itemDetails.item)
      itemDetails.offer.map{
        offer => ((itemAmount / offer.offerQuantity).floor * offer.offerPrice) + (itemAmount % (offer.offerQuantity) * itemDetails.price)
      }.getOrElse(itemAmount * itemDetails.price)

    }

    sku.map(elem => calculateTotalForItem(elem)).sum
  }

}
case class StockKeepingUnit(item:String, price: BigDecimal, offer: Option[SkuOffer])
case class SkuOffer(offerQuantity: Int, offerPrice: BigDecimal)
