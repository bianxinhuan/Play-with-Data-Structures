/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-29 23:33:53
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left,right;

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
}
