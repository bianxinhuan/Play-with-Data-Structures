/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-20 22:21:15
 */
public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    // 下一小节再做具体实现
    @Override
    public void enqueue(E e) {
    }

    // 下一小节再做具体实现
    @Override
    public E dequeue() {
        return null;
    }

    // 下一小节再做具体实现
    @Override
    public E getFront() {
        return null;
    }
}
