import java.util.Arrays;
import java.util.Stack;

/**
 * Created by vingc on 2017/4/21.
 * ref: https://leetcode.com/problems/longest-valid-parentheses/#/description
 * num: 32
 Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 For "(()", the longest valid parentheses substring is "()", which has length = 2.

 Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.
 */
/*
* */
public class LongestValidParentheses {
    static class Solution {
        public int longestValidParentheses(String s) {

            if( s == null || s.length() <= 1 ) {
                return 0;
            }

            int len = s.length();

            int[] valid = new int[ len + 1 ];
            Arrays.fill( valid, 0 ); //o invalid, 0 valid

            Stack<Character> stack = new Stack<>();
            Stack<Integer>   stack2 = new Stack<>(); //the index of character

            char ch;

            for( int i = 0; i < len; i++ ) {
                ch = s.charAt( i );
                if( ch == '(' ) {
                    stack.push( ch );
                    stack2.push( i );
                }
                else if( !stack.empty() && stack.peek() == '(' ) {
                    //valid parentheses.
                    stack.pop();
                    valid[stack2.pop()] = 1;
                    valid[i] = 1;
                }
            }

            System.out.println( Arrays.toString( valid ) );
            int max = 0;
            int cnt = 0;
            for( int i = 0; i < len + 1; i++ ) {
                if( valid[i] == 1 ) {
                    cnt++;
                }
                else {
                    if( cnt > max ) {
                        max = cnt;
                    }
                    cnt = 0;
                }
            }

            return max;
        }

        public int longestValidParentheses2(String s) {


            if( s == null || s.length() <= 1 ) {
                return 0;
            }

            int len = s.length();

            int[] valid = new int[ len + 1 ];
            Arrays.fill( valid, 0 ); //o invalid, 0 valid


            char ch;
            int max = 0;
            int cnt = 0;
            int lastInValid = 0;

            for( int i = 1; i < len; i++ ) {
                ch = s.charAt( i );
                if( ch == ')' ) {

                    //check if is pair.
                    if( lastInValid >= 0 && s.charAt(lastInValid) == '(' ) {
                        valid[lastInValid] = 1;
                        valid[i] = 1;

                        cnt = i-lastInValid;

                        //find last invalid
                        while( lastInValid >= 0 && valid[lastInValid] == 1 ) {
                            lastInValid--;
                            cnt++;
                        }

                        if( cnt > max ) max = cnt;
                    }
                    else {
                        lastInValid = i;
                    }

                }
                else {
                    lastInValid = i;
                }
            }


            return max;
        }

        private
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.longestValidParentheses("()" ) );
    }
}
