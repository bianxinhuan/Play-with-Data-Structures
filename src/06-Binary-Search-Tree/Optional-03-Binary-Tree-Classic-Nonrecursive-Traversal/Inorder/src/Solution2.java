import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Leetcode 题目 94. 二叉树的中序遍历 https://leetcode.com/problems/binary-tree-inorder-traversal/solution/
 * <p>
 * // Another Classic Non-Recursive algorithm for inorder traversal
 * // Time Complexity: O(n), n is the node number in the tree
 * // Space Complexity: O(h), h is the height of the tree
 *
 * @author bianxinhuan
 * @date 2019-05-11 09:49:00
 */
public class Solution2 {

    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            if (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } else {
                cur = stack.pop();
                res.add(cur.val);
                cur = cur.right;
            }
        }
        return res;
    }

}
