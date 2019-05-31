/**
 * Leetcode 307. Range Sum Query - Mutable 区域和检索 - 数组可修改
 * https://leetcode.com/problems/range-sum-query-mutable/description/
 * 使用sum数组的思路：TLE
 *
 * @author bianxinhuan
 * @date 2019-05-31 21:13:42
 */
public class NumArray3 {

    /**
     * 存放数据副本
     */
    private int[] data;

    /**
     * 存放前i个元素的合
     */
    private int[] sum;

    public NumArray3(int[] nums) {

        data = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            data[i] = nums[i];
        }

        sum = new int[nums.length + 1];
        sum[0] = 0;

        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
    }

    public void update(int i, int val) {
        data[i] = val;
        for (int j = i + 1; j < sum.length; j++) {
            sum[j] = sum[j - 1] + data[j - 1];
        }
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */