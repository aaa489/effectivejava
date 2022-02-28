package com.example.effectivejava;

import java.util.Date;

/**
 * 《effective java》-50：必要时进行防御性拷贝
 * 必须防御性地编写程序，假定类的客户端尽力摧毁类其不变量
 * 参数的防御性拷贝不仅仅适用于不可变类，如果你在设计的时候认为对象是不该改变的，则应该进行防御性拷贝对象
 * @author Don
 * @date 2022/2/28.
 */
public class DefenseCode {

     /**
       * 通过外部引用Date会破坏内部的不可变变量
       * Date 已过时，不应再在新代码中使
       * 不能用Date的clone方法，因为 Date 是非 final 的，所以 clone 方法不能保证返回类为 java.util.Date 的对象,它可以返回一个不受信任的子类的实例
       * @author: Don
       * @date: 2022/2/28 17:51
       **/
    public static final class Period {
        private final Date start;
        private final Date end;

        /**
         * @param start the beginning of the period
         * @param end   the end of the period; must not precede start
         * @throws IllegalArgumentException if start is after end
         * @throws NullPointerException     if start or end is null
         */
        public Period(Date start, Date end) {
            if (start.compareTo(end) > 0) {
                throw new IllegalArgumentException(start + " after " + end);
            }
            this.start = start;
            this.end = end;

            //通过new新的对象来进行防御
//            this.start = new Date(start.getTime());
//            this.end = new Date(end.getTime());
        }

        public Date start() {
            return start;
            //外部可以通过start方法来进行改变内部对象
            //return new Date(start.getTime());
        }

        public Date end() {
            return end;
            //外部可以通过start方法来进行改变内部对象
            //return new Date(start.getTime());
        }
    }

    public static void main(String[] args) throws Exception {
        //进行可变性破坏
        Date start = new Date();
        Date end = new Date();
        Period period = new Period(start, end);
        end.setYear(78);

        //使用LocalDateTime来代替，LocalDateTime是不可变的
//        LocalDateTime start = LocalDateTime.now();
//        LocalDateTime end = LocalDateTime.now();
//        Period period = new Period(start, end);
//        end.plusDays(12);
    }
}
