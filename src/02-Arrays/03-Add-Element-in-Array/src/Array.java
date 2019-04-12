/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-12 22:03:58
 */
public class Array {

    private int[] data;
    private int size;

    public Array(int capacity) {
        data = new int[capacity];
        size = 0;
    }

    public Array() {
        this(10);
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(int e) {
        add(0, e);
    }

    public void addLast(int e) {
        add(size, e);
    }

    public void add(int index, int e) {

        if (size == data.length) {
            throw new IllegalArgumentException("Add failed. Array is full.");
        }

        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Add failed. Required index >= 0 and index <= size.");
        }

        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }

        data[index] = e;

        size++;
    }
}
