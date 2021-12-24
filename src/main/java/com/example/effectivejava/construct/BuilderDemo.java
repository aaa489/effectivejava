package com.example.effectivejava.construct;

/**
 * 构造器模式来代替参数比较多的类构造
 * 1、构造时赋值清晰
 * 2、构造链路不会断
 * 缺点就是额外有builder对象
 * @author Don
 * @date 2021/12/23.
 */
public class BuilderDemo {
    private final int servingSize;
    private final int servings;
    private final int calories;
    private final int fat;
    private final int sodium;
    private final int carbohydrate;

    public static class Builder {
        // Required parameters
        private final int servingSize;
        private final int servings;
        // Optional parameters - initialized to default values
        private int calories = 0;
        private int fat = 0;
        private int sodium = 0;
        private int carbohydrate = 0;
        public Builder(int servingSize, int servings) {
            this.servingSize = servingSize;
            this.servings = servings;
        }
        public Builder calories(int val){
            calories = val; return this;
        }
        public Builder fat(int val){
            fat = val; return this;
        }
        public Builder sodium(int val){
            sodium = val; return this;
        }
        public Builder carbohydrate(int val){
            carbohydrate = val; return this;
        }
        public BuilderDemo build() {
            return new BuilderDemo(this);
        }
    }
    private BuilderDemo(Builder builder) {
        servingSize = builder.servingSize;
        servings = builder.servings;
        calories = builder.calories;
        fat = builder.fat;
        sodium = builder.sodium;
        carbohydrate = builder.carbohydrate;
    }
}
