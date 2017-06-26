/**
 * Created by vingc on 2017/6/26.
 * ref: https://leetcode.com/problems/edit-distance/#/description
 * num: 72
 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

 You have the following 3 operations permitted on a word:

 a) Insert a character
 b) Delete a character
 c) Replace a character

 */
public class EditDistance {
    static class Solution {
        public int minDistance(String word1, String word2) {

            if( word1.length() == 0 ) return word2.length(); //insert all in word2
            if( word2.length() == 0 ) return word1.length(); //delete all in word1

            if( word1.charAt(0) == word2.charAt(0) )
                return minDistance( word1.substring(1), word2.substring(1) );

            int cntI = 0;
            int cntD = 0;
            int cntR = 0;

            //insert
            cntI = 1 + minDistance( word1, word2.substring(1) );

            //delete
            cntD = 1 + minDistance( word1.substring(1), word2 );

            //replace
            cntR = 1 + minDistance( word1.substring(1), word2.substring(1) );

            return Math.min( cntI, Math.min( cntD, cntR ) );
        }

        /*
        Use f[i][j] to represent the shortest edit distance between word1[0,i) and word2[0, j). Then compare the last character of word1[0,i) and word2[0,j), which are c and d respectively (c == word1[i-1], d == word2[j-1]):

        if c == d, then : f[i][j] = f[i-1][j-1]

        Otherwise we can use three operations to convert word1 to word2:

        (a) if we replaced c with d: f[i][j] = f[i-1][j-1] + 1;

        (b) if we added d after c: f[i][j] = f[i][j-1] + 1;

        (c) if we deleted c: f[i][j] = f[i-1][j] + 1;

        Note that f[i][j] only depends on f[i-1][j-1], f[i-1][j] and f[i][j-1], therefore we can reduce the space to O(n) by using only the (i-1)th array and previous updated element(f[i][j-1]).

         int minDistance(string word1, string word2) {

            int l1 = word1.size();
            int l2 = word2.size();

            vector<int> f(l2+1, 0);
            for (int j = 1; j <= l2; ++j)
                f[j] = j;

            for (int i = 1; i <= l1; ++i)
            {
                int prev = i;
                for (int j = 1; j <= l2; ++j)
                {
                    int cur;
                    if (word1[i-1] == word2[j-1]) {
                        cur = f[j-1];
                    } else {
                        cur = min(min(f[j-1], prev), f[j]) + 1;
                    }

                    f[j-1] = prev;
                    prev = cur;
                }
                f[l2] = prev;
            }
            return f[l2];

        }  */

        public int minDistance2(String word1, String word2) {
            if( word1.length() == 0 ) return word2.length(); //insert all in word2
            if( word2.length() == 0 ) return word1.length(); //delete all in word1

            int len1 = word1.length();
            int len2 = word2.length();

            int[] f = new int[len2+1];
            int prev = 0;
            int cur = 0;

            for( int i = 1; i <= len2; i++ ) {
                f[i] = i;
            }

            for( int i = 1; i <= len1; i++ ) {
                prev = i; //convert 0..i to empty
                for( int j = 1; j <= len2; j++ ) {
                    if( word1.charAt(i-1) == word2.charAt(j-1) ) {
                        cur = f[j-1];
                    }
                    else {
                        cur = Math.min( Math.min(f[j-1],prev), f[j] ) + 1;
                    }

                    f[j-1] = prev;
                    prev = cur; //cache, cause we need f[j] again when we compare i and j+1
                }
                f[len2] = prev;
            }

            return f[len2];
        }

        public int minDistance3(String word1, String word2) {
            if (word1.length() == 0) return word2.length(); //insert all in word2
            if (word2.length() == 0) return word1.length(); //delete all in word1

            int len1 = word1.length();
            int len2 = word2.length();

            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 1; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int j = 1; j <= len2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]) ) + 1;
                    }
                }
            }

            return dp[len1][len2];
        }

    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.minDistance2("ab","a" ) );
    }
}
