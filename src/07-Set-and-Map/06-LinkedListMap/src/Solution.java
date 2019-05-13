import java.util.ArrayList;

/**
 * /// Leetcode 350. 两个数组的交集 II Intersection of Two Arrays II
 * /// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 * ///
 * /// 课程中在这里暂时没有介绍这个问题
 * /// 该代码主要用于使用Leetcode上的问题测试我们的LinkedListMap类
 *
 * @author bianxinhuan
 * @date 2019-05-13 16:43:59
 */
public class Solution {

    private interface Map<K, V> {

        void add(K key, V value);

        V remove(K key);

        boolean contains(K key);

        V get(K key);

        void set(K key, V newValue);

        int getSize();

        boolean isEmpty();
    }

    private class LinkedListMap<K, V> implements Map<K, V> {

        private class Node {
            K key;
            V value;
            Node next;

            public Node(K key, V value, Node next) {
                this.key = key;
                this.value = value;
                this.next = next;
            }

            public Node(K key, V value) {
                this(key, value, null);
            }

            public Node() {
                this(null, null, null);
            }

            @Override
            public String toString() {
                return "Node{key=" + key + ", value=" + value + '}';
            }
        }

        private Node dummyHead;
        private int size;

        public LinkedListMap() {
            dummyHead = new Node();
            size = 0;
        }

        @Override
        public void add(K key, V value) {
            Node node = getNode(key);
            if (node == null) {
                dummyHead.next = new Node(key, value, dummyHead.next);
            } else {
                node.value = value;
            }
        }

        @Override
        public V remove(K key) {

            // 找到待删除的前一个节点的位置(prev)
            Node prev = dummyHead;
            while (prev.next != null) {
                if (prev.next.key.equals(key)) {
                    break;
                }
                prev = prev.next;
            }

            // 找到节点
            if (prev.next != null) {
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                size--;
                return delNode.value;
            }

            // 节点不存在
            return null;
        }

        private Node getNode(K key) {
            Node cur = dummyHead.next;
            while (cur != null) {
                if (cur.key.equals(key)) {
                    return cur;
                }
                cur = cur.next;
            }
            return null;
        }

        @Override
        public boolean contains(K key) {
            return getNode(key) != null;
        }

        @Override
        public V get(K key) {
            Node node = getNode(key);
            return node == null ? null : node.value;
        }

        @Override
        public void set(K key, V newValue) {

            Node node = getNode(key);
            if (node == null) {
                throw new IllegalArgumentException(key + " doesn't exist!");
            }

            node.value = newValue;
        }

        @Override
        public int getSize() {
            return size;
        }

        @Override
        public boolean isEmpty() {
            return size == 0;
        }
    }

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出: [2,2]
     * 示例 2:
     * <p>
     * 输入: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
     * 输出: [4,9]
     * 说明：
     * <p>
     * 输出结果中每个元素出现的次数，应与元素在两个数组中出现的次数一致。
     * 我们可以不考虑输出结果的顺序。
     * 进阶:
     * <p>
     * 如果给定的数组已经排好序呢？你将如何优化你的算法？
     * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
     * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new LinkedListMap<>();

        // 先把nums1的所有元素都存入map中，K是元素，V是元素出现的次数
        for (int num : nums1) {
            if (map.contains(num)) {
                map.set(num, map.get(num) + 1);
            } else {
                map.add(num, 1);
            }
        }

        // 遍历nums2，发现nums2中和nums1相同的元素则加到返回结果中
        ArrayList<Integer> res = new ArrayList<>();
        for (int num : nums2) {
            if (map.contains(num)) {
                res.add(num);
                // 把一个元素添加进结果的list后，设置map中的次数-1
                map.set(num, map.get(num) - 1);
                // 如果抵消，则移除map中的元素
                if (map.get(num) <= 0) {
                    map.remove(num);
                }
            }
        }

        int[] ret = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ret[i] = res.get(i);
        }

        return ret;
    }
}
