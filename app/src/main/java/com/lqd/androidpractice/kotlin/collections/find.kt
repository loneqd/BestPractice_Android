package com.lqd.androidpractice.kotlin.collections

fun main() {
    val numberOfAdults = "20"
    val numberOfKids = "30"
    val total = numberOfAdults.toInt() + numberOfKids.toInt()
    println("The total party size is: $total")




//    testFind()
//    testFirst()
//    testFirstOrNull()
}

private fun testFind() {
    val words = listOf("Lets", "find", "something", "in", "collection", "somehow")  // 1
    val first = words.find { it.startsWith("some") }                                // 2
    val last = words.findLast { it.startsWith("some") }                             // 3
    val nothing = words.find { it.contains("nothing") }
    println("$first   $last  $nothing")
}

private fun testFirst() {
    val numbers = listOf(1, -2, 3, -4, 5, -6)            // 1
    val first = numbers.first()                          // 2
    val last = numbers.last()                            // 3
    val firstEven = numbers.first { it % 2 == 0 }        // 4
    val lastOdd = numbers.last { it % 2 != 0 }
}

private fun testFirstOrNull() {
    val words = listOf("foo", "bar", "baz", "faz")         // 1
    val empty = emptyList<String>()                        // 2

    val first = empty.firstOrNull()                        // 3
    val last = empty.lastOrNull()                          // 4

    val firstF = words.firstOrNull { it.startsWith('f') }  // 5
    val firstZ = words.firstOrNull { it.startsWith('z') }  // 6
    val lastF = words.lastOrNull { it.endsWith('f') }      // 7
    val lastZ = words.lastOrNull { it.endsWith('z') }
}