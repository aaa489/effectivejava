package com.example.effectivejava;

import java.util.Comparator;

/**
 * 《effective java》-14：考虑是否实现comparable
 * 如果你正在写一个明显具有自然顺序的值类，如字母顺序，数字大小顺序或者时间先后顺序，那么你就应该实现Comparable接口
 * 确保符合以下要求：
 * 1、sgn(x.compareTo(y)) == -sgn(y. compareTo(x))成立
 * 2、若(x. compareTo(y) > 0 && y.compareTo(z) > 0)，则x.compareTo(z)>0
 * 3、若x.compareTo(y) == 0，则对于所有的z，sgn(x.compareTo(z)) == sgn(y.compareTo(z))成立
 * 4、强烈建议(x.compareTo(y) == 0) == (x.equals(y))成立，但这并不是必须的
 * 通常来说，任何实现了Comparable接口的类如果违反了这个条件，那么应该做个说明。推荐的说法是“注意：该类具有自然排序，但是与equals方法不一致。”
 * 因为有些集合是基于equals做同等性测试而不是compareTo方法
 * @author Don
 * @date 2022/2/14.
 */
public class MyNumber implements Comparable<MyNumber> {
    private Integer a;

    //比较器进行比较
    private Comparator<Integer> comparator = Comparator.comparing((a) -> a);

    public Integer getA() {
        return a;
    }

    public void setA(Integer a) {
        this.a = a;
    }

    @Override
    public int compareTo(MyNumber o) {
        //return comparator.compare(a, o.getA());
        return Integer.compare(a, o.getA());
    }
}
