/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [2] 两数相加
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode p = head;
        int one = 0, ten = 0;

        while (l1 != null && l2 != null) {
            one = l1.val + l2.val + ten;
            ten = one > 9 ? 1 : 0;
            one = one % 10;
            p.next = new ListNode(one);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            one = l1.val + ten;
            ten = one > 9 ? 1 : 0;
            one = one % 10;
            p.next = new ListNode(one);
            p = p.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            one = l2.val + ten;
            ten = one > 9 ? 1 : 0;
            one = one % 10;
            p.next = new ListNode(one);
            p = p.next;
            l2 = l2.next;
        }
        if (ten != 0) {
            p.next = new ListNode(ten);
            p = p.next;
        }
        return head.next;
    }
}
// @lc code=end
