package com.fuhuang.firstkotlindemo

import android.content.Context
import android.view.View

/**
 * @author zhangdan
 * on 2019/8/20
 *
 * 继承
 */
class ExtendsDemo {

    // 在 Kotlin 中所有类都有一个共同的超类 Any，这对于没有超类型声明的类是默认超类：
    class Test1 {} // 从Any 隐式继承

    // 注意：Any 并不是 java.lang.Object；尤其是，它除了 equals()、hashCode() 与 toString() 外没有任何成员。
    open class Base {
        // 覆盖方法（重写方法）
        //      Kotlin 对于可覆盖的成员（我们称之为开放）以及覆盖后的成员需要显式修饰符
        open fun v() {}

        fun fv() {}
    }

    class Test2 : Base() {
        override fun v() {
            super.v()
        }

        open fun v2() {} // 将 open 修饰符添加到 final 类（即没有 open 的类）的成员上不起作用
//        fun fv(){} : 父类中的fv()方法没有加open，那么子类中不允许定义相同签名的函数
    }

    open class Test4 : Base() {
        // 标记为 override 的成员本身是开放的，也就是说，它可以在子类中覆盖。如果你想禁止再次覆盖，使用 final 关键字
        final override fun v() {
            super.v()
        }
    }


    // 如果派生类有一个主构造函数，其基类型可以（并且必须） 用基类的主构造函数参数就地初始化。
    class Test3(i: Int) : Base() {}

    // 如果派生类没有主构造函数，那么每个次构造函数必须使用 super 关键字初始化其基类型，或委托给另一个构造函数做到这一点。
    // 注意，在这种情况下，不同的次构造函数可以调用基类型的不同的构造函数：
    class MyView : View {
        constructor(context: Context) : super(context)
    }

    open class Foo {
        open val x: Int
            get() {
                return 1
            }
        open val y: Int
            get() {
                return 2
            }
    }

    // 可以在主构造函数中使用 override 关键字作为属性声明的一部分
    class Bar(override val x: Int) : Foo() {
        override val y: Int
            get() = super.y
    }


    // 派生类初始化顺序

}