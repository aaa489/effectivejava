package com.example.effectivejava;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;

/**
 * 《effective java》-29：优先考虑泛型
 * 编译期进行强类型检查，避免运行期再抛异常（强制转换带来）
 * 泛型不支持基本类型的使用，可以通过基本类的包装类来用
 * @author Don
 * @date 2022/2/22.
 */
public class GenericFirst {

     /**
       * 对《effective java》-7的泛型改造
       * @author: Don
       * @date: 2022/2/22 14:18
       **/
    public static class Stack<E> {
        private E[] elements;
        private int size = 0;
        private static final int DEFAULT_INITIAL_CAPACITY = 16;

        //记得加注解
        // The elements array will contain only E instances from push(E).
        // This is sufficient to ensure type safety, but the runtime
        // type of the array won't be E[]; it will always be Object[]!
        @SuppressWarnings("unchecked")
        public Stack() {
            elements = (E[])new Object[DEFAULT_INITIAL_CAPACITY];
        }

        public void push(E e) {
            ensureCapacity();
            elements[size++] = e;
        }

        public E pop() {
            if (size == 0) {
                throw new EmptyStackException();
            }
            E result = elements[--size];
            elements[size] = null; // 消除过期引用，防止内存占用
            return result;
        }

        public boolean isEmpty(){
            return size == 0;
        }

        /**
         * Ensure space for at least one more element, roughly
         * doubling the capacity each time the array needs to grow.
         */
        private void ensureCapacity() {
            if (elements.length == size){
                elements = Arrays.copyOf(elements, 2 * size + 1);
            }
        }
    }

    /**
     * 《effective java》-30：优先使用泛型方法
     * 编译期进行强类型检查，避免运行期再抛异常（强制转换带来）
     */

     /**
       * 此方法可以编译但有两个警告
       * @author: Don
       * @date: 2022/2/22 14:33
       **/
    public static Set union(Set s1, Set s2) {
        Set result = new HashSet(s1);
        result.addAll(s2);
        return result;
    }

     /**
       * 使用泛型方法来进行改造
       * @author: Don
       * @date: 2022/2/22 14:34
       **/
    public static <E> Set<E> union1(Set<E> s1, Set<E> s2) {
        Set<E> result = new HashSet<>(s1);
        result.addAll(s2);
        return result;
    }

     /**
       * 泛型单例模式
       * @author: Don
       * @date: 2022/2/22 14:59
       **/
    private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

    //不会抛出异常，因为恒等返回
    @SuppressWarnings("unchecked")
    public static <T> UnaryOperator<T> identityFunction() {
        return (UnaryOperator<T>) IDENTITY_FN;
    }

    public static void main(String[] args) {
        String[] strArgs = { "A", "B", "C" };
        Stack<String> stack = new Stack<>();
        for (String arg : strArgs) {
            stack.push(arg);
        }
        while (!stack.isEmpty()) {
            System.out.println(stack.pop().toUpperCase());
        }

        //下面测试的是条目30的内容
        String[] strings = { "jute", "hemp", "nylon" };
        UnaryOperator<String> sameString = identityFunction();
        for (String s : strings) {
            System.out.println(sameString.apply(s));
        }
        Number[] numbers = { 1, 2.0, 3L };
        UnaryOperator<Number> sameNumber = identityFunction();
        for (Number n : numbers) {
            System.out.println(sameNumber.apply(n));
        }
    }
}
