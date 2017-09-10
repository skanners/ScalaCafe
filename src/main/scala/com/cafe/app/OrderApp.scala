package com.cafe.app

import com.cafe.model._

import scala.collection.mutable.ListBuffer

object OrderApp extends App {

  val coffee = new Coffee("coffee", BigDecimal(1.00), MenuCategory.Beverage, MenuItemState.Hot)
  val cola = new Cola("Cola", BigDecimal(0.50), MenuCategory.Beverage, MenuItemState.Cold)
  val cheeseSw = new CheeseSandwich("Cheese Sandwich", BigDecimal(2.00), MenuCategory.Food, MenuItemState.Cold)
  val steakSw = new SteakSandwich("Steak sandwich", BigDecimal(4.50), MenuCategory.Food, MenuItemState.Hot)

  val order = new CafeOrder
  var orderList, orderList2 = new ListBuffer[MenuItem]()
  order.addItem(coffee)
  order.addItem(cola)
  order.addItem(steakSw)
  order.addItem(cheeseSw)
  order.addItem(steakSw)

  orderList = order.getCustomerOrder()

  val total = order.totalBill(orderList)
  val newTotal = order.applyCharge(orderList,total)
  println("Your total Cafe order after applying a service charge is £" + newTotal)


}
