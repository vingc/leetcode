/**
 * Created by vingc on 2017/4/11.
 * ref: https://leetcode.com/problems/longest-common-prefix/#/description
 * num: 14
 */
public class LongestCommonPrefixString {
    static class Solution {
        public String longestCommonPrefix(String[] strs) {

            char[] lcp;
            int i,j,k;
            int len; //len of every string.

            if( strs.length == 0 ) {
                return "";
            }

            lcp = strs[0].toCharArray();
            k   = lcp.length;

            for( i = 1; i < strs.length; i++ ) {
                len = strs[i].length();
                k = Math.min( len, k );
                for( j = 0; j < k; j++ ) {
                    if( strs[i].charAt(j) != lcp[j] ) {
                        k = j;
                        break;
                    }
                }
            }

            return new String( lcp, 0, k );
        }
    }

    public static void main( String[] args ) {
        String[] strs = {"avi","avid","av"};
        Solution sol = new Solution();
        System.out.println( sol.longestCommonPrefix( strs ) );
    }
}
