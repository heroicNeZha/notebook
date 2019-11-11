/*
 * @lc app=leetcode.cn id=141 lang=java
 *
 * [141] 环形链表
 */

// @lc code=start
/**
 * Definition for singly-linked list. class ListNode { int val; ListNode next;
 * ListNode(int x) { val = x; next = null; } }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null)
                return false;
            if (fast == slow)
                return true;
            fast = fast.next.next;
            slow = slow.next;
        }
    }
}
// @lc code=end
