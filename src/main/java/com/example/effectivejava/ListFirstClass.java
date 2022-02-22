package com.example.effectivejava;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 《effective java》-28：列表优先于数组
 * 数组是协变的（covariant）：如果 Sub 是 Super 的子类型，则数组类型 Sub[] 是数组类型 Super[] 的子类型
 * 泛型是不变的（invariant）：对于任何两种不同的类型 Type1 和 Type2 ， List<Type1> 既不是 List<Type2> 的子类型也不是父类型
 * 可以在编译期确认错误，避免在运行期抛出异常
 * @author Don
 * @date 2022/2/22.
 */
public class ListFirstClass {

     /**
       * 使用数组实现
       * @author: Don
       * @date: 2022/2/22 13:58
       **/
    public static class Chooser {
        private final Object[] choiceArray;

        public Chooser(Collection choices) {
            choiceArray = choices.toArray();
        }

        //涉及到Object类型和具体类型的强制转换，如果失败则会抛出异常
        public Object choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceArray[rnd.nextInt(choiceArray.length)];
        }
    }

     /**
       * 使用泛型数组
       * @author: Don
       * @date: 2022/2/22 14:02
       **/
    public static class Chooser1<T> {
        private final T[] choiceArray;
        public Chooser1(Collection<T> choices) {
            //这里需要添加注解来消除警告，但不是最好的做法
            choiceArray = (T[])choices.toArray();
        }
        //涉及到Object类型和具体类型的强制转换，如果失败则会抛出异常
        public Object choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceArray[rnd.nextInt(choiceArray.length)];
        }
    }

     /**
       * 使用列表实现
       * 可以看到没有任何警告了
       * @author: Don
       * @date: 2022/2/22 14:03
       **/
    public class Chooser2<T> {
        private final List<T> choiceList;
        public Chooser2(Collection<T> choices) {
            choiceList = new ArrayList<>(choices);
        }
        public T choose() {
            Random rnd = ThreadLocalRandom.current();
            return choiceList.get(rnd.nextInt(choiceList.size()));
        }
    }
}
