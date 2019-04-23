/**
 * 使用自己的栈实现
 *
 * @author bianxinhuan
 * @date 2019-04-19 16:59:24
 */
public class Solution {

    class Array<E> {

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
         * 获取第1个元素
         *
         * @return
         */
        public E getFirst() {
            return get(0);
        }

        /**
         * 获取最后一个元素
         *
         * @return
         */
        public E getLast() {
            return get(size - 1);
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
         * 查找数组中是否有元素e
         *
         * @param e
         * @return
         */
        public boolean contains(E e) {
            for (int i = 0; i < size; i++) {
                if (data[i] == e) {
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
                if (data[i] == e) {
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

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append(String.format("Array: size %d , capacity %d\n", size, data.length));
            res.append("[");
            for (int i = 0; i < size; i++) {
                res.append(data[i]);
                if (i != size - 1) {
                    res.append(", ");
                }
            }
            res.append("]");
            return res.toString();
        }
    }

    interface Stack<E> {

        int getSize();

        boolean isEmpty();

        void push(E e);

        E pop();

        E peek();
    }

    class ArrayStack<E> implements Stack<E> {

        private Array<E> array;

        public ArrayStack(int capacity) {
            this.array = new Array<>(capacity);
        }

        public ArrayStack() {
            this.array = new Array<>();
        }

        @Override
        public int getSize() {
            return array.getSize();
        }

        @Override
        public boolean isEmpty() {
            return array.isEmpty();
        }

        public int getCapacity() {
            return array.getCapacity();
        }

        @Override
        public void push(E e) {
            array.addLast(e);
        }

        @Override
        public E pop() {
            return array.removeLast();
        }

        @Override
        public E peek() {
            return array.getLast();
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            res.append("Stack: ");
            res.append('[');
            for (int i = 0; i < array.getSize(); i++) {
                res.append(array.get(i));
                if (i != array.getSize() - 1) {
                    res.append(", ");
                }
            }
            res.append("] top");
            return res.toString();
        }
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }

                char topChar = stack.pop();

                if (c == ')' && topChar != '(') {
                    return false;
                }
                if (c == ']' && topChar != '[') {
                    return false;
                }
                if (c == '}' && topChar != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()"));
        System.out.println(new Solution().isValid("()[}"));
    }
}
