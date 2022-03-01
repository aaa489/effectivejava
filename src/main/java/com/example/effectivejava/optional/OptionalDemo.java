package com.example.effectivejava.optional;

import java.util.*;

/**
 * 《effective java》-55：明智审慎地返回 Optional
 * 抛出异常缺点：抛出异常代价很高，因为在创建异常时捕获整个堆栈跟踪，Java每实例化一个Exception，都会对当时的栈进行快照，这是一个相对比较重的操作
 * 如何提高异常性能：参考 https://www.jianshu.com/p/4c62967ce1c6?utm_campaign=maleskine&utm_content=note&utm_medium=seo_notes&utm_source=recommendation
 * 返回null缺点：客户端必须包含特殊情况代码来处理 null 返回的可能性
 * Optional<T> 类表示一个不可变的容器，迫使 API 的用户面对可能没有返回任何值的事实
 * 不适合场景：
 * 1、容器类型，包括集合、映射、Stream、数组和Optional，不应该封装在 Optional中
 * 2、永远不应该返回装箱的基本类型的 Optional，可以使用OptionalInt、OptionalLong 和 OptionalDouble
 *
 * 如果发现自己编写的方法不能总是返回值，并且认为该方法的用户在每次调用时考虑这种可能性很重要，
 * 那么或许应该返回一个 Optional 的方法。但是，应该意识到，返回 Optional 会带来实际的性能后果；对于性能关键
 * 的方法，最好返回 null 或抛出异常。最后，除了作为返回值之外，不应该在任何其他地方中使用 Optional
 * @author Don
 * @date 2022/3/1.
 */
public class OptionalDemo {

    //抛异常方式
    public static <E extends Comparable<E>> E max(Collection<E> c) {
        if (c.isEmpty()) {
            throw new IllegalArgumentException("Empty collection");
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return result;
    }

    //optinal方式返回
    public static <E extends Comparable<E>> Optional<E> max4Optional(Collection<E> c) {
        if (c.isEmpty()) {
            return Optional.empty();
        }
        E result = null;
        for (E e : c) {
            if (result == null || e.compareTo(result) > 0) {
                result = Objects.requireNonNull(e);
            }
        }
        return Optional.of(result);

        //stream本身支持返回Optional，结合stream
        //return c.stream().max(Comparator. naturalOrder());
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>(Arrays.asList("1","2","3"));
        //optional允许使用者自己来做没有返回值的处理，也可以抛出异常，还有orElseGet、filter、map、flatMap和ifPresent等
        String max = OptionalDemo.max4Optional(list).orElse("no words");
        //String max1 = OptionalDemo.max4Optional(list).orElseThrow(() -> new Exception());
    }
}
