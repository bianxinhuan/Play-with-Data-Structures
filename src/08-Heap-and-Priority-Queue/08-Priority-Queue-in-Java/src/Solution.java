import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 347. Top K Frequent Elements 前K个高频元素
 * https://leetcode.com/problems/top-k-frequent-elements/description/
 *
 * @author bianxinhuan
 * @date 2019-05-26 17:17:00
 */
public class Solution {

    /**
     * 频率
     */
    private class Freq implements Comparable<Freq> {

        private int e;
        private int freq;

        public Freq(int e, int freq) {
            this.e = e;
            this.freq = freq;
        }

        /**
         * 比较频率，由于Java中的优先队列实现是使用最小堆，所以比较方式按默认从小到大
         *
         * @param another
         * @return
         */
        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return -1;
            } else if (this.freq > another.freq) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }

        PriorityQueue<Freq> priorityQueue = new PriorityQueue<>();
        for (int key : map.keySet()) {
            // 先取TreeMap顶部的k个元素
            if (priorityQueue.size() < k) {
                priorityQueue.add(new Freq(key, map.get(key)));
            } else {
                // 再比较k元素之后的元素，如果当前元素的频率大于堆中最[少]的频率
                if (map.get(key) > priorityQueue.peek().freq) {
                    priorityQueue.remove();
                    priorityQueue.add(new Freq(key, map.get(key)));
                }
            }
        }

        LinkedList<Integer> res = new LinkedList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.remove().e);
        }

        return res;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        System.out.println((new Solution()).topKFrequent(nums, k));
    }

}
