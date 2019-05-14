/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-05-13 14:33:05
 */
public interface Map<K, V> {

    void add(K key, V value);

    V remove(K key);

    boolean contains(K key);

    V get(K key);

    void set(K key, V newValue);

    int getSize();

    boolean isEmpty();
}
