/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-05-27 23:27:21
 */
public class SegmentTree<E> {

    private E[] tree;
    private E[] data;

    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {

        this.merger = merger;

        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    /**
     * 在treeIndex的位置创建表示区间[l...r]的线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {

        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        // 线段树的中间位置 or int mid = (l + r) / 2;
        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    @Override
    public String toString() {
        if (tree == null) {
            return "null";
        }

        StringBuilder b = new StringBuilder("[");

        for (int i = 0; i < tree.length; i++) {
            b.append(tree[i]);
            if (i != tree.length - 1) {
                b.append(", ");
            }
        }

        return b.append("]").toString();
    }
}
