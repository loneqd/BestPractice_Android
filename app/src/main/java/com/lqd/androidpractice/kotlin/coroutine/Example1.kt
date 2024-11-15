package com.lqd.androidpractice.kotlin.coroutine

fun main() {
    val list = getList()
    printList(list)
}

fun getList(): List<Int> {
    val list = mutableListOf<Int>()
    println("Add 1")
    list.add(1)
    println("Add 2")
    list.add(2)
    println("Add 3")
    list.add(3)
    println("Add 4")
    list.add(4)

    return list
}

fun printList(list: List<Int>) {
    val i = list[0]
    println("Get$i")
    val j = list[1]
    println("Get$j")
    val k = list[2]
    println("Get$k")
    val m = list[3]
    println("Get$m")
}

/* 运行结果：
Add 1
Add 2
Add 3
Add 4
Get1
Get2
Get3
Get4
*/