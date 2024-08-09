package com.lqd.androidpractice.kotlin


object Example1 {


    fun test1() {
        var count: Int = 10
        count = 12

        val name: String = "zhangsan"
//        name = "lisi" //不可变属性，不能赋值

        val languageName = "Kotlin" //类型推断
        val uperCaseName = languageName.toUpperCase()

        val fooCount = 100
        fooCount.inc()  //推断出整型

        val foo1: String? = null

        //条件语句
        if (count > 6) {

        } else {

        }

        val answerString: String = if (count == 42) {
            "I have the answer"
        } else if (count > 30) {
            "The answer is close"
        } else {
            "The answer eludes me."
        }

        val answerString2: String = when {
            count == 42 -> "I have the answer"
            count > 30 -> "The answer is close"
            else -> "The answer eludes me."
        }

    }

    // 函数
    fun generateAnswerString(count: Int): String {
        val answerString = if (count == 42) {
            "I have the answer."
        } else {
            "The answer eludes me"
        }

        return answerString
    }

    // 简化函数
    fun generateAnswerString2(count: Int): String {
        return if (count == 42) {
            "I have the answer."
        } else {
            "The answer eludes me"
        }
    }

    // return替换为赋值运算符
    fun generateAnswerString3(count: Int): String = if (count > 40) {
        "I have the answer."
    } else {
        "The answer eludes me"
    }

    // 匿名函数
    val stringLengthFuc: (String) -> Int = { input ->
        input.length
    }

    val stringLength: Int = stringLengthFuc("Android")

    // 高阶函数
    fun stringMapper(str: String, mapper: (String) -> Int): Int {
        return mapper(str)
    }

    fun test2() {

        stringMapper("Android", { input ->
            input.length
        })

        //如果匿名函数是在某个函数上定义的最后一个参数，则您可以在用于调用该函数的圆括号之外传递它
        stringMapper("Android") { input -> input.length }

        val account = Account("name", "type")
        account.name!!.trim()
        account.name?.trim()
        val accountName = account.name?.trim() ?: "Default name"

    }


}


class Account(val name: String, val type: String)

class Wheel {

}

class DoorLock {}
class Car(val wheels: List<Wheel>) {
    private val doorLock: DoorLock = DoorLock()

    // 公开get方法，set无法访问
    var gallonsOfFueInTank: Int = 15
        private set

    fun unlockDoor(key: String): Boolean {
        return true
    }
}