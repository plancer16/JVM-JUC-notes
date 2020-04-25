package JVM;

import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @author plancer16
 * @create 2020-04-23 10:27
 */
public class WeakHashMapDemo {
    public static void main(String[] args) {
        myWeakHashMap();
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key,value);
        System.out.println(map);
        key=null;//key是指向2的引用，不影响map的node键值对的key
        System.out.println(map);
        System.gc();//弱引用不管内存够不够都回收
        System.out.println(map+" "+map.size());
    }
}
