package com.lqd.androidpractice.kotlin.scopefunctions


class Person1(val name: String, var age: Int, val about:String) {
    fun writeCreationLog(it:String) {
        println("$name")
    }
}
//private class Person() {
//
//    var name:String? = null
//    var age:Int? = null
//    var about:String? = null
//}

/**
 * also 在赋值的同时可以做一些处理
 * 它的主要作用是在一个对象上执行给定的代码块，并返回该对象本身。这个函数对于在执行一些额外操作
 */
fun main() {
    val jake = Person1("Jake", 30, "Android developer")   // 1
        .also {
            println(it)
            it.age++
//            writeCreationLog(it)                         // 3
        }

    println("$jake")
}