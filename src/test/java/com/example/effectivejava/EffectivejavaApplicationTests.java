package com.example.effectivejava;

import com.example.effectivejava.enums.SingleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Comparator;

@SpringBootTest
class EffectivejavaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testSingle(){
        System.out.println(SingleEnum.INSTANCE.createId());
    }

    @Test
    void testClone(){
        MyCloneObj myCloneObj = new MyCloneObj();
        myCloneObj.setI(1);
        myCloneObj.push();
        MyCloneObj myCloneObj1 = (MyCloneObj)myCloneObj.clone();
        myCloneObj1.pop();
        System.out.println("equal: " + myCloneObj.equals(myCloneObj1));
        System.out.println("getClass: " + (myCloneObj.getClass() == myCloneObj1.getClass()));
        System.out.println("object: " + (myCloneObj == myCloneObj1));
    }


    @Test
    void testComparable(){
        Comparator<MyNumber> comparator = Comparator.comparing(MyNumber::getA);

        MyNumber number1 = new MyNumber();
        number1.setA(1);
        MyNumber number2 = new MyNumber();
        number2.setA(1);
        //int result = number1.compareTo(number2);
        int result = comparator.compare(number1, number2);
        if (result == 0){
            System.out.println("number1 和 number2 相等");
        }
        else {
            System.out.println("number1 比 number2 " + (result > 0 ? "大" : "小"));
        }
    }
}
