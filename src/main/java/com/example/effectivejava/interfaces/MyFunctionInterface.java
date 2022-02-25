package com.example.effectivejava.interfaces;

/**
 * 《effective java》-44：优先使用标准的函数式接口
 * java.util.function 包提供了大量标准函数式接口供你使用。 如果其中一个标准函数式接口完成这项工作，则通常应该优先使用它，而不是专门构建的函数式接口
 * 不要试图使用基本的函数式接口来装箱基本类型的包装类而不是基本类型的函数式接口
 * 始终使用 @FunctionalInterface 注解标注你的函数式接口
 *
 * 记住了六个基本接口，就可以在需要它们时派生出其余的接口
 * ------------------|-----------|------------
 *         接口      |     方法   |   示例
 * UnaryOperator<T> |T apply(T t)       |String::toLowerCase
 * BinaryOperator<T>|T apply(T t1, T t2)|BigInteger::add
 * Predicate<T>     |boolean test(T t)  |Collection::isEmpty
 * Function<T,R>    |R apply(T t)       |Arrays::asList
 * Supplier<T>      |T get()            |Instant::now
 * Consumer<T>      |void accept(T t)   |System.out::println
 * @author Don
 * @date 2022/2/25.
 */
public interface MyFunctionInterface {

}
