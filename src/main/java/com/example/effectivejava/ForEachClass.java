package com.example.effectivejava;

/**
 *《effective java》-58：for-each 循环优于传统 for 循环
 * for 循环比 while 循环更好（详见第 57 条）
 * for-each不仅更清晰和灵活，而且没有性能损失
 * 三种常见的情况不能使用：
 * 1、有损过滤（Destructive filtering）——如果需要遍历集合，并删除指定选元素，则需要使用显式迭代器，以便可以调用其 remove 方法
 * 2、转换——如果需要遍历一个列表或数组并替换其元素的部分或全部值，那么需要列表迭代器或数组索引来替换元素的值
 * 3、并行迭代——如果需要并行地遍历多个集合，那么需要显式地控制迭代器或索引变量，以便所有迭代器或索引变量都可以同步进行
 * @author Don
 * @date 2022/3/2.
 */
public class ForEachClass {
    public void useFor() {
        int[] a = new int[]{1,2,3,4,5};

        //迭代器和索引变量都很混乱，容易出错
        for (int i = 0; i < a.length; i++) {
            System.out.println(i);
        }

        //使用for-each（官方称为增加for）
        for (int i : a) {
            System.out.println(i);
        }
    }
}
