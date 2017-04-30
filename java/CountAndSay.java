/**
 * Created by vingc on 2017/4/30.
 * ref: https://leetcode.com/problems/count-and-say/#/description
 * num: 38
 The count-and-say sequence is the sequence of integers beginning as follows:
 1, 11, 21, 1211, 111221, ...

 1 is read off as "one 1" or 11.
 11 is read off as "two 1s" or 21.
 21 is read off as "one 2, then one 1" or 1211.
 Given an integer n, generate the nth sequence."1" is the 1th integer.

 Note: The sequence of integers will be represented as a string.
 */
public class CountAndSay {
    static class Solution {
        public String countAndSay(int n) {

            if( n < 1 ) {
                return "";
            }
            else if( n == 1 ) {
                return "1";
            }

            int i = 2;
            StringBuffer last = new StringBuffer("1");

            for( i = 2; i <= n; i++ ) {
                StringBuffer cur = new StringBuffer();
                //count last number ;represented as string
                int cnt = 1;
                int j;
                for( j = 1; j < last.length(); j++ ) {
                    if( last.charAt( j ) == last.charAt( j - 1 ) ) {
                        cnt++;
                    }
                    else {
                        cur.append(cnt).append(last.charAt(j-1));
                        cnt = 1;
                    }
                }

                //add the tail
                cur.append(cnt).append(last.charAt(j-1));
                last = cur;
            }

            return last.toString();
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.countAndSay(5) );
    }
}
