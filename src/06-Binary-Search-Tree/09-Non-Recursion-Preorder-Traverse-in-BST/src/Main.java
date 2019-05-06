/**
 * -
 *
 * @author bianxinhuan
 * @date 2019-05-03 22:14:40
 */
public class Main {
    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();
        int[] nums = {5, 3, 6, 8, 4, 2};

        for (int i = 0; i < nums.length; i++) {
            bst.add(nums[i]);
        }

        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////

        bst.preOrderNR();
        System.out.println();

        bst.preOrder();
        System.out.println();
//
//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();
    }
}
