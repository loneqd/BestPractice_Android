package com.lqd.androidpractice.kotlin.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val channel = getProducer(this)

    testConsumer(channel)
}

fun getProducer(scope: CoroutineScope) = scope.produce {
    println("Send:1")
    send(1)
    println("Send:2")
    send(2)
    println("Send:3")
    send(3)
    println("Send:4")
    send(4)
}

suspend fun testConsumer(channel: ReceiveChannel<Int>) {
    delay(100)
    val i = channel.receive()
    println("Receive$i")
    delay(100)
    val j = channel.receive()
    println("Receive$j")
    delay(100)
    val k = channel.receive()
    println("Receive$k")
    delay(100)
    val m = channel.receive()
    println("Receive$m")
}

/*
输出结果：
Send:1
Receive1
Send:2
Receive2
Send:3
Receive3
Send:4
Receive4
*/