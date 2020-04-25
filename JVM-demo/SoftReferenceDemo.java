package JVM;

import java.lang.ref.SoftReference;

/**
 * @author plancer16
 * @create 2020-04-23 12:08
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {
        softRef_Memory_Enough();
    }

    private static void softRef_Memory_Enough() {
        Object o1 = new Object();
        SoftReference<Object> softReference = new SoftReference<>(o1);
        o1=null;
        System.gc();
        System.out.println(softReference);
    }
}
