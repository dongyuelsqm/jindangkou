package com.kingdangkou.weixin.weixiaodan.entity;

import org.junit.Test;

public class TobePurchasedProductEntityTest {
    @Test
    public void TestColumnValue() throws Exception {
        new TobePurchasedProductEntity("11", "11", "color", "number", "aaa");
    }

    @Test(expected = Exception.class)
    public void testColumnWithInvalidColumn() throws Exception {
        new TobePurchasedProductEntity("11", null, "color", "number", "aaa");
    }
}