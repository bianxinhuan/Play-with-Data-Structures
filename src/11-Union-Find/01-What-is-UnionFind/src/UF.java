/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-06-05 19:42:36
 */
public interface UF {

    int getSize();

    void unionElements(int p, int q);

    void isConnected(int p, int q);
}
