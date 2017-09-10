package com.cafe.model

abstract class MenuItem(private var name: String, private var unitPrice: BigDecimal, private var menuCategory: MenuCategory.Value, private var menuItemState: MenuItemState.Value) {

  def getName(): String = {
    name
  }

  def getUnitPrice(): BigDecimal = {
    unitPrice
  }

  def setName(aName: String): Unit = {
    name = aName
  }

  def setUnitPrice(aPrice: BigDecimal): Unit = {
    unitPrice = aPrice
  }

  def getMenuCategory(): MenuCategory.Value = {
    menuCategory
  }

  def setMenuCategory(aCategory: MenuCategory.Value): Unit = {
    menuCategory = aCategory
  }

  def getMenuItemState(): MenuItemState.Value = {
    menuItemState
  }

  def setMenuItemState(aState: MenuItemState.Value): Unit = {
    menuItemState = aState
  }

  override def toString = s"MenuItem($name, $unitPrice, $menuCategory, $menuItemState)"
}

