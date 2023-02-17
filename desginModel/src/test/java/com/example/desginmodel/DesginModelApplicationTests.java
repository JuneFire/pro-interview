package com.example.desginmodel;

import com.example.abstractDemo.Demo1;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesginModelApplicationTests {

    @Test
    void contextLoads() {
        Demo1 demo1 = new Demo1();
        demo1.test_p();

    }

    @Test
    void TestContext() {
       int a = 1;
       System.out.println(a++ == 1);
       String b = "aa::bb::";
        System.out.println(b.split("::")[3]);

    }

}
