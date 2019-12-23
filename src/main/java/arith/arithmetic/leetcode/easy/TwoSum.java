package arith.arithmetic.leetcode.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 求数组中两数之和等于target的下标
 */
public class TwoSum {

    public static void main(String[] args) {
        int[] nums = {3, 2, 4, 8};
        int target = 6;
        // 期望结果是：下标为[1,2]
        Arrays.stream(getTwoSum(nums, target)).forEach(e -> {
            System.out.println(e);
        });
    }

    public static int[] getTwoSum(int[] nums, int target) {
        Map<Integer, Integer> cache = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            cache.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int v = target-nums[i];
            // 关键：&& cache.get(v) != i，排除掉了自己+自己=target的情况
            if (cache.containsKey(v) && cache.get(v) != i) {
                return new int[] {i, cache.get(v)};
            }
        }
//        return new int[0];
        throw new IllegalArgumentException("No two sum solution");
    }
}
