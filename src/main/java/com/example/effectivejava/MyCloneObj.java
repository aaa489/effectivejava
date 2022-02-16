package com.example.effectivejava;

/**
 * 谨慎地覆盖clone：
 * 实现Object的clone方法必须实现Cloneable接口
 * @author Don
 * @date 2022/2/11.
 */
public class MyCloneObj implements Cloneable {

    private int i;
    private String name;
    private Object[] elements;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyCloneObj(){
        i = 0;
        name = "don";
        elements = new Object[10];
    }

    public void push(){
        elements[0] = new Object();
    }

    public Object pop(){
        Object obj = elements[0];
        elements[0] = null;
        return obj;
    }

    @Override
    public Object clone(){
        try{
            //clone 之后elements会引用同一个对象。造成不安全的后果
            return super.clone();
        }
        catch (CloneNotSupportedException ex){
            return null;
        }
    }
}
