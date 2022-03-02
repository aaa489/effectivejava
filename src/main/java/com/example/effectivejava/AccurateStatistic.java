package com.example.effectivejava;

import java.math.BigDecimal;

/**
 *《effective java》-60：若需要精确答案就应避免使用 float 和 double 类型
 * float 和 double 类型特别不适合进行货币计算
 * 使用 BigDecimal、int 或 long 进行货币计算
 * @author Don
 * @date 2022/3/2.
 */
public class AccurateStatistic {

    //计算结果是0.399999999999999999
    public static void useDouble(){
        double funds = 1.00;
        int itemsBought = 0;
        for (double price = 0.10; funds >= price; price += 0.10) {
            funds -= price;
            itemsBought++;
        }
        System.out.println(itemsBought +"items bought.");
        System.out.println("Change: $" + funds);
    }

    //得到精确的结果
    public static void useBigDecimal(){
        final BigDecimal TEN_CENTS = new BigDecimal(".10");
        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            funds = funds.subtract(price);
            itemsBought++;
        }
        System.out.println(itemsBought +"items bought.");
        System.out.println("Money left over: $" + funds);
    }

    public static void main(String[] args) {
        useDouble();
        useBigDecimal();
    }
}
