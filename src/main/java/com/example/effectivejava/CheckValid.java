package com.example.effectivejava;

import java.util.Objects;

/**
 * 《effective java》-49：检查参数有效性
 * 对于公共方法和受保护方法，请使用 Java 文档 @throws 注解来记在在违反参数值限制时将引发的异常（条目
 * 74）。 通常，生成的异常是 IllegalArgumentException ， IndexOutOfBoundsException 或 NullPointerException （条目 72）
 * 你应该检查要存储起来供以后使用的参数的有效性
 * @author Don
 * @date 2022/2/28.
 */
public class CheckValid {

    private String strategy;

    //使用Objects.requireNonNull来做非空校验
    public void check(String strategy){
        this.strategy = Objects.requireNonNull(strategy, "strategy");
    }

    //使用断言assert，断言如果失败会抛出 AssertionError，使用 -ea (或者-enableassertions）标记传递给java 命令来启用它们，否则它们不会产生任何效果
    private static void sort(long a[], int offset, int length) {
        assert a != null;
        assert offset >= 0 && offset <= a.length;
        assert length >= 0 && length <= a.length - offset;
    }
}
