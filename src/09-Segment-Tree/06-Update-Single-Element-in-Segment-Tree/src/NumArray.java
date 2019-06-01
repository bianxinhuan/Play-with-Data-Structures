/**
 * 307. Range Sum Query - Mutable 区域和检索 - 数组可修改
 * https://leetcode.com/problems/range-sum-query-mutable/submissions/
 *
 * @author bianxinhuan
 * @date 2019-05-31 20:30:34
 */
public class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {

        if (nums.length > 0) {
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++) {
                data[i] = nums[i];
            }
            segmentTree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public void update(int i, int val) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Error");
        }
        segmentTree.set(i, val);
    }

    public int sumRange(int i, int j) {
        if (segmentTree == null) {
            throw new IllegalArgumentException("Segment Tree is null.");
        }
        return segmentTree.query(i, j);
    }
}
/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */