/**
 * 移除链表元素
 * <p>
 * /// Leetcode 203. Remove Linked List Elements
 * /// https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * @author bianxinhuan
 * @date 2019-04-24 21:38:11
 */
public class Solution2 {

    public ListNode removeElements(ListNode head, int val) {

        // 移除头部的元素
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution2().removeElements(head, 6);
        System.out.println(res);
    }

}
