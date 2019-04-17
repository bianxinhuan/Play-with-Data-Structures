/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-17 23:45:15
 */
public interface Stack<E> {

    int getSize();

    boolean isEmpty();

    void push(E e);

    E pop();

    E peek();
}
