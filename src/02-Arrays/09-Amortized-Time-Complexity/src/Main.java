/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-04-15 22:59:48
 */
public class Main {

    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();
        for (int i = 0; i < 10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);

        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);

        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);

        for (int i = 0; i < 8; i++) {
            arr.removeFirst();
            System.out.println(arr);
        }
    }
}