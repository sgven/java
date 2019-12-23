package arith.arithmetic.basic_idea.dynamic_programming;

/**
 * 动态规划
 */
public class DynamicProgramming {

    public static int knapsack2(int[] items, int n, int w) {
        boolean[] states = new boolean[w+1]; // 默认值 false
        states[0] = true;  // 第一行的数据要特殊处理，可以利用哨兵优化
        states[items[0]] = true;
        int count = 0;
        for (int i = 1; i < n; ++i) { // 动态规划
            for (int j = w-items[i]; j >= 0; --j) {// 把第 i 个物品放入背包
                if (states[j]==true) {
                    count++;
                    states[j+items[i]] = true;
                }
            }
            /** */
            /*for (int j = 0; j <= w-items[i]; ++j) {// 把第 i 个物品放入背包
                if (states[j]==true) {
                    count++;
                    states[j+items[i]] = true;
                }
            }*/
        }
        System.out.println("count: " + count);
        for (int i = w; i >= 0; --i) { // 输出结果
            if (states[i] == true) return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        int[] items = {2,2,4,6,3};
        System.out.println("背包可装物品的最大重量：" + knapsack2(items,5,9));
    }
}
