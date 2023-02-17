package com.pattern.Strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author zkCheng
 * @date 2021/12/3 10:50
 */
@RestController
public class PayController {

    @Autowired
    private PayService payService;

    @RequestMapping("/pay")
    public String pay(String type, String amount) {
        return payService.pay(type, amount);
    }

    @RequestMapping("/hello/{hello2}")
    public String hello(String hello, @PathVariable String hello2) {
        return "........." + hello + hello2;
    }

}
