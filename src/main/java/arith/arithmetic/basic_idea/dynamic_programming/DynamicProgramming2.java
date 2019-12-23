package arith.arithmetic.basic_idea.dynamic_programming;

/**
 * 动态规划理论
 */
public class DynamicProgramming2 {

    private static int[][] matrix = {{1,3,5,9}, {2,1,3,4},{5,2,6,7},{6,8,4,3}};

    /**
     * n*n 阶矩阵，求从(0,0)移动到(n-1,n-1)最短距离
     * 每次只能向下或向右移动一步，矩阵中元素的值表示距离
     * @param matrix    int [i][j]用一个二维数组表示n阶矩阵,i表示行，j表示列，数组值表示距离
     * @param n          n阶
     * @return
     */
    public static int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int j = 0; j < n; ++j) { // 初始化 states 的第一行数据
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        sum = 0;
        for (int i = 0; i < n; ++i) { // 初始化 states 的第一列数据
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < n; ++j) {
                states[i][j] =
                        matrix[i][j] + Math.min(states[i][j-1], states[i-1][j]);
            }
        }
        return states[n-1][n-1];
    }


    /**
     * 硬币找零问题，有v1, v2, ... , vn种硬币(单位是元)，支付w元(找零w元)，求最少需要多少个硬币。
     * 为了使问题总有解，一般第1枚硬币的面值为1
     */

    // 解法一： 状态转移表法
    private static int minCoin = Integer.MAX_VALUE;

    /**
     *
     * 回溯算法求解，最少硬币
     * @param coinValues    币值数组
     * @param w 找零w元
     * @return
     */
    public static void minCoinByBack(int[] coinValues, int w) {
        if (w <= 0) { // 所需找零的钱小于等于0
            return;
        }
        int n = coinValues.length;
        chargeCoin(0, 0, 0, w, n);
    }

    private static void chargeCoin(int i, int money, int count, int w, int n) {
        if (i == n || money == w) {
            if (count < minCoin) {
                minCoin = count;
            }
            return;
        }
        // 第i种硬币不用来找零
        chargeCoin(i+1, money, count, w, n);
        if (money + coinValues[i] <= w) {
            // 第i种硬币用来找零，继续考察下一种硬币
            chargeCoin(i+1, money+coinValues[i], count+1, w, n);
            // 第i种硬币用来找零，继续考察第i种硬币
            chargeCoin(i, money+coinValues[i], count+1, w, n);
        }
    }

    // 解法二： 状态转移方程，看作爬阶梯问题，分为1,2,3步走
    // 状态转移方程： minCoin(w) = minCoin(v1) + minCoin(w-v1); minCoin(v1) = 1;
    // 即minCoin(w) = 1 + minCoin(w-v1);
    // 即minCoin(w) = v[i] + minCoin(w-v[i]);

    // 思路：
    // 为了使问题总有解，一般第1枚硬币的面值为1
    // 第 i 枚硬币有两种选择：用它来找零 和 不用它找零。因此，c[i,j]的最优解如下：（状态转移方程法）
    // c[i,j]= min{c[i-1,j] , c[i, j-coinsValues[i]] + 1}
    // 其中，i 表示可用的硬币种类数， j 表示 需要找回的零钱
    // c[i-1,j] 表示 不使用第 i 枚硬币找零时，对金额为 j 进行找钱所需要的最少硬币数
    // c[i, j-coinsValues[i]] + 1 表示 使用 第 i 枚硬币找零时，对金额为 j 进行找钱所需要的最少硬币数
    //    由于用了第 i 枚硬币，故使用的硬币数量要增1
    //    c[i,j] 取二者的较小值的那一个。
    // 边界条件处理：
    // c[0][j]=Integer.MAXVALUE ，因为 对 金额为 j 的钱找零，但是可用的硬币面值 种类为0
    // c[i][0]=Integer.MAXVALUE ，因为 不可能做到对 金额为 0 的钱找零


    private static int[] coinValues = {1,2,5,10,20,30};
    /**
     * 硬币找零，求找零w元，所需最少硬币数量
     * @param coinValues 可用来找零的硬币 coinsValues.length是硬币的种类
     * @param w 待找的零钱
     * @return
     */
    public static int minCoin(int[] coinValues, int w) {
        int[][] states = new int[coinValues.length+1][w+1]; // 初始化，长度n+1，下标值[0,n]

        return -1;
    }

    public static void main(String[] args) {
        System.out.println("最短路径："+minDistDP(matrix, 4));
        System.out.println("找零最少需要的硬币个数："+minCoin(coinValues, 98));
    }
}
