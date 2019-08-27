package com.fuhuang.firstkotlindemo

/**
 * @author zhangdan
 * on 2019/8/19
 * 循环控制
 */
class LoopControl {

    // for 循环
    // 可以对任何提供迭代器（iterator）的对象进行遍历，语法如下:
    //      for (item in collection) print(item)
    // 循环体可以是一个代码块:
    //      for (item: Int in ints)
    // 通过索引遍历一个数组或者一个 list
    //      for (i in array.indices) {
    //          print(array[i])
    //      }
    // 用库函数 withIndex
    //      for ((index, value) in array.withIndex()) {
    //          println("the element at $index is $value")
    //      }
    fun forMain(){
        val items = listOf("apple","banana","kiwi")
        for (item in items){
            print(item)  // 结果 apple banana kiwi
        }
        for (index in items.indices){
            print("item at $index is ${items[index]}") // 结果 item at 0 is apple -- item at 1 is banana -- item at 2 is kiwi
        }
    }

    // while 与 do...while 循环
    // do…while 循环 对于 while 语句而言，如果不满足条件，则不能进入循环。但有时候我们需要即使不满足条件，也至少执行一次。
    // do…while 循环和 while 循环相似，不同的是，do…while 循环至少会执行一次。
    // while循环结构
    //      while(布尔条件表达式){循环内容}
    // do...while循环结构
    //      do{代码语句}while(布尔表达式);
    fun testWhile(){
        // while
        var x = 5
        while (x > 0){
            x--
        }
        // do...while
        var y = 5
        do {
            y--
        }while (y>0)
    }

    // 循环的返回和跳转
    // Kotlin 有三种结构化跳转表达式：
    //      return。默认从最直接包围它的函数或者匿名函数返回。
    //      break。终止最直接包围它的循环。
    //      continue。继续下一次最直接包围它的循环。
    fun main1() {
        for (i in 1..10) {
            if (i==3) continue  // i 为 3 时跳过当前循环，继续下一次循环
            println(i)
            if (i>5) break   // i 为 6 时 跳出循环
        }
    }

    // Break 和 Continue 标签
    //  在 Kotlin 中任何表达式都可以用标签（label）来标记。
    //  标签的格式为标识符后跟 @ 符号，例如：abc@、fooBar@都是有效的标签。 要为一个表达式加标签，我们只要在其前加标签即可。
    //      loop@ for (i in 1..100) {
    //          // ……
    //      }
    // 用标签限制 break 或者continue：
    //      loop@ for (i in 1..100) {
    //          for (j in 1..100) {
    //              if (……) break@loop
    //          }
    //      }

    // 标签出返回
    // Kotlin 有函数字面量、局部函数和对象表达式。因此 Kotlin 的函数可以被嵌套。标签限制的 return 允许我们从外层函数返回。 最重要的一个用途就是从 lambda 表达式中返回。
    // fun foo() {
    //    ints.forEach {
    //        if (it == 0) return 此时会直接从foo方法中返回
    //        print(it)
    //    }
    // }
    // fun foo() {
    //    ints.forEach lit@ {
    //        if (it == 0) return@lit 此时会从forEach的lambda处返回
    //        print(it)
    //    }
    // }
    // 隐式标签返回
    // // fun foo() {
    //    //    ints.forEach {
    //    //        if (it == 0) return@forEach
    //    //        print(it)
    //    //    }
    //    // }
    // 匿名函数替代 lambda 表达式
    // fun foo() {
    //    ints.forEach(fun(value: Int) {
    //        if (value == 0) return
    //        print(value)
    //    })
    // }
}