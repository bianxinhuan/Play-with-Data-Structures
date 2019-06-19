import java.util.TreeMap;

/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-06-19 20:19:15
 */
public class HashTable<K, V> {

    private TreeMap<K, V>[] hashtable;
    private int size;
    /**
     * 素数
     */
    private int M;

    public HashTable(int M) {
        this.M = M;
        this.size = 0;
        this.hashtable = new TreeMap[M];

        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    private int hash(K key) {
        // 按位与去掉key.hashCode()的第1位的符号，保证得到一个正数（第1位符号位为0）
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];

        // if(!hashtable[hash(key)].containsKey(key)){
        if (!map.containsKey(key)) {
            map.put(key, value);
            size++;
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
        }
        return ret;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }
}
