import java.util.ArrayList;

/**
 * /// Leetcode 350. 两个数组的交集 II Intersection of Two Arrays II
 * /// https://leetcode.com/problems/intersection-of-two-arrays-ii/description/
 * ///
 * /// 课程中在这里暂时没有介绍这个问题
 * /// 该代码主要用于使用Leetcode上的问题测试我们的BSTMap类
 *
 * @author bianxinhuan
 * @date 2019-05-13 21:16:58
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

    private class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

        private class Node {
            public K key;
            public V value;
            public Node left, right;

            public Node(K key, V value) {
                this.key = key;
                this.value = value;
                left = null;
                right = null;
            }
        }

        private Node root;
        private int size;

        public BSTMap() {
            root = null;
            size = 0;
        }

        /**
         * 向二分搜索树中添加新的元素(key, value)
         *
         * @param key
         * @param value
         */
        @Override
        public void add(K key, V value) {
            root = add(root, key, value);
        }

        /**
         * 向以node为根的二分搜索树中插入元素(key, value)，递归算法
         * 返回插入新节点后二分搜索树的根
         *
         * @param node
         * @param key
         * @param value
         * @return
         */
        private Node add(Node node, K key, V value) {

            if (node == null) {
                size++;
                return new Node(key, value);
            }

            if (key.compareTo(node.key) < 0) {
                node.left = add(node.left, key, value);
            } else if (key.compareTo(node.key) > 0) {
                node.right = add(node.right, key, value);
            } else { //  key.compareTo(node.key) == 0
                node.value = value;
            }

            return node;
        }

        /**
         * 返回以node为根节点的二分搜索树中，key所在的节点
         *
         * @param node
         * @param key
         * @return
         */
        private Node getNode(Node node, K key) {

            if (node == null) {
                return null;
            }

            if (key.equals(node.key)) {
                return node;
            } else if (key.compareTo(node.key) < 0) {
                return getNode(node.left, key);
            } else { // if(key.compareTo(node.key) > 0
                return getNode(node.right, key);
            }
        }

        /**
         * 返回以node为根的二分搜索树的最小值所在的节点
         *
         * @param node
         * @return
         */
        private Node minium(Node node) {

            if (node.left == null) {
                return node;
            }
            return minium(node.left);
        }

        /**
         * 删除掉以node为根的二分搜索树中的最小节点
         * 返回删除节点后新的二分搜索树的根
         *
         * @param node
         * @return
         */
        private Node removeMin(Node node) {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            node.left = removeMin(node.left);
            return node;
        }

        @Override
        public V remove(K key) {

            Node node = getNode(root, key);

            if (node != null) {
                root = remove(root, key);
                return node.value;
            }

            return null;
        }

        private Node remove(Node node, K key) {

            if (node == null) {
                return null;
            }

            if (key.compareTo(node.key) < 0) {
                node.left = remove(node.left, key);
                return node;
            } else if (key.compareTo(node.key) > 0) {
                node.right = remove(node.right, key);
                return node;
            } else { // key.compareTo(node.key) == 0

                // 待删除节点左子树为空的情况
                if (node.left == null) {
                    Node rightNode = node.right;
                    node.right = null;
                    size--;
                    return rightNode;
                }

                // 待删除节点右子树为空的情况
                if (node.right == null) {
                    Node leftNode = node.left;
                    node.left = null;
                    size--;
                    return leftNode;
                }

                // 待删除节点左右子树均不为空的情况
                // 找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
                // 用这个节点顶替待删除节点的位置
                Node successor = minium(node.right);
                successor.right = removeMin(node.right);
                successor.left = node.left;

                node.left = node.right = null;

                return successor;
            }
        }

        @Override
        public boolean contains(K key) {
            return getNode(root, key) != null;
        }

        @Override
        public V get(K key) {
            Node node = getNode(root, key);
            return node == null ? null : node.value;
        }

        @Override
        public void set(K key, V newValue) {
            Node node = getNode(root, key);
            if (node == null) {
                throw new IllegalArgumentException(key + " does't exist!");
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

    public int[] intersect(int[] nums1, int[] nums2) {

        Map<Integer, Integer> map = new BSTMap<>();

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
