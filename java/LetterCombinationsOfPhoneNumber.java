import java.util.ArrayList;
import java.util.List;

/**
 * Created by vingc on 2017/4/14.
 * ref: https://leetcode.com/problems/letter-combinations-of-a-phone-number/#/description
 * num: 17
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 */
public class LetterCombinationsOfPhoneNumber {
    static class Solution {
        private List<String> list;

        String[] button = new String[10];
        {
            button[2] = "abc";
            button[3] = "def";
            button[4] = "ghi";
            button[5] = "jkl";
            button[6] = "mno";
            button[7] = "pqrs";
            button[8] = "tuv";
            button[9] = "wxyz";
        }

        public List<String> letterCombinations(String digits) {

            list = new ArrayList<>();
            char[] comb = new char[digits.length()];

            if( digits.length() > 0 )
                combination( digits, 0, comb );

            return list;
        }

        private void combination( String digits, int index, char[] comb ) {
            int i = 0;
            int num = digits.charAt( index ) - '0';
            int len = digits.length();
            String str = button[num];

            for( i = 0; i < str.length(); i++ ) {
                comb[index] = str.charAt(i);
                if( index == len - 1 ) {
                    //end,produce the string and add it into list
                    list.add( new String( comb ) );
                }
                else {
                    //recursive
                    combination( digits, index+1, comb );
                }
            }
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.letterCombinations( "23" ) );
    }
}
