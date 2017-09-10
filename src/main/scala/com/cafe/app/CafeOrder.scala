package com.cafe.app

import com.cafe.model.{MenuItem, ServiceCharge}

import scala.collection.mutable.ListBuffer
import scala.math.BigDecimal.RoundingMode

class CafeOrder extends ServiceCharge {
  private var customerOrder = new ListBuffer[MenuItem]()

  def getCustomerOrder(): ListBuffer[MenuItem] = {
    customerOrder
  }

  def setCustomerOrder(anOrder: ListBuffer[MenuItem]): Unit = {
    customerOrder = anOrder
  }

  def addItem(menuItem: MenuItem): ListBuffer[MenuItem] = {
    customerOrder += menuItem
  }


  def totalBill(orderList: ListBuffer[MenuItem]): BigDecimal = {
    val total = orderList.map(_.getUnitPrice()).sum
    total.setScale(2, RoundingMode.HALF_EVEN)
    println("You ordered " + customerOrder.length + " items.")
    println("Your total Cafe order is £" + total)
    total
  }
}
