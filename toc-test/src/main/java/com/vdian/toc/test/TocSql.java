package com.vdian.toc.test;

import org.junit.Test;

public class TocSql {

    @Test
    public void testSql() {
        for (int i = 0; i < 256; i++) {
            StringBuilder sb = new StringBuilder("delete from ");
            if (i < 64) {
                sb.append("tocenter_common_0000.");
            } else if (i < 128) {
                sb.append("tocenter_common_0001.");
            } else if (i < 192) {
                sb.append("tocenter_common_0002.");
            } else if (i < 256) {
                sb.append("tocenter_common_0003.");
            }

            sb.append("job_info_" + String.format("%04d", i));
            sb.append(" where app_name = 'fenxiao-deamon';");
            System.out.println(sb);
        }
    }

}
