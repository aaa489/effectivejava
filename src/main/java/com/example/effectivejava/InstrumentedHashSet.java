package com.example.effectivejava;

import java.util.Collection;
import java.util.HashSet;

/**
 * 《effective java》-18：组合优先于继承
 * 继承虽然强大，但它存在一些问题，因为它违反了封装，通过改变父类的行为会破会子类的行为，导致代码脆弱。
 * 它只有在子类和父类之间存在真正的子类型关系的时候才使用。
 * 这个例子，如果调用addAll(Arrays.asList("1","2","3"))会得到结果6，因为调用HashSet的addAll方法时又去调用在InstrumentedHashSet类中重写的add方法
 * 《effective java》-19： 若要设计继承，则提供文档说明，否则禁止继承
 * 1、必须在这个类的文档里为每个方法说明覆盖带来的影响
 * 2、构造器一定不能调用可覆盖方法，无论是直接点用还是间接调用
 * 3、如果你决定让设计用来被继承的类实现Serializable接口，而且这个类拥有readResolve方法或writeReplace方法，
 * 你一定要把readResolve方法或writeReplace方法设为受保护的，而不是私有的，如果这些方法是私有的，它们将会被子类忽略掉
 * 阻止子类化的方式：
 * 1、将这个类声明为final
 * 2、将所有的构造器设为私有或者包级私有，同时添加公有静态工厂来替代构造器
 * @author Don
 * @date 2022/2/17.
 */
public class InstrumentedHashSet<E> extends HashSet<E> {
    // The number of attempted element insertions
    private int addCount = 0;
    public InstrumentedHashSet() {
    }

    public InstrumentedHashSet(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

     /**
       * 使用组合，为了方便放在一起
       * @author: Don
       * @date: 2022/2/17 19:45
       **/
    public static class InstrumentedSet<E> {
        private int addCount = 0;
        private HashSet<E> hashSet = new HashSet<>();

        public boolean add(E e) {
            addCount++;
            return hashSet.add(e);
        }

        public boolean addAll(Collection<? extends E> c) {
            addCount += c.size();
            return hashSet.addAll(c);
        }

        public int getAddCount() {
            return addCount;
        }
    }

     /**
       * 构造器中调用了可覆盖方法，错误的行为
       * @author: Don
       * @date: 2022/2/18 14:21
       **/
    public class Super {
        // Broken - constructor invokes an overridable method
        public Super() {
            overrideMe();
        }
        public void overrideMe() { }
    }
}
