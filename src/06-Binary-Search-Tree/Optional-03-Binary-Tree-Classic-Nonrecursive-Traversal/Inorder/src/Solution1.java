import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Leetcode 题目 94. 二叉树的中序遍历 https://leetcode.com/problems/binary-tree-inorder-traversal/solution/
 *
 * // Classic Non-Recursive algorithm for inorder traversal
 * // Time Complexity: O(n), n is the node number in the tree
 * // Space Complexity: O(h), h is the height of the tree
 *
 * @author bianxinhuan
 * @date 2019-05-10 23:03:25
 */
public class Solution1 {

    /**
     * 给定一个二叉树，返回它的中序 遍历。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            res.add(cur.val);
            cur = cur.right;
        }

        return res;
    }
}
