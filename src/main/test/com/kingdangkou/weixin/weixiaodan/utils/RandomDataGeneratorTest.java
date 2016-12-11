package com.kingdangkou.weixin.weixiaodan.utils;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

public class RandomDataGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            int random = RandomDataGenerator.generate();
            if (randoms.contains(random)) assertFalse(true);
            randoms.add(random);
        }
    }

    @Test
    public void testGenerate100() throws Exception {
        ArrayList<Integer> randoms = new ArrayList<Integer>();
        for (int i = 0; i < 100; i++) {
            int random = RandomDataGenerator.generate();
            if (randoms.contains(random)) {
                System.out.println(i);
                assertFalse(true);
            }
            randoms.add(random);
        }
    }
}