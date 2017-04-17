import java.util.*;

/**
 * Created by vingc on 2017/4/17.
 * ref: https://leetcode.com/problems/generate-parentheses/#/description
 * num: 22
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

 For example, given n = 3, a solution set is:

 [
 "((()))",
 "(()())",
 "(())()",
 "()(())",
 "()()()"
 ]
 */
public class GenerateParentheses {

    static class Solution {
        private void generate( List<String> list, String str, int left, int right, int n ) {
            if( str.length() == n * 2 ) {
                list.add( str );
                return;
            }

            if( left < n ) {
                generate( list, str+"(", left+1, right, n );
            }

            if( right < left ) {
                generate( list, str+")", left, right+1, n );
            }
        }

        public List<String> generateParenthesis(int n) {

            List<String> list = new ArrayList<>();

            String str = "";
            generate( list, str, 0, 0, n );

            return list;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.generateParenthesis( 3 ) );
    }
}
