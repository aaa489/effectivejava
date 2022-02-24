package com.example.effectivejava.enums;

import java.util.*;

/**
 * 《effective java》-37：使用EnumMap替代序数索引
 * 使用序数来索引数组很不合适：改用 EnumMap
 * @author Don
 * @date 2022/2/24.
 */
public class EnumMapClass {

    public static final List<Plant> garden = new ArrayList<>();

    static {
        garden.add(new Plant("A", Plant.LifeCycle.ANNUAL));
        garden.add(new Plant("B", Plant.LifeCycle.BIENNIAL));
        garden.add(new Plant("C", Plant.LifeCycle.PERENNIAL));
    }

    public static class Plant {
        public enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }
        private final String name;
        private final LifeCycle lifeCycle;
        public Plant(String name, LifeCycle lifeCycle) {
            this.name = name;
            this.lifeCycle = lifeCycle;
        }
        @Override
        public String toString() {
            return name;
        }
    }

     /**
       * 普通实现，存在类型转换问题
       * @author: Don
       * @date: 2022/2/24 10:27
       **/
    public static void printA(){
        Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
        for (int i = 0; i < plantsByLifeCycle.length; i++){
            plantsByLifeCycle[i] = new HashSet<>();
        }

        for (Plant p : garden) {
            plantsByLifeCycle[p.lifeCycle.ordinal()].add(p); // Print the results
        }

        for (int i = 0; i < plantsByLifeCycle.length; i++) {
            System.out.printf("%s: %s%n", Plant.LifeCycle.values()[i], plantsByLifeCycle[i]);
        }
    }

     /**
       * 使用EnumMap实现
       * @author: Don
       * @date: 2022/2/24 10:27
       **/
    public static void printB(){
        Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class);
        for (Plant.LifeCycle lc : Plant.LifeCycle.values()) {
            plantsByLifeCycle.put(lc, new HashSet<>());
        }
        for (Plant p : garden) {
            plantsByLifeCycle.get(p.lifeCycle).add(p);
        }
        System.out.println(plantsByLifeCycle);
    }

    public static void main(String[] args) {
        EnumMapClass.printA();
        EnumMapClass.printB();
    }
}
