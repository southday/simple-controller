package southday.j2eework.water.ustc;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(1);
        map.put("xx", "yy");
        map.put("vv", "aa");
        System.out.println(map);
    }
}
