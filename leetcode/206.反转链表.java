/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    // 遍历
    // public ListNode reverseList(ListNode head) {
    // ListNode newHead = new ListNode(0);
    // ListNode cur = head;
    // ListNode nxt;
    // while (cur != null) {
    // nxt = cur.next;
    // cur.next = newHead.next;
    // newHead.next = cur;
    // cur = nxt;
    // }
    // return newHead.next;
    // }
    // 递归
    public ListNode reverseList(ListNode head) {
        ListNode newHead = new ListNode(0);
        ListNode cur = head;
        ListNode nxt;
        while (cur != null) {
            nxt = cur.next;
            cur.next = newHead.next;
            newHead.next = cur;
            cur = nxt;
        }
        return newHead.next;
    }
}
// @lc code=end
