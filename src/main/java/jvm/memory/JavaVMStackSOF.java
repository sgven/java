package jvm.memory;

/**
 * 虚拟机栈和本地方法栈溢出
 * VM Args: -Xss128k
 *
 * 实验结果表明：单个线程下，无论是由于栈帧太大还是虚拟机栈容量太小，当内存无法分配的时候，虚拟机抛出的都是StackOverflowError
 *
 *
 * 如果是多线程需要注意：
 * 为每个线程的栈分配的内存越大，反而越容易产生内存溢出异常。
 *
 * 原因不难理解，其他内存忽略，剩下的内存就由虚拟机栈和本地方法栈“瓜分”了，
 * 如果每个线程分配到的栈容量越大，可以建立的线程数量自然就越少，建立线程时就越容易把剩下的内存耗尽。
 *
 * 如果是建立过多线程导致的内存溢出，在不能减少线程数或者更换64位虚拟机的情况下，就只能通过减少最大堆和减少栈容量来换取更多的线程。
 * 这种通过“减少内存”的手段来解决内存溢出的方式，如果没有这方面经验会比较难以想到。
 */
public class JavaVMStackSOF {

    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try {
            oom.stackLeak();
        } catch (Throwable e) {
            System.out.println("stack length:" + oom.stackLength);
            throw e;
        }
    }

}
