/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode temp;
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode pointer = dummyHead;
        while (pointer.next != null) {
            if (pointer.next.next != null) {
                temp = pointer.next;
                pointer.next = temp.next;
                temp.next = pointer.next.next;
                pointer.next.next = temp;
                pointer = pointer.next.next;
            } else {
                break;
            }
        }
        return dummyHead.next;
    }
}
// @lc code=end
