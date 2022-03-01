package com.example.effectivejava;

import java.util.Objects;

/**
 * 《effective java》-6：依赖注入优于硬连接资源（hardwiring resources）
 * 如果实现依赖于底层资源，不要使用工具类或单例类引入，而使用依赖注入模式，具体资源由外部来注入，这样更灵活
 * @author Don
 * @date 2022/3/1.
 */
public class DIClass {
    private final Lexicon dictionary;

    //由外部注入，使用什么字典就比较灵活
    public DIClass(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }

    public static class Lexicon {

    }
}
