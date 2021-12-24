package com.example.effectivejava;

import com.example.effectivejava.enums.SingleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EffectivejavaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testSingle(){
        System.out.println(SingleEnum.INSTANCE.createId());
    }

}
