import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by vingc on 2017/4/5.
 * ref: https://leetcode.com/problems/regular-expression-matching/#/description
 * num: 10
 */
public class RegularExpressionMatching {
    /*
    1, If p.charAt(j) == s.charAt(i) :  dp[i+1][j+1] = dp[i][j];
    2, If p.charAt(j) == '.' : dp[i+1][j+1] = dp[i][j];
    3, If p.charAt(j) == '*':
       here are two sub conditions:
                   1   if p.charAt(j-1) != s.charAt(i) : dp[i+1][j+1] = dp[i+1][j-1]  //in this case, a* only counts as empty
                   2   if p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.':
                                  dp[i+1][j+1] = dp[i][j+1]    //in this case, a* counts as multiple a
                               or dp[i+1][j+1] = dp[i+1][j]   // in this case, a* counts as single a
                               or dp[i+1][j+1] = dp[i+1][j-1]   // in this case, a* counts as empty
     */
    static class Solution {
        public boolean isMatch(String s, String p) {
            //Pattern  pat = Pattern.compile( p );
           // Matcher mat = pat.matcher( s );
            //return s.matches(p);

            //the first char of p can't be *
            if( p.charAt( 0 ) == '*' )
                return false;

            int slen = s.length();
            int plen = p.length();
            boolean[][] state = new boolean[ slen + 1 ][ plen + 1 ];

            int i = 0;
            int j = 0;

            state[0][0] = true;

            //p[0...i] matches empty only if p[i] is * and p[0..i-2] matches empty
            for( i = 0; i < plen; i++ ) {
                if( p.charAt( i ) == '*' ) {
                    state[0][i+1] = state[0][ i - 1 ];
                }
            }

            for( i = 0; i < slen; i++ ) {
                for( j = 0; j < plen; j++ ) {
                    if( s.charAt(i) == p.charAt(j) || p.charAt(j) == '.' ) {
                        state[i+1][j+1] = state[i][j];
                    }
                    else if( p.charAt(j) == '*' ) {
                        state[i+1][j+1] = state[i+1][j-1]; //a* matches the empty.
                        if( s.charAt(i) == p.charAt(j-1) || p.charAt(j-1) == '.' ) {
                            //There is no need to count the case where a* is used a SINGLE time. In each step when you see an * you either use it or you don't.
                            //state[i+1][j+1] |= state[i+1][j]; //a* matches the a
                            state[i+1][j+1] |= state[i][j+1]; //a* matches multiple a
                        }
                    }
                }
            }

            return state[slen][plen];
        }
    }

    public static void main( String[] args ) {
        Solution sol  = new Solution();

        System.out.println( sol.isMatch( "aa", "aa" ) );
        System.out.println( sol.isMatch( "aa", "a." ) );
        System.out.println( sol.isMatch( "aa", "a*" ) );
        System.out.println( sol.isMatch( "aab", "a*" ) );
    }
}
