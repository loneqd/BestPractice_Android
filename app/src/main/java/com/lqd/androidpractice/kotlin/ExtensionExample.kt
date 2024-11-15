package com.lqd.androidpractice.kotlin


fun String.lastElement(): Char? {
    if (this.isEmpty())
        return null
    return this[length - 1]
}

val String.lastEle: Char?
    get() {
        if (this.isEmpty())
            return null
        return this[length - 1]
    }


fun main() {
    val msg = "asdf"
    val last = msg.lastElement()
    val xx = msg.lastEle
}