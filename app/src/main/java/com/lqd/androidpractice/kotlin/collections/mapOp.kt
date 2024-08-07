package com.lqd.androidpractice.kotlin.collections

fun main() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)     // 1

    val x = mutableListOf<Int>()
    x.add(1)
    x.add(2)
    println(x)
    println(">>>>>>>>>>")


    val doubled = numbers.map { x -> x * 2 }      // 2

    val tripled = numbers.map { it * 3 }


    val map = mapOf("key" to 42)

    val value1 = map["key"]                                     // 1
    val value2 = map["key2"]                                    // 2

    val value3: Int = map.getValue("key")                       // 1

    val mapWithDefault = map.withDefault { k -> k.length }
    val value4 = mapWithDefault.getValue("key2")                // 3

    try {
        map.getValue("anotherKey")                              // 4
    } catch (e: NoSuchElementException) {
        println("Message: $e")
    }


}