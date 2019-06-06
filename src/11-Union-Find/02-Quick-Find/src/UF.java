/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-06-05 19:42:36
 */
public interface UF {

    int getSize();

    boolean isConnected(int p, int q);

    void unionElements(int p, int q);
}
