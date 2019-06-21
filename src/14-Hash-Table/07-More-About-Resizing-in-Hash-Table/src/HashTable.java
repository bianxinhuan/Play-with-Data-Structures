import java.util.TreeMap;

/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-06-19 20:19:15
 */
public class HashTable<K, V> {

    /**
     * 容量集合，全部都是素数，以大约2倍的趋势依次增加
     */
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

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
    private int capacityIndex = 0;

    private TreeMap<K, V>[] hashtable;
    private int size;
    /**
     * 素数
     */
    private int M;

    public HashTable() {
        this.M = capacity[capacityIndex];
        this.size = 0;
        this.hashtable = new TreeMap[M];

        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
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

            // 判断平均容量是否超出了设置的上界，等同：size / M >= upperTol，并且防止capacity下标越界
            if (size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(2 * capacity[capacityIndex]);
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
            if (size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex--;
                resize(capacity[capacityIndex] / 2);
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
            newHashTable[i] = new TreeMap<>();
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
