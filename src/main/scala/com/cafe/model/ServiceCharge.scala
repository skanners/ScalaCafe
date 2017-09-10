package com.cafe.model

import scala.collection.mutable.ListBuffer

trait ServiceCharge {

  val chargeLimit: BigDecimal = 20.00

  def applyCharge(orderList: ListBuffer[MenuItem], billTotal: BigDecimal): BigDecimal = {
    var chargeMultiplier: BigDecimal = 0.0
    var newTotal: BigDecimal = 0.0
    val hasFoodItem = orderList.exists(_.getMenuCategory().equals(MenuCategory.Food))
    val foodItemList: ListBuffer[MenuItem] = orderList.filter(_.getMenuCategory().equals(MenuCategory.Food))
    val hasHotFood = foodItemList.exists(_.getMenuItemState().equals(MenuItemState.Hot))

    if (hasHotFood) {
      chargeMultiplier = 0.2 // Apply 20% charge
    } else if (hasFoodItem) {
      chargeMultiplier = 0.1 // Apply 10% charge
    }
    var serviceCharge = billTotal * chargeMultiplier
    if (chargeExceedsMax(serviceCharge)) {
      serviceCharge = chargeLimit
    }
    println("A service charge of £" + serviceCharge + " has been applied")
    newTotal = billTotal + (serviceCharge)
    newTotal
  }

  private def chargeExceedsMax(totalCharge: BigDecimal): Boolean = {
    totalCharge > chargeLimit
  }
}
