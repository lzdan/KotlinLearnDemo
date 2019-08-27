package com.fuhuang.firstkotlindemo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        vars(1,2,3,4,56,7)
//        Log.d("ZD", "  " + sumLambda(1, 2))
//        stringMethod()
//        nullMethod()

        zoneRange()
    }


    fun sum(x: Int, y: Int): Int {
        return x + y
    }

    fun sum1(x: Int, y: Int) = x + y
    fun sum2(x: Int, y: Int): Int = x + y
    public fun sum3(x: Int, y: Int): Int = x + y
    public fun sum4(x: Int, y: Int) = x + y

    // 无返回值：Unit
    fun printNum(x: Int, y: Int): Unit {}

    fun printNum2(x: Int, y: Int) {}
    public fun printNum3(x: Int, y: Int) {}

    // 可变长参数函数
    fun vars(vararg v: Int) {
        for (v1 in v) {
            Log.d("ZD", "vt=$v1")
        }
    }

    // lambda(匿名函数)
    //lambda表达式使用实例：
    val sumLambda: (Int, Int) -> Int = { x, y -> x + y }

    fun params() {
        // 定义常量与变量
        // 可变变量定义：var关键字
        // var <标识符> : <类型> = <初始化值>
        // 不可变变量定义：val关键字，只能赋值一次的变量（类似Java中的final修饰的变量）
        val a: Int = 1
        val b: Int // 如果不在声明时初始化则必须提供变量类型
        b = 1  // 明确赋值
        // b = 2 // 不可再指定
        val c = 1 // 系统自动推断变量类型为Int

        var x = 5 // 系统自动推断变量类型为Int
        x += 1 // 可变变量可修改值
    }


    // 字符串模板
    private fun stringMethod() {
        // $ 表示一个变量名或者变量值
        //$varName 表示变量值
        //${varName.fun()} 表示变量的方法返回值:
        var a = 1
        // 模板中的简单名称：
        var s1 = "a is $a" // 输出结果 a is 1
        Log.d("ZD", "s1 = " + s1)

        a = 2
        // 模板中的任意表达式：
        var s2 = "${s1.replace("is", "was")},but now a is $a" //输出结果 a was 1,but now a is 2
        Log.d("ZD", "s2 = " + s2)
    }

    // null机制检测
    fun nullMethod() {
//        var age: String = null
//        var age: String? = null // 类型后面加?表示可为null
        var age:String? = null // 类型后面加?表示可为null
//        val age1 = age!!.toInt() // 抛出空指针异常 !!
        val age2 = age?.toInt() // 返回null，不处理
        val age3 = age?.toInt() ?: -1 // 如果为null,返回-1
//        Log.d("ZD","--- "+forParseInt(age) + "-- "+age1 + "  "+ age2 + "  === "+age3)
//        Log.d("ZD","--- "+forParseInt(age) + "-- " + "  "+ age2 + "  === "+age3)
        Log.d("ZD", "-- " + "  "+ age2 + "  === "+age3)
    }

    fun forParseInt(str:String?):Int?{
        return str!!.toInt()
    }



    // 类型检测及自动类型转换:使用 is 运算符检测一个表达式是否某类型的一个实例(类似于Java中的instanceof关键字)。
    fun getStringLen(obj : Any):Int?{
        if(obj is String){
            // 做过类型判断之后，obj会被系统自动转换为String
            return obj.length
        }
        // 此时的obj仍是Any的引用
        return null

//        if(obj !is String){
//            return null
//        }
//        return obj.length
    }


    // 区间：区间表达式由具有操作符形式 .. 的 rangeTo 函数辅以 in 和 !in 形成。
    // 区间是为任何可比较类型定义的，但对于整型原生类型，它有一个优化的实现。
    fun zoneRange(){
        for (i in 1..4){  // 输出“1234”

            Log.d("ZD","i1="+i)
        }
        for (i in 4..1){  // 什么都不输出
            Log.d("ZD","i2 = "+i)
        }
        for (i in 1..10) { // 等同于 1 <= i && i <= 10
            Log.d("ZD","i3 = "+i)
        }

        // 使用 step 指定步长
        for (i in 1..4 step 2){
            Log.d("ZD","i4 = "+i)
        } // 输出“13”

        for (i in 4 downTo 1 step 2) {
            Log.d("ZD","i5 = "+i)
        }// 输出“42”


        // 使用 until 函数排除结束元素
        for (i in 1 until 10) {   // i in [1, 10) 排除了 10
            Log.d("ZD","i6 = "+i)
        }
    }






}
