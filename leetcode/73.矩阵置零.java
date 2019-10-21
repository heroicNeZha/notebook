/*
 * @lc app=leetcode.cn id=73 lang=java
 *
 * [73] 矩阵置零
 */

// @lc code=start
class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[] lineFlag = new boolean[m];
        boolean[] colFlag = new boolean[n];
        // 标志位
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    lineFlag[i] = true;
                    colFlag[j] = true;
                }
            }
        }
        // 置零
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0 && (lineFlag[i] || colFlag[j])) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
// @lc code=end
