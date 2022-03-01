package com.example.effectivejava;

/**
 * 《effective java》-53：明智审慎地使用可变参数
 * 代码容易出缺陷并且是运行时的
 * 在性能关键的情况下使用可变参数时要小心。每次调用可变参数方法都会导致数组分配和初始化
 * @author Don
 * @date 2022/3/1.
 */
public class VariableArg {

    //如果客户端在没有参数的情况下调用此方法，则它在运行时而不是在编译时失败
    private int sum(int... args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Too few arguments");
        }
        int min = args[0];
        for (int i = 1; i < args.length; i++) {
            if (args[i] < min) {
                min = args[i];
            }
        }
        return min;
    }

    //采用两个参数，纠正了前一个示例的所有缺陷
    private int min(int firstArg, int... remainingArgs) {
        int min = firstArg;
        for (int arg : remainingArgs) {
            if (arg < min) {
                min = arg;
            }
        }
        return min;
    }

     /**
       * 假设你已确定 95％ 的调用是三个或更少的参数的方法，那么声明该方法的五个重载，这样来减少性能损耗
       * @author: Don
       * @date: 2022/3/1 11:12
       **/
    public void foo() { }
    public void foo(int a1) { }
    public void foo(int a1, int a2) { }
    public void foo(int a1, int a2, int a3) { }
    public void foo(int a1, int a2, int a3, int... rest) { }
}
