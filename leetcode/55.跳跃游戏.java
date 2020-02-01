/*
 * @lc app=leetcode.cn id=55 lang=java
 *
 * [55] 跳跃游戏
 */

// @lc code=start
class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length < 2)
            return true;
        int[] toggets = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            toggets[i] = nums[i] + i;
        }
        // 排序
        // quicksort(toggets, 0, toggets.length - 1);
        bubbleSort(toggets, toggets.length);
        // 贪心
        int max = toggets[0];
        for (int i = 0; i <= max; i++) {
            if (max < toggets[i]) {
                max = toggets[i];
            }
            if (max >= toggets.length - 1)
                return true;
        }
        return false;
    }

    public int division(int[] nums, int left, int right) {
        int flag = nums[(left + right) / 2];
        nums[(left + right) / 2] = nums[left];
        nums[left] = flag;
        while (left < right) {
            while (left < right && nums[right] >= flag) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= flag) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = flag;
        return left;
    }

    public void quicksort(int[] nums, int start, int end) {
        if (start < end) {
            int base = division(nums, start, end);
            quicksort(nums, start, base - 1);
            quicksort(nums, base + 1, end);
        }
    }

    public void bubbleSort(int[] arr, int n) {
        if (n <= 1)
            return; // 如果只有一个元素就不用排序了

        for (int i = 0; i < n; ++i) {
            // 提前退出冒泡循环的标志位,即一次比较中没有交换任何元素，这个数组就已经是有序的了
            boolean flag = false;
            for (int j = 0; j < n - i - 1; ++j) { // 此处你可能会疑问的j<n-i-1，因为冒泡是把每轮循环中较大的数飘到后面，
                // 数组下标又是从0开始的，i下标后面已经排序的个数就得多减1，总结就是i增多少，j的循环位置减多少
                if (arr[j] > arr[j + 1]) { // 即这两个相邻的数是逆序的，交换
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag)
                break;// 没有数据交换，数组已经有序，退出排序
        }
    }
}
// @lc code=end
