package map;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

    private static Map<String, String> apiInvokerMap = new ConcurrentHashMap<String, String>();

    public static void main(String[] args) {
        String key = null;
        try {
            if (apiInvokerMap.containsKey(key)) {
                System.out.println("yes");
            } else {
                System.out.println("no");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
