import java.util.Arrays;

/**
 * Created by vingc on 2017/6/9.
 * ref: https://leetcode.com/problems/plus-one/#/description
 * num: 66
 Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.

 You may assume the integer do not contain any leading zero, except the number 0 itself.

 The digits are stored such that the most significant digit is at the head of the list.
 */
public class PlusOne {
    static class Solution {
        public int[] plusOne(int[] digits) {
            if( digits == null || digits.length == 0 ) {
                return digits;
            }

            int keep = 1;
            int sum = 0;

            for( int i = digits.length - 1; i >= 0; i-- ) {
                sum = digits[i] + keep;
                keep = sum / 10;
                digits[i] = sum % 10;
            }
            int[] ret;
            if( keep == 1 ) {
                ret = new int[digits.length+1];
                for( int i = 0; i < digits.length; i++ ) {
                    ret[i+1] = digits[i];
                }
                ret[0] = keep;
            }
            else {
                ret = digits;
            }

            return ret;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        int[] ret = sol.plusOne( new int[]{9,9} );
        for( int i: ret ) {
            System.out.print( i + "," );
        }
    }
}
