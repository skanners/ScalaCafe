package com.cafe.app

import com.cafe.model._
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

import scala.collection.mutable.ListBuffer

class CafeOrderTest extends FunSuite with BeforeAndAfter with Matchers {

  val coffee = new Coffee("Coffee", BigDecimal(1.00), MenuCategory.Beverage, MenuItemState.Hot)
  val cola = new Cola("Cola", BigDecimal(0.50), MenuCategory.Beverage, MenuItemState.Cold)
  val cheeseSw = new CheeseSandwich("Cheese Sandwich", BigDecimal(2.50), MenuCategory.Food, MenuItemState.Cold)
  val steakSw = new SteakSandwich("Steak sandwich", BigDecimal(4.50), MenuCategory.Food, MenuItemState.Hot)

  var cafeOrder = new CafeOrder()
  var orderList = new ListBuffer[MenuItem]
  var billTotal: BigDecimal = 0.0

  before {
    orderList.clear()
  }

  test("Add ONLY drinks to the order using addItem() method") {
    cafeOrder.addItem(coffee)
    cafeOrder.addItem(cola)
    cafeOrder.addItem(coffee)
    assert(cafeOrder.getCustomerOrder().size == 3)

  }
  test("Add several drinks and food items to order") {
    orderList += (coffee, cola, coffee, cola, cheeseSw, steakSw)
    cafeOrder.setCustomerOrder(orderList)

    assertResult(6) {
      cafeOrder.getCustomerOrder().size
    }
  }

  test("Add order items to an existing order list") {
    orderList += (coffee, cheeseSw, steakSw)
    cafeOrder.addItem(cola)
    cafeOrder.getCustomerOrder() should have length 4
  }

  test("Total bill for an empty order will be £0") {
    assert(cafeOrder.totalBill(orderList) == 0)
  }

  test("Total bill ALL drinks, hence no service charge applies") {
    orderList += (coffee, cola)
    cafeOrder.setCustomerOrder(orderList)
    billTotal = cafeOrder.totalBill(orderList)
    cafeOrder.applyCharge(orderList, billTotal) should equal(1.50)
  }

  test("Total bill for 2 drinks and 2 food items, one of which is a HOT food item, hence 20% service charge applies") {
    orderList += (coffee, cheeseSw, steakSw, cola)
    cafeOrder.setCustomerOrder(orderList)
    billTotal = cafeOrder.totalBill(orderList)
    cafeOrder.applyCharge(orderList, billTotal) should equal(10.20)
  }

  test("Total bill for 2 drinks and 2 food items. Both food items are COLD, hence 10% service charge applies") {
    orderList += (coffee, cheeseSw, cheeseSw, cola)
    cafeOrder.setCustomerOrder(orderList)
    billTotal = cafeOrder.totalBill(orderList)
    cafeOrder.applyCharge(orderList, billTotal) should equal(7.15)
  }

  test("Total bill for mix of drinks and HOT food items, hence 20% service charge applies") {
    orderList += (coffee, cola, steakSw)
    cafeOrder.setCustomerOrder(orderList)
    billTotal = cafeOrder.totalBill(orderList)
    cafeOrder.applyCharge(orderList, billTotal) should equal(7.20)
  }

  test("Service charge exceeds £20.00 for ordered items, hence a max service charge of £20.00 applies") {
    orderList += (coffee, cola, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw,
      steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw, steakSw)
    cafeOrder.setCustomerOrder(orderList)
    billTotal = cafeOrder.totalBill(orderList)
    cafeOrder.applyCharge(orderList, billTotal) should equal(120.50)
  }


}
