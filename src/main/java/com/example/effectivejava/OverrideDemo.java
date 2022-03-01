package com.example.effectivejava;

import java.math.BigInteger;
import java.util.*;

/**
 * 《effective java》-10：覆盖equals方法时请遵守通用约定
 * 1）覆盖Equal方法要遵从以下规约：
 * 自反性：对于任意非空引用值x，x.equals(x)必须返回true。
 * 对称性：对于任意非空引用值x和y，x.equals(y)必须返回true，当且仅当y.equals(x)返回true。
 * 传递性：对于任意非空引用值x，y，z，如果x.equals(y)返回true而且y.equals(z)也返回true，那么x.equals(z)必须返回true。
 * 一致性：对于任意非空引用值x和y，只要equals方法中使用的信息没有被修改，那么不管多少次调用x.equals(y)都必须一致性地返回true或者false。
 * 对于任意非空引用值x，x.equals(null)必须返回false。
 * -------------------------------------------------
 * 《effective java》-11：覆盖equals方法时总要覆盖hashCode方法
 * 只要equals方法里用到的信息没有被更改，那么在一个应用的执行期间，不管调用hashCode方法多少次，hashCode方法都必须持续返回相同的值。但在一个应用程序的多次执行过程中，这个值可以不一致。
 * 若两个对象根据equals(Object)方法比较是相等的，那么分别调用这两个对象的hashCode方法时必须产生相同的整型数值。
 * 若两个对象根据equals(Object)方法比较是不等的，那么分别调用这两个对象的hashCode方法时不一定要产生不同的数值。只是程序员应该知道，对于不等的对象若能产生不同的哈希值，有助于提高哈希表的性能。
 * -------------------------------------------------
 * 《effective java》-12：始终要覆盖toString
 * 描述信息应该更简洁、有用
 * -------------------------------------------------
 *《effective java》-40：始终使用 Override 注解
 * 应该在你认为要重写父类声明的每个方法声明上使用 Override 注解。这条规则有一个小例外， 如果
 * 正在编写一个没有标记为抽象的类，并且确信它重写了其父类中的抽象方法。编译器可以保护免受很多错误的影响
 * @author Don
 * @date 2021/12/24.
 */
public class OverrideDemo {

    private int i;
    private String s;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    /**
       * 如果不想让别人调用，则抛异常
       **/
//    @Override
//    public boolean equals(Object o){
//        throw new AssertionError(); // Method is never called
//    }

    @Override
    public boolean equals(Object o){
        if (o instanceof OverrideDemo){
            return (this.i == ((OverrideDemo) o).getI() && this.s.equals(((OverrideDemo) o).getS()));
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 31 * Integer.hashCode(this.i) + this.s.hashCode();
    }

    @Override
    public String toString(){
        return String.format("%s:%s", i, s);
    }

    public static class Bigram {
        private final char first;
        private final char second;

        public Bigram(char first, char second) {
            this.first = first;
            this.second = second;
        }

        //如果是重写父类的方法，要加注解@Override，否则就是覆盖
        public boolean equals(Bigram b) {
            return b.first == first && b.second == second;
        }

        @Override
        public int hashCode() {
            return 31 * first + second;
        }
    }

     /**
       *《effective java》-52：明智审慎地使用重载
       * 重载（overloaded）方法之间的选择是静态的（编译时），而重写（overridden）方法之间的选择是动态的（运行时）
       * 一个安全和保守的策略是永远不要导出两个具有相同参数数量的重载
       * 总是可以为方法赋予不同的名称，而不是重载它们（如 writeBoolean(boolean) 、 writeInt(int)）
       * 不要在相同参数位置重载采用不同函数式接口的方法
       * @author: Don
       * @date: 2022/3/1 10:00
       **/
    public static class CollectionClassifier {
        public static String classify(Set<?> s) { return "Set"; }
        public static String classify(List<?> lst) { return "List"; }
        public static String classify(Collection<?> c) { return "Unknown Collection"; }

        //打印了三次 Unknown Collection 字符串，因为重载是编译时选择方法
        public static void testOverload(){
            Collection<?>[] collections = {
                    new HashSet<String>(),
                    new ArrayList<BigInteger>(),
                    new HashMap<String, String>().values()
            };
            for (Collection<?> c : collections) {
                System.out.println(classify(c));
            }
        }

        //做了自动装箱，打印[-3, -2, -1] [-2, 0, 2]，不符合预期
        public static void testRemove(){
            Set<Integer> set = new TreeSet<>();
            List<Integer> list = new ArrayList<>();
            for (int i = -3; i < 3; i++) {
                set.add(i);
                list.add(i);
            }
            for (int i = 0; i < 3; i++) {
                set.remove(i);
                list.remove(i);
            }
            System.out.println(set + " " + list);
        }

        public static void main(String[] args) {
            CollectionClassifier.testOverload();
            CollectionClassifier.testRemove();
        }
    }

    //这里没加注解@Override，所以最终输出结果可能不是你想要的
    public static void main(String[] args) {
        Set<Bigram> s = new HashSet<>();
        for (int i = 0; i < 10; i++) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                s.add(new Bigram(ch, ch));
            }
            System.out.println(s.size());
        }
    }
}
