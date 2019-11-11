/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 */

// @lc code=start
/**
 * Definition for singly-linked list. class ListNode { int val; ListNode next;
 * ListNode(int x) { val = x; next = null; } }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        int cLength = 0;
        if (head == null || head.next == null)
            return null;
        ListNode fast = head.next;
        ListNode slow = head;
        while (true) {
            if (fast == null || fast.next == null)
                return null;
            if (fast == slow){
                do{
                    slow = slow.next;
                    cLength++;
                }while(fast!=slow);
                slow = head;
                fast = head;
                for(;cLength>0;cLength--){
                    slow = slow.next;
                }
                while(slow!=fast){
                slow = slow.next;
                fast = fast.next;
                }
                return slow;
            }
            else{
                fast = fast.next.next;
                slow = slow.next;
            }
        }
    }
}
// @lc code=end
