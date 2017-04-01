/**
 * Created by vingc on 2017/4/1.
 */
public class LongestPalindromicSubstring {
    static class Solution {
        public String longestPalindrome(String s) {
            int maxLen = 0;
            String maxPalStr = null;
            int i,j;
            int len = s.length();
            int[][] pal = new int[len][2]; // the length of palindromic string with i'th as the center.
            int oddPal = 0;
            int evenPal = 0;

            if( len == 0 )
                return null;
            //traverse the string, i is the center of palindromic string.
            i = 0;
            while( i < len ) {
                //pal[i][0] = 1;
                oddPal = 1;
                while( i - oddPal >= 0  && i + oddPal < len ) {
                    if( s.charAt( i-oddPal ) == s.charAt( i + oddPal ) ) {
                        oddPal++;
                    }
                    else
                    {
                        break;
                    }
                }

                evenPal = 0;
                while( i - evenPal >= 0 && i + 1 + evenPal < len ) {
                    if( s.charAt( i - evenPal ) == s.charAt( i + 1 + evenPal ) ) {
                        evenPal++;
                    }
                    else
                    {
                        break;
                    }
                }

                pal[i][0] = oddPal;
                pal[i][1] = evenPal;

                //move
                i++;
            }

            maxLen = 0;
            j = 0; //begin of the string,
            for( i = 0; i < len; i++ ) {
                if( 2 * pal[i][0] - 1 > maxLen ) {
                    maxLen = 2 * pal[i][0] - 1;
                    j = i - pal[i][0] + 1; //odd
                }
                if( 2* pal[i][1] > maxLen ) {
                    maxLen = 2 * pal[i][1];
                    j = i - pal[i][1] + 1; //even
                }
            }

            maxPalStr = s.substring( j, j + maxLen ); //even
            return maxPalStr;
        }

        public String longestPalindrome2(String s) {

            int i = 0; //the begin of palindromic string.
            int j = 0; //pos from i
            int len = s.length();
            int maxLen = len; //check the palindromic string length from max to min;

            while( maxLen > 0 ) {

                i = 0;
                while( i <= len - maxLen ) {
                    j = 0;
                    while( i + j < i + maxLen - j - 1 ) {
                        if( s.charAt( i + j ) == s.charAt( i + maxLen - j - 1 ) )
                            j++;
                        else
                            break;

                    }

                    if( i + j >= i + maxLen - j - 1 ) {
                        //find the palindromic
                        return s.substring( i, i + maxLen );
                    }

                    //move
                    i++;
                }

                maxLen--;
            }

            return null;
        }

        private int lo, maxLen;

        public String longestPalindrome3(String s) {
            int len = s.length();
            if (len < 2)
                return s;

            for (int i = 0; i < len-1; i++) {
                extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
                extendPalindrome(s, i, i+1); //assume even length.
            }
            return s.substring(lo, lo + maxLen);
        }

        private void extendPalindrome(String s, int j, int k) {
            while (j >= 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
                j--;
                k++;
            }
            if (maxLen < k - j - 1) {
                lo = j + 1;
                maxLen = k - j - 1;
            }
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        String str = "cbbb";
        System.out.println( sol.longestPalindrome3( str ) );
    }
}
