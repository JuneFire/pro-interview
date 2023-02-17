package com.example.desginmodel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zkCheng
 * @date 2022/11/14 20:10
 */
@Service
public class A {
    @Autowired
    private B b;
}
