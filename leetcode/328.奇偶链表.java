
/*
 * @lc app=leetcode.cn id=328 lang=java
 *
 * [328] 奇偶链表
 */

// @lc code=start
/**
 * Definition for singly-linked list. public class ListNode { int val; ListNode
 * next; ListNode(int x) { val = x; } }
 */
class Solution {
    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null)
            return head;
        ListNode evenHead = head.next;
        ListNode evenPointer = head.next;
        ListNode pointer = head;
        while (evenPointer.next != null) {
            pointer.next = evenPointer.next;
            pointer = pointer.next;
            evenPointer.next = pointer.next;
            if (evenPointer.next != null)
                evenPointer = evenPointer.next;
        }
        pointer.next = evenHead;
        return head;
    }
}
// @lc code=end
