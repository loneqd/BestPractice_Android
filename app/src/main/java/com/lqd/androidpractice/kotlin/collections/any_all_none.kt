package com.lqd.androidpractice.kotlin.collections

fun main() {
    testAll()
    testAny()
    testNone()
}

fun testAny() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val anyNegative = numbers.any { it < 0 }             // 2
    val anyGT6 = numbers.any { it > 6 }
    println(">>>> $anyNegative  $anyGT6")
}

fun testAll() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val allEven = numbers.all { it % 2 == 0 }            // 2
    val allLess6 = numbers.all { it < 6 }
    println(">>>> $allEven  $allLess6")
}

fun testNone() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val allEven = numbers.none { it % 2 == 1 }           // 2
    val allLess6 = numbers.none { it > 6 }
    println(">>>> $allEven  $allLess6")
}