/**
 * /// Leetcode 804. Unique Morse Code Words
 * /// https://leetcode.com/problems/unique-morse-code-words/description/
 * ///
 * /// 课程中在这里暂时没有介绍这个问题
 * /// 该代码主要用于使用Leetcode上的问题测试我们的BST类
 *
 * @author bianxinhuan
 * @date 2019-04-30 22:13:40
 */
public class Solution {

    private class BST<E extends Comparable<E>> {
        private class Node {
            private E e;
            Node left, right;

            public Node(E e) {
                this.e = e;
                left = null;
                right = null;
            }
        }

        private Node root;
        private int size;

        public BST() {
            root = null;
            size = 0;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 向二分搜索树中添加新的元素e
         *
         * @param e
         */
        public void add(E e) {
            if (root == null) {
                root = new Node(e);
                size++;
            } else {
                add(root, e);
            }
        }

        /**
         * 向以node为根的二分搜索树中插入元素e，递归算法
         *
         * @param node
         * @param e
         */
        private void add(Node node, E e) {

            if (e.equals(node.e)) {
                return;
            }

            if (node.e.compareTo(e) < 0) {
                if (node.left == null) {
                    node.left = new Node(e);
                    size++;
                } else {
                    add(node.left, e);
                }
            } else { // (node.e.compareTo(e) > 0) {
                if (node.right == null) {
                    node.right = new Node(e);
                    size++;
                } else {
                    add(node.right, e);
                }
            }
        }
    }

    public int uniqueMorseRepresentations(String[] words) {

        String[] codes = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        BST<String> bst = new BST<>();

        for (String word : words) {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                res.append(codes[word.charAt(i) - 'a']);
            }
            bst.add(res.toString());
        }

        return bst.size();
    }
}
