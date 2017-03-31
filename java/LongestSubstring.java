import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vingc on 2017/3/31.
 * ref: https://leetcode.com/problems/longest-substring-without-repeating-characters/#/description
 * num: 3
 */
public class LongestSubstring {
     static class Solution extends ArrayList<Character> {
        public int lengthOfLongestSubstring(String s) {

            int i = 0;
            int len = s.length();
            int tmpLen = 0;
            int max_len = 0;
            Character ch = 0;
            //ArrayList<Character> arr = new ArrayList<Character>( 52 );
            Solution sol = new Solution();

            while( i < len ) {
                ch = s.charAt( i );
                if( sol.contains( ch ) ) {
                    sol.removeRange( 0, sol.indexOf( ch )+1 );
                }

                sol.add( ch );
                tmpLen = sol.size();
                if( tmpLen > max_len )
                    max_len  = tmpLen;

                i++;
            }

            //System.out.println( Arrays.toString( sol.toArray() )  );
            return max_len;
        }

        public int lengthOfLongestSubstring2(String s) {
            int len = s.length();
            int tmpLen = 0;
            int maxLen = 0;
            int i = 0;
            Character ch = null;
            //Map< Character, Integer > map = new HashMap<>( 52 );
            ArrayList< Character > arr = new ArrayList<>( 52 );

            while( i < len ) {
                ch = s.charAt( i );
                if( arr.contains( ch ) ) {

                    while( arr.get( 0 ) != ch ) {
                        arr.remove( 0 );
                    }

                    arr.remove( 0 );
                }

                arr.add( ch );
                tmpLen = arr.size();
                if( tmpLen > maxLen )
                    maxLen  = tmpLen;

                i++;
            }
            return maxLen;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        String str = "abcabcbb";
        System.out.println( "maxLen:" + sol.lengthOfLongestSubstring2( str ) );
    }
}
