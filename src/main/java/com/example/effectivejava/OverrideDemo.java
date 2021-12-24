package com.example.effectivejava;

import com.sun.org.apache.xpath.internal.operations.Equals;

/**
 * 覆盖Equal方法要遵从以下规约：
 * 自反性：对于任意非空引用值x，x.equals(x)必须返回true。
 * 对称性：对于任意非空引用值x和y，x.equals(y)必须返回true，当且仅当y.equals(x)返回true。
 * 传递性：对于任意非空引用值x，y，z，如果x.equals(y)返回true而且y.equals(z)也返回true，那么x.equals(z)必须返回true。
 * 一致性：对于任意非空引用值x和y，只要equals方法中使用的信息没有被修改，那么不管多少次调用x.equals(y)都必须一致性地返回true或者false。
 * 对于任意非空引用值x，x.equals(null)必须返回false。
 * -------------------------------------------------
 * 覆盖equals方法时总要覆盖hashCode方法
 * 只要equals方法里用到的信息没有被更改，那么在一个应用的执行期间，不管调用hashCode方法多少次，hashCode方法都必须持续返回相同的值。但在一个应用程序的多次执行过程中，这个值可以不一致。
 * 若两个对象根据equals(Object)方法比较是相等的，那么分别调用这两个对象的hashCode方法时必须产生相同的整型数值。
 * 若两个对象根据equals(Object)方法比较是不等的，那么分别调用这两个对象的hashCode方法时不一定要产生不同的数值。只是程序员应该知道，对于不等的对象若能产生不同的哈希值，有助于提高哈希表的性能。
 * -------------------------------------------------
 *
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
}
