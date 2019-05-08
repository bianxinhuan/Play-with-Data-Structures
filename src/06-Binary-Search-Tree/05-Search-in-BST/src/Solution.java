/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-05-01 22:47:02
 */
public class Solution {

    private class BST<E extends Comparable<E>> {

        private class Node {
            public E e;
            public Node left, right;

            public Node(E e) {
                this.e = e;
                this.left = null;
                this.right = null;
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
            root = add(root, e);
        }

        /**
         * 向以node为根的二分搜索树中插入元素e，递归算法
         * 返回插入新节点后二分搜索树的根
         *
         * @param node
         * @param e
         * @return
         */
        private Node add(Node node, E e) {

            if (node == null) {
                size++;
                return new Node(e);
            }

            if (e.compareTo(node.e) < 0) {
                node.left = add(node.left, e);
            } else if (e.compareTo(node.e) > 0) {
                node.right = add(node.right, e);
            }

            return node;
        }

        /**
         * 看二分搜索树中是否包含元素e
         *
         * @param e
         * @return
         */
        public boolean contains(E e) {
            return contains(root, e);
        }

        /**
         * 看以node为根的二分搜索树中是否包含元素e, 递归算法
         *
         * @param node
         * @param e
         * @return
         */
        private boolean contains(Node node, E e) {

            if (node == null) {
                return false;
            }

            if (e.compareTo(node.e) == 0) {
                return true;
            } else if (e.compareTo(node.e) < 0) {
                return contains(node.left, e);
            } else { // e.compareTo(node.e) > 0
                return contains(node.right, e);
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

    public static void main(String[] args) {
        int nums = new Solution().uniqueMorseRepresentations(new String[]{"gin", "zen", "gig", "msg"});
        System.out.println(nums);
    }
}
