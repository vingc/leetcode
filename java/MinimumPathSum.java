/**
 * Created by vingc on 2017/6/9.
 * ref: https://leetcode.com/problems/minimum-path-sum/#/description
 * num: 64
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.
 */
public class MinimumPathSum {
    static class Solution {
        //DP
        public int minPathSum(int[][] grid) {

            if (grid == null || grid.length == 0) {
                return 0;
            }

            int m = grid.length;
            int n = grid[0].length;
            int[][] dp = new int[m][n];

            for (int i = 0; i < m; i++)
                for (int j = 0; j < n; j++) {
                    if (i == 0 && j == 0) {
                        dp[i][j] = grid[i][j];
                    } else if (i == 0) {
                        dp[i][j] = grid[i][j] + dp[i][j - 1];
                    } else if (j == 0) {
                        dp[i][j] = grid[i][j] + dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j] + grid[i][j], dp[i][j - 1] + grid[i][j]);
                    }
                }

            return dp[m - 1][n - 1];

        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[][] grid = { {1,3,2},{4,5,6},{7,8,9} };
        System.out.println( sol.minPathSum( grid ) );
    }
}
