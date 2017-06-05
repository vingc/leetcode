/**
 * Created by vingc on 2017/6/5.
 * ref: https://leetcode.com/problems/length-of-last-word/#/description
 * num: 58
 iven a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 For example,
 Given s = "Hello World",
 return 5.
 */
public class LengthOfLastWord {
    static class Solution {
        public int lengthOfLastWord( String s ) {
            if( s == null || s.length() == 0 ) {
                return 0;
            }

            //skip the empty at the tail.
            int len = s.length() - 1;

            while(  len >= 0 && s.charAt( len ) == ' ' ) {
                len--;
            }

            if( len < 0 ) {
                return 0;
            }

            //count the chars of last word
            int cnt = 0;
            for( int i = len; i >= 0; i-- ) {
                char ch = s.charAt( i );
                if( ( ch >= 'a' && ch <= 'z' ) || ( ch >= 'A' && ch <= 'Z' ) ) {
                    cnt++;
                }
                else {
                    break;
                }

            }

            return cnt;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.lengthOfLastWord( "a  bbb" ) );
        System.out.println( sol.lengthOfLastWord( "a  bbb " ) );
        System.out.println( sol.lengthOfLastWord( "a  z " ) );
        System.out.println( sol.lengthOfLastWord( "  " ) );
        System.out.println( sol.lengthOfLastWord( null ) );
    }
}
