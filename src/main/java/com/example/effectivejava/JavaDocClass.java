package com.example.effectivejava;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *《effective java》-56：为所有已公开的 API 元素编写文档注释
 * javadoc的使用参考 https://blog.csdn.net/vbirdbest/article/details/80296136
 * 要正确地记录 API，必须在每个导出的类、接口、构造方法、方法和属性声明之前加上文档注释
 * 包级别文档注释应放在名为 package-info.java 的文件中，如果使用模块化系统（条目 15），则应将模块级别注释放在 module-info.java 文件中
 * 无论类或静态方法是否线程安全，都应该在文档中描述其线程安全级别
 * @author Don
 * @date 2022/3/2.
 */
public interface JavaDocClass<E> {

    /** Returns the element at the specified position in this list.
     *
     * <p>This method is <i>not</i> guaranteed to run in constant
     * time. In some implementations it may run in time proportional
     * to the element position.
     *
     * @param index index of element to return; must be
     *             non-negative and less than the size of this list
     * @return the element at the specified position in this list
     * @throws IndexOutOfBoundsException if the index is out of range
     *         ({@code index < 0 || index >= this.size()})
     */
    E get(int index);

     /**
       * 泛型，枚举和注释需要特别注意文档注释。记录泛型类型或方法时，请务必记录所有类型参数
       * An object that maps keys to values. A map cannot contain
       * duplicate keys; each key can map to at most one value.
       *
       * (Remainder omitted)
       * @param <K> the type of keys maintained by this map
       * @param <V> the type of mapped values
       **/
    public interface Map<K, V> {

    }

     /**
       * 在记录枚举类型时，一定要记录常量，以及类型和任何公共方法。注意，如果文档很短，可以把整个文档注释放在一行
       * An instrument section of a symphony orchestra.
       **/
    public enum OrchestraSection{
        /** Woodwinds, such as flute, clarinet, and oboe. */
        WOODWIND,
        /** Brass instruments, such as french horn and trumpet. */
        BRASS,
        /** Percussion instruments, such as timpani and cymbals. */
        PERCUSSION,
        /** Stringed instruments, such as violin and cello. */
        STRING;
    }

    /**
     * 在为注解类型记录文档时，一定要记录任何成员
     *  Indicates that the annotated method is a test method that
     *  must throw the designated exception to pass.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public @interface ExceptionTest {
        /**
         * The exception that the annotated test method must throw
         * in order to pass. (The test is permitted to throw any
         * subtype of the type described by this class object.)
         */
        Class<? extends Throwable> value();
    }
}
