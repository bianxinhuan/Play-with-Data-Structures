/**
 * 303. Range Sum Query - Immutable 区域和检索 - 数组不可变
 * https://leetcode.com/problems/range-sum-query-immutable/description/
 *
 * @author bianxinhuan
 * @date 2019-05-31 21:02:53
 */
public class NumArray2 {

    // sum[i]存储前i个元素和, sum[0] = 0
    // 即sum[i]存储nums[0...i-1]的和
    // sum(i, j) = sum[j + 1] - sum[i]
    private int[] sum;

    public NumArray2(int[] nums) {

        sum = new int[nums.length + 1];
        sum[0] = 0;

        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}
