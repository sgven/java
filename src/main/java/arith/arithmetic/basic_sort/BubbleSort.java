package arith.arithmetic.basic_sort;

/**
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 原理：遍历数组，比较前后数据大小，若不符合排序规则则交换；一次冒泡操作即遍历一次数组，比较大小。
     * 原则上，一次冒泡操作至少会让一个数据移动到它应该在的位置。一个大小为n的数组只要进行n次冒泡操作即可完成排序。
     * 在此基础上对算法优化，加标志位判断是否还需要冒泡，减少冒泡次数。
     *
     * @param arr 算法结果：默认自然排序，数组按从小到大排序
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;
        int count = 0;//比较次数
        int exchangeCount = 0;//交换次数
        for (int i = 0; i < n; i++) {
            boolean flag = false;//是否有交换标志
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                    exchangeCount++;
                }
                count++;
            }
            if (!flag) break;//若无交换，说明已排序完成
        }
        System.out.println();
        System.out.println("比较次数:" + count);
        System.out.println("交换次数:" + exchangeCount);
    }

    public static void print(int[] arr) {
        if (arr == null || arr.length == 0) return;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(" " + arr[i] + " ");
        }
    }

    public static void main(String[] args) {
        int a[] = {4, 5, 6, 3, 2, 1};
        // 满有序度：5+4+3+2+1; (1+5)*5/2=15  公式：n*(n-1)/2
        // 有序度： (4,5)  (4,6)  (5,6) = 3
        // 逆序度： 15-3 = 12; 即交换次数
        print(a);
        sort(a);
        print(a);

        System.out.println();
        int b[] = {1, 2, 3, 4, 6, 5};
        print(b);
        sort(b);
        print(b);

    }
}
