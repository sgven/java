package jvm.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法区和运行时常量池溢出
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * JDK 1.6 及之前的版本中，由于常量池分配在永久代内，我们可以通过 -XX:PermSize 和 -XX:MaxPermSize 限制方法区大小，
 * 从而间接限制其中常量池的容量。
 *
 * Java HotSpot(TM) 64-Bit Server VM warning: ignoring option PermSize=10M; support was removed in 8.0
 *
 * 运行时常量是方法区的一部分，两个区域的溢出放一起测试。
 * 当前很多主流框架，如Spring、Hibernate，在对类进行增强时，都会使用到CGLib这类字节码技术，增强的类越多，就需要越大的方法区来
 * 保证动态生成的Class可以加载入内存。随着这类语言的流行，也会越容易遇到方法区溢出。
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        testIntern();
//        testOOM();
    }

    public static void testOOM() {
        // 使用List保持着常量池引用，避免 Full GC 回收常量池行为
        List<String> list = new ArrayList<String>();
        // 10MB 的 PermSize 在 integer 范围内足够产生 OOM 了
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

    public static void testIntern() {
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
