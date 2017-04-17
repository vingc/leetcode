import java.util.Stack;

/**
 * Created by vingc on 2017/4/17.
 * ref: https://leetcode.com/problems/valid-parentheses/#/description
 * num: 20
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */

public class ValidParentheses {

    static class Solution {
        private boolean isLeftParentheses( char ch ) {
            if( ch == '(' || ch == '{' || ch == '[' )
                return true;
            return false;
        }

        private boolean isValidRightParentheses( char last, char ch ) {

            if ( ( last == '{' && ch == '}')
                || ( last == '(' && ch == ')' )
                || ( last == '[' && ch == ']' ) ) {
                return true;
            }

            return false;
        }

        public boolean isValid(String s) {

            Stack<Character> stack = new Stack<>();
            int i = 0;
            for( i = 0; i < s.length(); i++ ) {
                if( isLeftParentheses( s.charAt( i ) ) ) {
                    stack.push( s.charAt( i ) );
                }
                else if( stack.empty() != true && isValidRightParentheses( stack.pop(), s.charAt( i ) ) ) {
                    //next;
                }
                else {
                    return false;
                }
            }
            if( stack.empty() )
                return true;
            else
                return false;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.isValid( "[{{}}]" ) );
    }
}
