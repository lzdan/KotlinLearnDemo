package com.fuhuang.firstkotlindemo

/**
 * @author zhangdan
 * on 2019/8/19
 *  kotlin 条件控制
 */
class ConditionControl {

    //IF 表达式
    // 一条if语句包含一个布尔表达式和一条或多条语句
    fun test1(){
        // 传统用法
        val a = 1
        val b = 2
        var max = a
        if (a < b) max = b

        // 使用else
        var max2 :Int
        if (a > b){
            max2 = a
        } else {
            max2 = b
        }

        // 作为表达式
        val max3 = if (a > b) a else b

        // 也可以把if表达式的结果赋给一个变量
        val max4 = if (a > b){
            a
        } else {
            b
        }

        // val c = if(条件表达式) a else b

        // 使用区间
        // 使用in运算符来检测某个数字是否在指定区间内 区间格式x..y
        val x = 5
        if (x in 1..6){
            x
        }
    }

    // WHEN表达式（类似Java的switch表达式）
    // when 将它的参数和所有的分支条件顺序比较，直到某个分支满足条件。
    // when 既可以被当做表达式使用也可以被当做语句使用。
    //      如果它被当做表达式，符合条件的分支的值就是整个表达式的值，
    //      如果当做语句使用， 则忽略个别分支的值。
    fun test2(){
        var x:Int = 1
        when(x){
            1 -> print("x =1")
            2 -> print("x =2")
            3,4 -> print("x =3 or x =4") // 多个分支处理相同
            else -> { // 注意这个块，相当于switch的default
                print("x 不是 1 ，也不是 2")
            }
        }

        // 可以检测一个值在（in）或者不在（!in）一个区间或者集合中
        when(x){
            in 1..10 -> print("x is in the range")
            !in 10..20 -> print("x is outside the range")
            else -> print("none of the above")
        }

        // when 也可以用来取代 if-else if链。 如果不提供参数，所有的分支条件都是简单的布尔表达式，而当一个分支的条件为真时则执行该分支
        when {
            x > 1 -> print("x > 1")
            else -> print("x <= 1")
        }
    }
    // 检测一个值是（is）或者不是（!is）一个特定类型的值。注意： 由于智能转换，你可以访问该类型的方法和属性而无需 任何额外的检测。
    fun hasPrefix(x: Any)  = when(x){
        is String -> x.startsWith("prefix")
        else -> false
    }



}