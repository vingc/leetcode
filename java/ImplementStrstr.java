/**
 * Created by vingc on 2017/4/20.
 * ref: https://leetcode.com/problems/implement-strstr/#/description
 * num: 28
 Implement strStr().

 Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */

public class ImplementStrstr {
    static class Solution {

        //O(nk)
        public int strStr(String haystack, String needle) {
            int stackLen = haystack.length();
            int needleLen = needle.length();
            int i, j, k;

            if  (needleLen > stackLen ) {
                return -1;
            } else if ( needleLen == 0 ) {
                return 0;
            }

            for (i = 0; i < stackLen - needleLen + 1; i++) {
                j = i;
                k = 0;
                while (k < needleLen && haystack.charAt(j) == needle.charAt(k)) {
                    j++;
                    k++;
                }

                if ( k == needleLen ) {
                    //found,
                    return i;
                }

            }

            return -1;
        }


        //O(n) KMP pattern
        public int strStr2(String haystack, String needle) {
            int stackLen = haystack.length();
            int needleLen = needle.length();
            int i, j, k;

            if ( needleLen > stackLen) {
                return -1;
            }
            else if ( needleLen == 0 ) {
                return 0;
            }

            int[] lps = new int[needleLen];

            computeLps( needle, lps );

            j = 0;
            for( i = 0; i < stackLen; ) {
                if( haystack.charAt( i ) == needle.charAt( j ) ) {
                    i++;
                    j++;
                    if( j == needleLen ) {
                        //find the matched
                        return i - j;
                    }
                }
                else {
                    if( j > 0 ) {
                        j = lps[j-1]; //can't match at j, so skip lps[j-1] char in needle.
                    }
                    else {
                        i++;//skip the char in haystack, can't find proper prefix
                    }

                }
            }

            return -1;
        }

        private void computeLps(String needle, int[] lps) {
            int i = 1; //traverse from 1.
            int j = 0;
            int len = needle.length();

            lps[0] = 0; //always zero

            while ( i < len ) {
                if( needle.charAt( i ) == needle.charAt( j ) ) {
                    lps[i] = j+1;
                    i++;
                    j++;
                }
                else {
                    //can't match at j, then skip lps[j-1] to check again
                    if( j > 0 ) {
                        j = lps[j-1];
                    }
                    else {
                        lps[i] = 0; //match nothing proper prefix
                        i++;
                    }
                }
            }
        }
    }
    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.strStr( "abcd", "abcde" ) );
    }
}
