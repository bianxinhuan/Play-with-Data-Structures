/**
 * 合并接口
 *
 * @author bianxinhuan
 * @date 2019-05-29 20:47:53
 */
public interface Merger<E> {
    E merge(E a, E b);
}
