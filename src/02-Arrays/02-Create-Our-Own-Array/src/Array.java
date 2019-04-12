package src;

/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-11 20:57:35
 */
public class Array {

    private int[] data;
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     *
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = new int[10];
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
     * 支架数组是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

}
