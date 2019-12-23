package arith.arithmetic.basic_sort;

/**
 * 选择排序
 */
public class SelectionSort {

    /**
     * 原理：类似插入排序，也分为已排序区间和未排序区间；不同的是，每次从未排序区间中选出最小的元素，将其插入到已排序区间的末尾。
     * 此处，左侧为已排序区间，右侧是未排序区间。
     *
     * @param arr 算法结果：默认自然排序，数组按从小到大排序
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        if (n <= 1) {
            return;
        }
        int count = 0; // 比较次数
//        int moveCount = 0; // 移动次数
        for (int i = 0; i < n; i++) { // 未排序区间
            int minIndex = i, j = i;
            int min = arr[j];
            for (; j < n - 1; j++) {
                if (arr[j + 1] < min) {
                    min = arr[j + 1];
                    minIndex = j + 1;
                }
                count++;
            }
            if (minIndex == i) { // 最小元素就是未排序区间第一个元素，无须改变元素位置；
                continue;
            }
            /* 选择排序是一种不稳定的排序算法
            for (int k = minIndex; k > i; k--) { // 为保证排序算法的稳定性,将最小元素插入已排序区间末尾；未排序区间中最小元素之前的元素往后移动一位
                arr[k] = arr[k - 1];
                moveCount++;
            }*/
            // 交换元素
            arr[minIndex] = arr[i];
            arr[i] = min; // 插入元素
        }
        System.out.println();
        System.out.println("比较次数：" + count);
//        System.out.println("移动次数：" + moveCount);
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
        // 逆序度： 15-3 = 12; 即移动次数
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
