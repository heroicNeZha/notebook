/*
 * @lc app=leetcode.cn id=215 lang=java
 *
 * [215] 数组中的第K个最大元素
 */

// @lc code=start
class Solution {
    int[] heap;
    int index = 0;

    public int findKthLargest(int[] nums, int k) {
        heap = new int[k];
        for (int i : nums) {
            if (i > nums[0]) {
                push(val);
            }
        }
        return nums[0];
    }

    public void push(int val) {
        if (index < heap.length) {
            heap[index++] = val;
        }
        while (key < 1) {
            return;
        }
        if (heap[(key - 1) / 2] > heap[key]) {
            int temp = heap[key];
            heap[key] = heap[(key - 1) / 2];
            heap[(key - 1) / 2] = temp;
            balance(heap, (key - 1) / 2);
        }
    }

    // 2i + 1,2i + 2
    public void balance(int i) {
        if (i > index)
            return;
        int maxindex = heap[i]<=heap[2i+1]?heap[2i+1]<=heap[2i+2]?i:
    }
}
// @lc code=end
