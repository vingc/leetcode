/**
 * Created by vingc on 2017/5/8.
 * ref: https://leetcode.com/problems/multiply-strings/#/description
 * num: 43
 Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2.

 Note:

 The length of both num1 and num2 is < 110.
 Both num1 and num2 contains only digits 0-9.
 Both num1 and num2 does not contain any leading zero.
 You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
public class MultiplyStrings {
    static class Solution {
        private String multiOne( String num2, int num1, int idx1 ) {

            if( num1 == 0 || num2 == "0" ) {
                return "0";
            }

            int len2 = num2.length();
            int i;
            int total;
            int mod;
            int step = 0;
            StringBuffer ret = new StringBuffer( "" );

            for( i = 0; i < idx1; i++ ) {
                ret.append( 0 );
            }

            for( i = len2-1; i >= 0; i-- ) {
                int cur = num2.charAt( i ) - '0';

                total = cur * num1 + step;
                mod = total % 10;
                step = total / 10;

                ret.append( mod );
            }
            if( step != 0 ) {
                ret.append( step );
            }

            return ret.reverse().toString();
        }

        public String addStr( String str1, String str2 ) {
            int len1 = str1.length();
            int len2 = str2.length();

            if( len1 == 0 || str1 == "0") {
                return str2;
            }

            if( len2 == 0 || str2 == "0") {
                return str1;
            }

            StringBuffer ret = new StringBuffer( "" );
            int i = 0, j = 0;
            int step  = 0;
            int sum = 0;
            int mod = 0;

            while( i < len1 || i < len2 ) {
                sum = step + ( i < len1 ? str1.charAt(len1-1-i) - '0' : 0 ) + ( i < len2 ? str2.charAt(len2-1-i) - '0' : 0 );
                step = sum / 10;
                mod = sum % 10;
                ret.append( mod );
                i++;
            }

            if( step != 0 ) {
                ret.append( step );
            }

            return ret.reverse().toString();
        }

        public String multiply(String num1, String num2) {
            if( num1 == null || num2 == null ) {
                return null;
            }
            if( num1.length() == 0 || num2.length() == 0 ) {
                return "";
            }
            if( num1 == "0" || num2 == "0" ) {
                return "0";
            }

            //int len2 = num2.length();
            int len1 = num1.length();

            int i;
            String sum = "";
            for( i = len1 - 1; i >= 0; i-- ) {
                //use every num in num1 to multiply num2, then get the sum of all results.
                char ch = num1.charAt(i);
                String ret = multiOne( num2, ch - '0', len1 - 1 - i );
                if( ret.length() != 0 ) {
                    sum = addStr( ret, sum );
                }
            }

            return sum;
        }

        //ref: https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
        public String multiply2(String num1, String num2) {
            if (num1 == null || num2 == null) {
                return null;
            }
            if (num1.length() == 0 || num2.length() == 0) {
                return "";
            }
            if (num1 == "0" || num2 == "0") {
                return "0";
            }

            int m = num1.length();
            int n = num2.length();
            int[] pos = new int[m+n];
            for( int i = m-1; i >= 0; i-- )
            for( int j = n-1; j >= 0; j-- ) {
                int id = i+j;
                int mul = ( num1.charAt(i) - '0' ) * (num2.charAt(j) - '0' ) + pos[id+1];
                pos[id+1] = mul % 10;
                pos[id] = mul / 10;
            }

            StringBuffer str = new StringBuffer("");
            for( int i : pos ) {
                if( str.length() == 0 && i == 0 ) {
                    continue;
                }
                str.append( i );
            }

            return str.toString();
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.multiply2( "1", "9133" ) );
    }
}
