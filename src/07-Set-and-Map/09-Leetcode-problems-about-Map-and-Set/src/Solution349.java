import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Leetcode 349. 两个数组的交集 Intersection of Two Arrays
 * https://leetcode.com/problems/intersection-of-two-arrays/description/
 *
 * @author bianxinhuan
 * @date 2019-05-20 13:57:03
 */
public class Solution349 {

    public int[] intersection(int[] nums1, int[] nums2) {

        Set<Integer> set = new TreeSet<>();
        for (int num : nums1) {
            set.add(num);
        }

        List<Integer> res = new ArrayList<>();
        for (int num : nums2) {
            if (set.contains(num)) {
                res.add(num);
                set.remove(num);
            }
        }

        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }

        return ret;
    }
}
