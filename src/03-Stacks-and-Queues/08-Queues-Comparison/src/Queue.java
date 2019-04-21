/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-19 20:55:11
 */
public interface Queue<E> {

    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
