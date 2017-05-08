/**
 * Created by vingc on 2017/5/8.
 * ref: https://leetcode.com/problems/wildcard-matching/#/description
 * num 44
 Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 */
public class WildcardMatching {
    static class Solution {
        public boolean isMatch(String s, String p) {

            if( s == null || p == null ) {
                return false;
            }

            int m = s.length();
            int n = p.length();


            boolean[][] mat = new boolean[m+1][n+1]; //mat[i+1][j+1] is the result of matching 0..i in s with 0..j in p

            mat[0][0] = true; // s and p all is ""

            for( int i = 1; i < m+1; i++ ) {
                mat[i][0] = false; // if s is not "" but p is "";
            }

            for( int i = 1; i < n+1; i++ ) {
                if( p.charAt(i-1) == '*' ) {
                    mat[0][i] = mat[0][i-1];
                }
                else {
                    mat[0][i] = false;
                }
            }

            for( int i = 0; i < m; i++ ) {  //s
                char sch = s.charAt( i );
                for( int j = 0; j < n; j++ ) { //p
                    char pch = p.charAt( j );
                    if( sch == pch || pch == '?' ) {
                        //1. if p[j] = s[i] or ?, then matching result of s[0..i] with p[0..j] is the matching result of
                        //   s[0..i-1] with p[0..j-1];
                        mat[i+1][j+1] = mat[i][j];
                    }
                    else if( pch == '*' ) {
                        /* 2. if p[j] = *, then matching result mat[i][j] has some cases;
                             2.1) * represent 0, ignore *, result equal to mat[i][j-1];
                             2.2) * represent one or more char, result equal to mat[i-1][j];

                        */
                        mat[i+1][j+1] = mat[i+1][j]; //0
                        mat[i+1][j+1] |= mat[i][j+1]; // one or more

                    }
                    else {
                        mat[i+1][j+1] = false;
                    }
                }

            }

            return mat[m][n];
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.isMatch("aa","a") );
        System.out.println( sol.isMatch("aa","aa") );
        System.out.println( sol.isMatch("aaa","aa") );
        System.out.println( sol.isMatch("aa", "*") );
        System.out.println( sol.isMatch("aa", "a*") );
        System.out.println( sol.isMatch("ab", "?*") );
        System.out.println( sol.isMatch("aab", "c*a*b") );
    }
}
