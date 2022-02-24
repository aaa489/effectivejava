package com.example.effectivejava.enums;

import java.util.EnumSet;
import java.util.Set;

/**
 * 《effective java》-36：使用 EnumSet 替代位属性
 * 优点：
 * 1、性能与位属性的性能相当
 * 2、理解起来更方便
 * @author Don
 * @date 2022/2/24.
 */
public class EnumSetClass {

    private int styles;
    private Set<Style> stylesSet;

    public static final int STYLE_BOLD = 1 << 0; // 1
    public static final int STYLE_ITALIC = 1 << 1; // 2
    public static final int STYLE_UNDERLINE = 1 << 2; // 4
    public static final int STYLE_STRIKETHROUGH = 1 << 3; // 8

    public enum Style { BOLD, ITALIC, UNDERLINE, STRIKETHROUGH }

    // Parameter is bitwise OR of zero or more STYLE_ constants
    public void applyStyles(int styles) {
        this.styles = styles;
    }

    public void applyStyles4Enum(Set<Style> stylesSet) {
        this.stylesSet = stylesSet;
    }

    public int getStyles(){
        return this.styles;
    }

    public boolean isContainsStyle(Style style){
        return stylesSet.contains(style);
    }

    public static void main(String[] args){
        EnumSetClass enumSetClass = new EnumSetClass();
        //用位运算（or）加入集合，再用位运算（and）判断是否在集合中存在
        enumSetClass.applyStyles(STYLE_BOLD | STYLE_ITALIC);
        System.out.println((enumSetClass.getStyles() & STYLE_BOLD) == STYLE_BOLD);
        System.out.println((enumSetClass.getStyles() & STYLE_ITALIC) == STYLE_ITALIC);

        //用EnumSet.of()方式来将元素加入集合
        enumSetClass.applyStyles4Enum(EnumSet.of(Style.BOLD, Style.ITALIC));
        System.out.println(enumSetClass.isContainsStyle(Style.BOLD));
        System.out.println(enumSetClass.isContainsStyle(Style.ITALIC));
    }
}
