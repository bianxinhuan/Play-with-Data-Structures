import java.util.*;

/**
 * 347. Top K Frequent Elements 前K个高频元素
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * @author bianxinhuan
 * @date 2019-05-26 17:17:00
 */
public class Solution4 {

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        // 去掉Freq对象，直接比较频率
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                // 从map中获取元素的频率进行比较
                return map.get(a) - map.get(b);
            }
        });

        for (int key : map.keySet()) {
            // 先取TreeMap顶部的k个元素
            if (priorityQueue.size() < k) {
                priorityQueue.add(key);
            } else {
                // 再比较k元素之后的元素，如果当前元素的频率大于堆中最[少]的频率
                if (map.get(key) > map.get(priorityQueue.peek())) {
                    priorityQueue.remove();
                    priorityQueue.add(key);
                }
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.remove());
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println((new Solution4()).topKFrequent(nums, k));
    }

}
