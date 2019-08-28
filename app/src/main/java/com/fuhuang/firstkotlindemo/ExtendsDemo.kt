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


    /**
     * 派生类初始化顺序
     * 在构造派生类新实例过程中，第一步完成其基类的初始化（在之前只有对基类构造函数参数的求值），
     * 因此发生在派生类的初始化逻辑之前
     */
    open class MyBase(val name: String) {
        init {
            println("Initializing Base")
        }

        open val size: Int =
            name.length.also { println("Initializing size in Base: $it") }
    }

    class Derived(name: String, val lastName: String) :
        MyBase(name.capitalize().also { println("Argument for Base: $it") }) {
        init {
            println("Initializing Derived")
        }

        override val size: Int =
            (super.size + lastName.length).also { println("Initializing size for Derived: $it") }
    }

    fun testMyBase(){
        println("Constructing Derived(\"hello\",\"world\")")
        val d = Derived("hello","world")

        /**
         * 输出结果：
         *      Constructing Derived("hello", "world")
                Argument for Base: Hello
                Initializing Base
                Initializing size in Base: 5
                Initializing Derived
                Initializing size in Derived: 10

            从结果可以看出：
                基类构造函数执行时，派生类中声明或覆盖的属性都还没有初始化。
                如果在基类初始化逻辑中（直接或通过另一个覆盖的 open 成员的实现间接）使用了任何一个这种属性，
                那么都可能导致不正确的行为或运行时故障。
                设计一个基类时，应该避免在构造函数、属性初始化器以及 init 块中使用 open 成员。
         */
    }


    /**
     * 调用超类实现
     *      派生类中的代码可以使用super关键字调用其超类的函数与属性访问器实现
     */
    open class Foo2 {
        open fun f() {
            println("Foo2.f()")
        }

        open val x: Int = 1
    }

    class Bar2: Foo2(){
        override fun f() {
            super.f()
            println("Bar2.f()")
        }

        override val x: Int
            get() = super.x + 1

        inner class Baz {
            fun g(){
                super@Bar2.f()         // 使用Bar2的超类Foo2实现f()
                println(super@Bar2.x)  // 使用Bar2的超类Foo2的x的getter
            }
        }
    }

    /**
     * 覆盖规则
     */
    open class A {
        open fun f(){}

        fun a(){}
    }

    interface B {
        fun f(){}

        fun b(){}
    }

    class C(): A() , B{
        override fun f() {
            super<A>.f()
            super<B>.f()
        }
    }


    /**
     * 抽象类
     *      类以及其中的某些成员声明为abstract
     */
    open class Base3{
        open fun f(){

        }
    }

    abstract class TestBase3: Base3(){
        override abstract fun f() // 可以用一个抽象成员覆盖一个非抽象的开放成员
    }
}