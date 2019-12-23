package arith.arithmetic.basic_sort;

/**
 * 插入排序
 */
public class InsertionSort {

    /**
     * 原理：将一段静态数组分为已排序区间和未排序区间，取出未排序区间的元素，找到它在已排序区间的插入位置将其插入，
     * 将插入位置后面的元素往后移动一位(保证已排序区间一直有序)。重复这个过程，直到未排序区间元素为空。
     * 此处，左侧为已排序区间，右侧是未排序区间。
     * @param arr 算法结果：默认自然排序，数组按从小到大排序
     */
    public static void sort(int[] arr) {
        int n = arr.length;
        if (n <= 1) return;
        int count = 0; // 比较次数
        int moveCount = 0; // 移动次数
        for (int i = 1; i < n; i++) { // 未排序区间
            int value = arr[i]; // 在未排序区间中取出元素
            int j = i - 1; // a[j] 已排序区间元素
            for (; j >= 0; --j) { /** j >= 0  注意：是大于等于0*/
                count++;
                if (arr[j] > value) {
                    arr[j + 1] = arr[j]; // 移动已排序元素：往后移动一位
                    moveCount++;
                } else {
                    break; // 找到插入位置，跳出内循环
                }
            }
            arr[j+1] = value; // 插入元素
        }
        System.out.println();
        System.out.println("比较次数:"+count);
        System.out.println("移动次数:"+moveCount);
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
