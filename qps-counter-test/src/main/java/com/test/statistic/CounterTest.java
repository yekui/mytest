package com.test.statistic;


import com.test.statistic.metric.ArrayMetric;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class CounterTest {

    static ConcurrentHashMap<String, ArrayMetric> map = new ConcurrentHashMap();

    public static void main(String[] args) throws Exception {

        String prefix = "test_";

        int sum = 0;
        boolean beyond = false;
        int sleepCount = 0;
        for (int i = 0; i < 10; i++) {
            String key = prefix;

            int times = 5;
            if (i % 2 == 0) {
                times = new Random().nextInt(10);
            }

            System.out.println("key :" + key + " times:" + times + " beyond -->" + beyond);
            System.out.println("====");

            sum += times;

            for (int a = 0; a < times; a++) {
                ArrayMetric bucket = null;
                if (map.putIfAbsent(key, new ArrayMetric(1000, 5)) == null) {
                    bucket = map.get(key);
                } else {
                    bucket = map.get(key);
                }

                bucket.addCount();
                int sleep = new Random().nextInt(1000);
                sleepCount += sleep;
                Thread.sleep(sleep);
                if (sleepCount > 5000) {
                    System.out.println("超过5s");
                    beyond = true;
                }
            }
        }


        System.out.println("finished -- > sums: " + sum);
        for (ConcurrentHashMap.Entry<String, ArrayMetric> m : map.entrySet()) {
            System.out.println("key :" + m.getKey() + " value:" + m.getValue().count());
        }
    }
}
