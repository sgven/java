package arith.arithmetic.basic_idea;

/**
 * 回溯算法
 */
public class Backtracking {


    /**===================================== 8皇后 =====================================BEGIN*/
    private static int[] queens = new int[8]; // 下标表示行，值表示皇后的位置，即存储在哪一列


    public static void cal8queens(int row) {
        if (row == 8) {
            print8queens(queens);
            return;
        }
        for (int column = 0; column < 8; column++) { // 逐列考察每行皇后的位置
            if (isOk(row, column)) {
                queens[row] = column;
                cal8queens(row + 1); // 考察下一行
            }
        }

    }

    private static boolean isOk(int row, int column) { // 判断 row 行 column 列放置是否合适
        int leftUpColumn = column - 1;
        int rightUpColumn = column + 1;
        for (int i = row - 1; i >= 0; i--) { // 逐行往上考察
            if (queens[i] == column) return false; // 第 i 行（即row的上一行），第 column 列 是否有皇后
            if (leftUpColumn >= 0 && queens[i] == leftUpColumn) return false;
            if (rightUpColumn < 8 && queens[i] == rightUpColumn) return false;
            --leftUpColumn;
            ++rightUpColumn;
        }
        return true;
    }

    private static void print8queens(int[] result) { // 打印出一个二维矩阵
        for (int i = 0; i < 8; i++) {
            for (int column = 0; column < 8; column++) {
                if (queens[i]==column) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    /**===================================== 8皇后 =====================================END*/

    /**===================================== 0-1背包 =====================================BEGIN*/
    public static int maxW = Integer.MIN_VALUE;
    /**
     * 回溯算法求背包最多可装的物品重量（物品不可分割）
     *
     * @param i 表示考察到第几个物品
     * @param cw 表示已装进背包的物品的重量和
     * @param items 表示每个物品的重量
     * @param n 表示物品个数
     * @param w 表示背包最多装的承重重量
     */
    public static void f(int i, int cw, int[] items, int n, int w) {
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        f(i+1, cw, items, n, w);
        if (cw + items[i] <= w) { // 这里容易出错，1. cw 要+ items[i]，2. 是<=w
            f(i+1, cw+items[i], items, n, w);
        }
    }
    /**===================================== 0-1背包 =====================================END*/

    /**===================================== 正则表达式 =====================================BEGIN*/
    /**
     * 假设正表达式中只包含“*”和“?”这两种通配符，并稍微改变一下它们的含义。其中，
     * “*”匹配任意多个（大于等于 0 个）任意字符
     * “?”匹配零个或者一个任意字符
     */
    public static class Pattern {
        private boolean matched = false;
        private char[] pattern;
        private int plen;

        /**
         *
         * @param pattern 正则表达式
         * @param plen 正则表达式的长度
         */
        public Pattern(char[] pattern, int plen) {
            this.pattern = pattern;
            this.plen = plen;
        }

        /**
         *
         * @param text 文本
         * @param tlen 文本的长度
         * @return
         */
        public boolean match(char[] text, int tlen) {
            matched = false;
            rmatch(0, 0 , text, tlen);
            return matched;
        }

        /**
         *
         * @param ti 表示考察到文本串的第几个元素了
         * @param pj 表示考察到正则表达式的第几个元素了
         * @param text
         * @param tlen
         */
        private void rmatch(int ti, int pj, char[] text, int tlen) {
            if (matched) return; // 如果已经匹配了，就不要继续递归了
            if (pj == plen) { // 正则表达式，考察到结尾了
                if (ti == tlen) matched = true; // 文本串也到结尾了。
                return;
            }
            if (pattern[pj] == '*') { // 匹配任意多个（大于等于 0 个）任意字符
                for (int k = 0; k <= tlen - ti; k++) {
                    rmatch(ti+k, pj+1, text, tlen);
                }
            } else if (pattern[pj] == '?') { // 匹配零个或者一个任意字符
                rmatch(ti, pj+1, text, tlen);
                rmatch(ti+1, pj+1, text, tlen);
            } else if (ti < tlen && pattern[pj] == text[ti]) { //
                rmatch(ti+1, pj+1, text, tlen);
            }
        }
    }
    /**===================================== 正则表达式 =====================================END*/

    public static void main(String[] args) {
        // 8皇后
//        cal8queens(0);

        // 0-1背包
//        int[] a = new int[] {2, 2, 4, 6, 3};
//        f(0,0, a, 5, 10);
//        System.out.println("maxW: " + maxW);

        // 正则表达式
        char[] pattern = "*?xp".toCharArray();
        Pattern p = new Pattern(pattern, pattern.length);
        char[] text = "1211xp".toCharArray();
        System.out.println("matched:" + p.match(text, text.length));
    }
}
