/**
 * 移除链表元素
 * <p>
 * /// Leetcode 203. Remove Linked List Elements
 * /// https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * @author bianxinhuan
 * @date 2019-04-24 20:55:44
 */
public class Solution {

    public ListNode removeElements(ListNode head, int val) {

        // 移除头部的元素
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = head.next;
            delNode.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};

        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution().removeElements(head, 6);
        System.out.println(res);
    }
}
