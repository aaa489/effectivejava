package com.example.effectivejava;

import java.util.ArrayList;
import java.util.List;

/**
 * 《effective java》-54：返回空的数组或集合，不要返回 null
 * 可以使用Collections.emptyList、Collections.emptySet、Collections.emptyMap等方法来返回空集合
 * @author Don
 * @date 2022/3/1.
 */
public class ReturnClass {

    private List<String> list = new ArrayList<>();

    //零长度数组是不可变的，可以重复返回，减少每次分配零长度数组的性能损耗
    private static final String[] zeroArray = new String[0];

    //这里有可能返回null
    public List<String> getCheeses() {
        return list.isEmpty() ? null : new ArrayList<>(list);
    }

    //改进
    public List<String> getCheeses1() {
        return new ArrayList<>(list);
        //return list.isEmpty() ? Collections.emptyList() : new ArrayList<>(list);
    }

    public String[] getArray() {
        return list.toArray(zeroArray);
        // Don’t do this - 带来性能损耗
        //return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        ReturnClass returnClass = new ReturnClass();
        //由于有可能返回null，所以这里需要代码做判断，不是很友好
        if (returnClass.getCheeses() != null) {
            System.out.println("Jolly good, just the thing.");
        }
    }
}
