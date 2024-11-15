package com.lqd.androidpractice.kotlin.coroutine

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val sequence = getSequence()
    printSequence(sequence)
}

fun getSequence() = sequence {

    println("Add 1")
    yield(1)
    println("Add 2")
    yield(2)
    println("Add 3")
    yield(3)
    println("Add 4")
    yield(4)
}

fun printSequence(sequence: Sequence<Int>) {
    val it = sequence.iterator()
    val i = it.next()
    println("Get$i")
    val j = it.next()
    println("Get$j")
    val k = it.next()
    println("Get$k")
    val m = it.next()
    println("Get$m")
}