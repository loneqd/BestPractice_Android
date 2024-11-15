package com.lqd.androidpractice.kotlin.coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    GlobalScope.launch {
        val list = getList()
        printList(list)
    }

    // 启动的协程会阻塞当前线程的执行
    val result = runBlocking {
        val sequence = getSequence()
        printSequence(sequence)
        return@runBlocking "done"
    }

    runBlocking {
        println(">>>>>>")

        val deferred: Deferred<String> = async {
            println("async start")
            delay(1000L)
            return@async "async done"
        }
        val result = deferred.await()
        println("result: $result")
    }
}


