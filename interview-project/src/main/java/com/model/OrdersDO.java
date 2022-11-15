package com.model;

import lombok.Data;

/**
 * @author zkCheng
 * @date 2022/11/8 16:48
 */
@Data
public class OrdersDO {

    private Long appId;
    private Long tradeAmount;
    private Integer status;
}
