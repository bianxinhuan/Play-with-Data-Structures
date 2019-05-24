/**
 * 最大堆
 *
 * @author bianxinhuan
 * @date 2019-05-20 22:28:26
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 返回堆中的元素个数
     *
     * @return
     */
    public int size() {
        return data.getSize();
    }

    /**
     * 返回一个布尔值，表示堆中是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的父亲节点的索引
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * 堆中的元素上浮
     *
     * @param k 元素的索引
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * 查找堆中的最大元素
     *
     * @return
     */
    public E findMax() {
        if (data.getSize() <= 0) {
            throw new IllegalArgumentException("Can not findMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 取出堆中的最大元素
     *
     * @return
     */
    public E extractMax() {

        E ret = findMax();

        // 把堆顶元素和最后一个元素交换位置，再移除掉最后一个元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();

        // 然后把堆顶元素下沉
        siftDown(0);

        return ret;
    }

    /**
     * 堆中的元素下沉
     *
     * @param k
     */
    private void siftDown(int k) {

        while (leftChild(k) < data.getSize()) {

            // 在此轮循环中,data[k]和data[j]交换位置
            int j = leftChild(k);

            // data[j] 是 leftChild 和 rightChild 中的最大值
            // 如果存在右孩子，并且右孩子比左孩子大，则把右孩子索引赋值给j
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0) {
                j++;
            }

            // 如果当前元素不小于右孩子则结束循环
            if (data.get(k).compareTo(data.get(j)) >= 0) {
                break;
            }

            // 把当前元素和左右孩子中的最大元素进行交换
            data.swap(k, j);
            k = j;
        }
    }
}
