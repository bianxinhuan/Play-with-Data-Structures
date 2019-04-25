/**
 * 移除链表元素
 * <p>
 * /// Leetcode 203. Remove Linked List Elements
 * /// https://leetcode.com/problems/remove-linked-list-elements/description/
 *
 * @author bianxinhuan
 * @date 2019-04-24 21:48:24
 */
public class Solution3 {

    public ListNode removeElements(ListNode head, int val) {

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = new Solution3().removeElements(head, 6);
        System.out.println(head);
    }
}
