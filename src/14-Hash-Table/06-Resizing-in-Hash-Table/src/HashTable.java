import java.util.TreeMap;

/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-06-19 20:19:15
 */
public class HashTable<K, V> {

    /**
     * 哈希冲突的元素的上界 tolerance
     */
    private static final int upperTol = 10;
    /**
     * 哈希冲突的元素的下界 tolerance
     */
    private static final int lowerTol = 2;
    /**
     * 初始容量
     */
    private static final int initCapacity = 7;

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

        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;

            // 判断平均容量是否超出了设置的上界，等同：size / M >= upperTol
            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }

    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;

            // 判断平均容量是否超出了设置的下界，等同：size / M < lowerTol
            if (size < lowerTol * M && M / 2 >= initCapacity) {
                resize(M / 2);
            }
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

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = hashtable[i];
        }

        int oldM = M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }

        this.hashtable = newHashTable;
    }
}
