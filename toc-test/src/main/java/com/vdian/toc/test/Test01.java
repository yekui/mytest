package com.vdian.toc.test;

import org.junit.Test;

public class Test01 {
    @Test
    public void test() {
        String[] args = new String[3];
        for (int i = 0; i < args.length; i++) {
            args[i] = i + "";
        }
        String[] newArgs = new String[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, args.length);

        for (int j = 0; j < newArgs.length; j++) {
            System.out.println(newArgs[j]);
        }
    }

}
