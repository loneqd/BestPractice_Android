package com.lqd.androidpractice.kotlin.introduction


fun main() {
    var neverNull: String = "This can't be null"

//    neverNull = null // 不能为空

    var nullable: String? = "You can keep a null here"

    nullable!!.length

    nullable = null

    var xx = nullable ?: "123"
    print(xx)

    var inferredNonNull = "The compiler assumes non-null"

//    inferredNonNull = null

    fun strLength(notNull: String): Int {
        return notNull.length
    }

    strLength(neverNull)
//    strLength(nullable)
}