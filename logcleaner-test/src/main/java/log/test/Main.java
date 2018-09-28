package log.test;

import com.vdian.logcleaner.DefaultScheduledLogCleanRegistry;

public class Main {

    public static void main(String[] args) {
        new DefaultScheduledLogCleanRegistry("test", "YYYY-MM-DD", 2);

        try {
            Thread.currentThread().join();
        } catch (Exception e) {
        }

    }
}
