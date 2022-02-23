package com.example.effectivejava;

import org.omg.CORBA.Object;

import java.util.*;
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
           * 《effective java》-31：使用限定通配符来增加 API 的灵活性
           * 为了获得最大的灵活性，对代表生产者或消费者的输入参数使用通配符类型
           * 助记：producer-extends，consumer-super，如果一个参数化类型代表一个 T 生产者，使用 <? extends T> ；如果它代表 T 消费者，则使用 <? super T>
           * 如果一个类的使用者必须考虑通配符类型，那么该类的 API可能有问题
           * @author: Don
           * @date: 2022/2/23 9:35
           **/

         /**
           * 生产者，因为是生成E实例的，所以传入的类型必须是E的子类
           * 使用Iterable<? extends E>来代替Iterable<E>，限定传入的迭代器中元素类型必须是E的子类
           * @author: Don
           * @date: 2022/2/23 9:25
           **/
         public void pushAll(Iterable<? extends E> src) {
             for (E e : src) {
                 push(e);
             }
         }

          /**
            * 消费者，因为是取出E实例的，所以传入的类型必须是E的父类
            * 使用Collection<? super E>来代替Collection<E>，限定传入的迭代器中元素类型必须是E的父类
            * @author: Don
            * @date: 2022/2/23 9:41
            **/
         public void popAll(Collection<? super E> dst) {
             while (!isEmpty()){
                 dst.add(pop());
             }
         }

         //swap1和swap2为例子，如果类型参数在方法声明中只出现一次，请将其替换为通配符，swap2的声明方式更好
         public static <E> void swap1(List<E> list, int i, int j){

         }
         public static void swap2(List<?> list, int i, int j){

         }

        /**
         * 《effective java》-32：合理地结合泛型和可变参数
         * @SafeVarargs 注解已添加到平台，以允许具有泛型可变参数的方法的作者自动禁止客户端警告，只对不能被重写的方法是合法的
         * 如果选择使用泛型（或参数化）可变参数编写方法，请首先确保该方法是类型安全的，然后使用 @SafeVarargs 注解对其进行标注，以免造成使用不愉快
         * 在下列情况下，泛型可变参数方法是安全的：
         * 1. 它不会在可变参数数组中存储任何东西
         * 2. 它不会使数组（或克隆）对不可信代码可见。 如果违反这些禁令中的任何一项，请修复。
         */

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
     * 《effective java》-33：优先考虑类型安全的异构容器
     * Favorites就是类型安全异构容器，它支持不同的类型参数
     * java.util.Collections 中有一些集合包装类，他们的静态工厂除了一个集合（或 Map ）之外还有一个 Class 对象（或两个）。
     * 静态工厂是泛型方法，确保 Class 对象和集合的编译时类型匹配。
     *
     */
    public class Favorites {
        private Map<Class<?>, Object> favorites = new HashMap<>();

        public <T> void putFavorite(Class<T> type, T instance){
            favorites.put(Objects.requireNonNull(type),  (Object)instance);
        }

        public <T> T getFavorite(Class<T> type){
            //使用cast方法进行转换
            return type.cast(favorites.get(type));
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

        Set<String> hashSet1 = new HashSet<>();
        Set<String> hashSet2 = new HashSet<>();
        //可以像下面这样显示指定泛型类
        Set<String> strings1 = GenericFirst.<String>union1(hashSet1, hashSet2);

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

        //下面测试的是条目31的内容
        Stack<Number> numberStack = new Stack<>();
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        numberStack.pushAll(integers);
    }
}
