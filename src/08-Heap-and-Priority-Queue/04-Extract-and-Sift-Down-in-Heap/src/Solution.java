import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

/**
 * 347. Top K Frequent Elements 前K个高频元素 https://leetcode.com/problems/top-k-frequent-elements/description/
 * 课程中在这里暂时没有介绍这个问题
 * 该代码主要用于使用Leetcode上的问题测试我们的MaxHeap类
 *
 * @author bianxinhuan
 * @date 2019-05-24 14:00:40
 */
public class Solution {

    private class Array<E> {

        private E[] data;
        private int size;

        /**
         * 构造函数，传入数组的容量capacity构造Array
         *
         * @param capacity 容量
         */
        public Array(int capacity) {
            data = (E[]) new Object[capacity];
            size = 0;
        }

        /**
         * 无参数的构造函数，默认数组的容量capacity=10
         */
        public Array() {
            this(10);
        }

        /**
         * 获取数组的容量
         *
         * @return
         */
        public int getCapacity() {
            return data.length;
        }

        /**
         * 获取元素的个数
         *
         * @return
         */
        public int getSize() {
            return size;
        }

        /**
         * 判断数组是否为空
         *
         * @return
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * 在index索引的位置插入一个新元素e
         *
         * @param index
         * @param e
         */
        public void add(int index, E e) {

            if (index < 0 || index > size) {
                throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");
            }

            if (size == data.length) {
                resize(data.length * 2);
            }

            for (int i = size - 1; i >= index; i--) {
                data[i + 1] = data[i];
            }

            data[index] = e;

            size++;
        }

        /**
         * 向所有元素后添加一个新元素
         *
         * @param e
         */
        public void addFirst(E e) {
            add(0, e);
        }

        /**
         * 在所有元素前添加一个新元素
         *
         * @param e
         */
        public void addLast(E e) {
            add(size, e);
        }

        /**
         * 获取index索引位置的元素
         *
         * @param index
         * @return
         */
        public E get(int index) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Get failed. Index is illegal.");
            }

            return data[index];
        }

        /**
         * 修改index索引位置的元素为e
         *
         * @param index
         * @param e
         */
        public void set(int index, E e) {
            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Set failed. Index is illegal.");
            }

            data[index] = e;
        }

        /**
         * 交换2个元素
         *
         * @param i
         * @param j
         */
        public void swap(int i, int j) {
            if (i < 0 || i >= size || j < 0 || j >= size) {
                throw new IllegalArgumentException("Index is illegal.");
            }
            E t = data[i];
            data[i] = data[j];
            data[j] = t;
        }

        /**
         * 查找数组中是否有元素e
         *
         * @param e
         * @return
         */
        public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    return true;
                }
            }
            return false;
        }

        /**
         * 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
         *
         * @param e
         * @return
         */
        public int find(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i].equals(e)) {
                    return i;
                }
            }
            return -1;
        }

        /**
         * 从数组中删除index位置的元素, 返回删除的元素
         *
         * @param index
         * @return
         */
        public E remove(int index) {

            if (index < 0 || index >= size) {
                throw new IllegalArgumentException("Remove failed. Index is illegal.");
            }

            E res = data[index];

            for (int i = index + 1; i < size; i++) {
                data[i - 1] = data[i];
            }

            size--;
            data[size] = null;

            if (size == data.length / 4 && data.length / 2 != 0) {
                resize(data.length / 2);
            }

            return res;
        }

        /**
         * 从数组中删除第一个元素, 返回删除的元素
         *
         * @return
         */
        public E removeFirst() {
            return remove(0);
        }

        /**
         * 从数组中删除最后一个元素, 返回删除的元素
         *
         * @return
         */
        public E removeLast() {
            return remove(size - 1);
        }

        /**
         * 从数组中删除元素e
         *
         * @param e
         * @return
         */
        public void removeElement(E e) {
            int index = find(e);
            if (index > -1) {
                remove(index);
            }
        }

        /**
         * 将数组空间的容量变成newCapacity大小
         *
         * @param newCapacity
         */
        public void resize(int newCapacity) {

            E[] newData = (E[]) new Object[newCapacity];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }
    }

    private class MaxHeap<E extends Comparable<E>> {

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
         * 比较频率
         *
         * @param another
         * @return
         */
        @Override
        public int compareTo(Freq another) {
            if (this.freq < another.freq) {
                return 1;
            } else if (this.freq > another.freq) {
                return -1;
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

        MaxHeap<Freq> maxHeap = new MaxHeap<>();
        for (int key : map.keySet()) {

            // 先取TreeMap顶部的k个元素
            if (maxHeap.size() < k) {
                maxHeap.add(new Freq(key, map.get(key)));

                // 再比较k元素之后的元素，如果当前元素的频率大于堆中最[少]的频率
            } else if (map.get(key) > maxHeap.findMax().freq) {
                // 则取出堆中的最大元素
                maxHeap.extractMax();
                // 再把当前元素添加到堆中
                maxHeap.add(new Freq(key, map.get(key)));
            }
        }

        List<Integer> res = new LinkedList<>();
        while (!maxHeap.isEmpty()) {
            res.add(maxHeap.extractMax().e);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};

        int k = 2;
        System.out.println(new Solution().topKFrequent(nums, k));
    }
}
