package com.robin.encuentas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EncuestasApplicationTests {

    @Test
    void contextLoads() {
        Assertions.assertEquals(41,Integer.sum(19,22));
    }

}
