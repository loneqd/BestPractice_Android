package com.lqd.androidpractice.kotlin.generics


open class Fruit {
    open fun name() = "Fruit"
}

class Apple : Fruit() {
    override fun name() = "Apple"

}

class FruitBasket<in T : Fruit> {
    fun add(fruit: T) {
        println("Added ${fruit.name()}")
    }
}

fun main() {
    val strArr: Array<String> = arrayOf("A", "B")
//    val arr:Array<Any> = strArr // kotlin中数组也是抗变的
    val appleBasket: FruitBasket<Fruit> = FruitBasket()
    val fruitBasket: FruitBasket<Apple> = appleBasket
    fruitBasket.add(Apple())

    test(XiaoMiTV())
    buy(Controller<TV>())
}

open class TV {
    open fun turnOn() {
        println("TV is turning on")
    }
}

class XiaoMiTV : TV() {
    override fun turnOn() {
        println("XiaoMi TV is turning on")
    }
}

class Controller<in T> {
    fun turnOn(tv: T) {
    }
}

fun test(tv: TV) {
    tv.turnOn()
}

fun buy(controller: Controller<XiaoMiTV>) {
    val xiaoMiTV = XiaoMiTV()
    controller.turnOn(xiaoMiTV)
}
