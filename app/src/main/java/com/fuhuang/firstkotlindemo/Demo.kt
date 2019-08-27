package com.fuhuang.firstkotlindemo

/**
 * @author zhangdan
 * on 2019/8/20
 *  kotlin 类和对象
 *      可以包含:构造函数、初始化代码块、函数、属性、内部类、对象声明
 */
class Demo {

    // 类的属性定义
    // 使用var声明成可变的，否则使用val声明成不可变的
    var name: String = "zhangsan"
    var url: String = "www.baidu.com"
    var city: String = "hefei"
    // 创建类实例
    val demo1 = Demo()

    // 使用类属性
    fun foo() {
        demo1.name
        demo1.url
        demo1.city
    }

    // -------- 主构造函数---------
    // kotlin类可以有一个 主构造器，以及一个或多个次构造器，主构造器是类头部的一部分，位于类名称之后:
    // 主构造器中不能包含任何代码，初始化代码可以放在初始化代码段中，初始化代码段使用 init 关键字作为前缀。
    class Test constructor(obj: String) {
        init {
            print("obj:$obj")
        }
    }

    // 如果主构造器没有任何注解，也没有任何可见度修饰符，那么constructor关键字可以省略
    class Test1(name: String) {}

    // 在实例初始化期间，初始化块按照它们出现在类体中的顺序执行，与属性初始化器交织在一起
    class InitOrderDemo(name: String) {
        val firstProperty = "First property: $name".also(::println)

        init {
            println("First initializer block that prints ${name}")
        }

        val secondProperty = "Second property: ${name.length}".also(::println)

        init {
            println("Second initializer block that prints ${name.length}")
        }
    }

    fun test0() {
        InitOrderDemo("hello")
        // 输出结果：
        //      First property: hello
        //      First initializer block that prints hello
        //      Second property: 5
        //      Second initializer block that prints 5
    }

    // 主构造的参数可以在初始化块中使用。它们也可以在类体内声明的属性初始化器中使用
    class Customer(name: String) {
        val customerKey = name.toUpperCase()
    }

    // 声明属性以及从主构造函数初始化属性，Kotlin 有简洁的语法：
    class Person2(val firstName: String, val lastName: String, var age: Int) {}
    // 与普通属性一样，主构造函数中声明的属性可以是可变的（var）或只读的（val）
    // 如果构造函数有注解或可见性修饰符，这个 constructor 关键字是必需的，并且这些修饰符在它前面：
//    class Customer2 public @Inject constructor(name: String){}


    // -------- 次构造函数---------
    // 类也可以声明前缀有 constructor的次构造函数：
    class Person3 {
        var children: MutableList<Person3> = mutableListOf<Person3>()

        constructor(parent: Person3) {
            parent.children.add(this)
        }
    }

    // 如果类有一个主构造函数，每个次构造函数需要委托给主构造函数， 可以直接委托或者通过别的次构造函数间接委托。
    // 委托到同一个类的另一个构造函数用 this 关键字即可
    class Person4(val name: String) {
        var children: MutableList<Person4> = mutableListOf()

        constructor(name: String, parent: Person4) : this(name) {
            parent.children.add(this)
        }
    }
    // 注：初始化块中的代码实际上会成为主构造函数的一部分。
    // 委托给主构造函数会作为次构造函数的第一条语句，因此所有初始化块中的代码都会在次构造函数体之前执行。
    // 即使该类没有主构造函数，这种委托仍会隐式发生，并且仍会执行初始化块
    class Constructors {
        init {
            println("Init block")
        }

        constructor(i: Int) {
            println("Constructor")
        }
    }
    fun test3(){
        Constructors(1)
        // 输出结果：
        // Init block
        // Constructor
    }
    // 如果一个非抽象类没有声明任何（主或次）构造函数，它会有一个生成的不带参数的主构造函数。构造函数的可见性是 public。
    // 如果你不希望你的类有一个公有构造函数，你需要声明一个带有非默认可见性的空的主构造函数：
    //      class DontCreateMe private constructor () { ... }
    // 注意：在 JVM 上，如果主构造函数的所有的参数都有默认值，编译器会生成 一个额外的无参构造函数，它将使用默认值。
    // 这使得 Kotlin 更易于使用像 Jackson 或者 JPA 这样的通过无参构造函数创建类的实例的库。
    //      class Customer(val customerName: String = "")


    // getter setter
    // 属性声明getter setter完整语法：
    // var <propertyName>[: <PropertyType>] [= <property_initializer>]
    //    [<getter>]
    //    [<setter>]
    // getter 和 setter 都是可选
    // 如果属性类型可以从初始化语句或者类的成员函数中推断出来，那就可以省去类型，val不允许设置setter函数，因为它是只读的。
    // var allByDefault: Int? // 错误: 需要一个初始化语句, 默认实现了 getter 和 setter 方法
    // var initialized = 1    // 类型为 Int, 默认实现了 getter 和 setter
    // val simple: Int?       // 类型为 Int ，默认实现 getter ，但必须在构造函数中初始化
    // val inferredType = 1   // 类型为 Int 类型,默认实现 getter
    class Person {
        var lastName: String = "zhang"
            get() = field.toUpperCase() //调用get方法时，将变量转换为大写
            set

        var no: Int = 100
            get() = field           // 后端变量
            set(value) {
                if (value < 10) {    // 如果传入的值小于 10 返回该值
                    field = value
                } else {            // 如果传入的值大于等于 10 返回 -1
                    field = -1
                }
            }

        var height: Float = 100.1f
            private set
    }

    fun test() {
        var person: Person = Person()
        person.lastName = "wang"
        print("lastName:${person.lastName}") // 结果为lastName:WANG
        person.no = 9
        println("no:${person.no}") //结果是no:9
        person.no = 20
        println("no:${person.no}") //结果是no:-1
    }


}


