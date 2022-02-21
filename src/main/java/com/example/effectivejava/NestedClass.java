package com.example.effectivejava;

/**
 * 《effective java》-24：支持使用静态成员类而不是非静态类
 * 嵌套类应该只存在于其宿主类中，如果一个嵌套类在其他一些情况下是有用的，那么它应该是一个顶级类
 * 4种嵌套类：静态成员类、非静态成员类、匿名类、局部类，后三种都被称为内部类
 * 如果你声明了一个不需要访问宿主实例的成员类，总是把 static 修饰符放在它的声明中，使它成为一个静态成员类，而不是非静态的成员类
 * @author Don
 * @date 2022/2/21.
 */
public class NestedClass {

    private final static String nameA = "static name";
    private final String nameB = "nonstatic name";

     /**
       * 静态成员类
       * 可以看作是一个普通的类，可以访问所有宿主类的成员，甚至是那些被声明为私有类的成员，
       * 常见用途是作为公共帮助类，仅在与其外部类一起使用时才有用
       * @author: Don
       * @date: 2022/2/21 17:19
       **/
    public static class NestedClassA {
        public void printName(){
            System.out.println(nameA);
            //System.out.println(nameB);不能访问非静态成员
        }
    }

     /**
       * 非静态成员类
       * 通过在宿主类的实例方法中调用非静态成员类构造方法来自动建立关联，更占空间
       * 非静态成员类的一个常见用法是定义一个 Adapter [Gamma95]，它允许将外部类的实例视为某个不相关类的实例，比如 Map 的 keySet
       * @author: Don
       * @date: 2022/2/21 17:21
       **/
    public class NestedClassB {
        public void printName(){
            System.out.println(nameA);
            System.out.println(nameB);
        }
    }

    public static void main(String[] args) {
        //测试静态内部类
        NestedClassA nestedClassA = new NestedClassA();
        nestedClassA.printName();

        //测试非静态成员类
        NestedClass nestedClass = new NestedClass();
        NestedClass.NestedClassB nestedClassB = nestedClass.new NestedClassB();
        nestedClassB.printName();

        //定义一个匿名类
        NestedClassA anonymousClassA = new NestedClassA() {
            public void printName(){
                System.out.println("anonymous name");
            }
        };
        anonymousClassA.printName();

         /**
           * 定义一个局部类
           * 一个局部类可以在任何可以声明局部变量的地方声明，并遵守相同的作用域规则
           * @author: Don
           * @date: 2022/2/21 18:02
           **/
        class LocalClass {
            private final String name = "local name";

            public void printName() {
                System.out.println(name);
            }
        }
        LocalClass localClass = new LocalClass();
        localClass.printName();
    }
}
