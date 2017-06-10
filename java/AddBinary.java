/**
 * Created by vingc on 2017/6/10.
 * ref: https://leetcode.com/problems/add-binary/#/description
 * num: 67
 Given two binary strings, return their sum (also a binary string).

 For example,
 a = "11"
 b = "1"
 Return "100".
 */
public class AddBinary {
    static class Solution {
        public String addBinary(String a, String b) {
            if( a == null || a.length() == 0 ) {
                return b;
            }
            if( b == null || b.length() == 0 ) {
                return a;
            }

            StringBuffer ret = new StringBuffer("");
            //-hpmmhgggx3
            int aLen = a.length();
            int bLen = b.length();
            int ida = 0;
            int idb = 0;
            int sum = 0;
            int keep = 0;

            while( ida < aLen || idb < bLen ) {
                sum = (ida < aLen?(a.charAt(aLen-ida-1)-'0'):0) + (idb < bLen?(b.charAt(bLen-idb-1)-'0'):0) + keep;
                keep = sum / 2;
                sum = sum % 2;

                ret.append( sum );
                ida++;
                idb++;
            }

            if( keep == 1 ) {
                ret.append( keep );
            }

            return ret.reverse().toString();
        }

    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.addBinary( "1011", "0101" ) );
    }
}
