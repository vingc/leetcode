import java.util.*;

/**
 * Created by vingc on 2017/4/20.
 * ref: https://leetcode.com/problems/substring-with-concatenation-of-all-words/#/description
 * num: 30

 You are given a string, s, and a list of words, words, that are all of the same length.
 Find all starting indices of substring(s) in s that is a concatenation of each word in words exactly once
 and without any intervening characters.

 For example, given:
 s: "barfoothefoobarman"
 words: ["foo", "bar"]

 You should return the indices: [0,9].
 (order does not matter).

 */
public class SubstringWithConcatenationOfAllWords {
    static class Solution {
        public List<Integer> findSubstring(String s, String[] words) {

            List<Integer> list = new LinkedList<>();

            if( s == null || words == null || words.length == 0 || s.length() == 0 || words[0].length() == 0 ) {
                return list;
            }

            //s = s.toUpperCase();

            int strLen = s.length();
            int keyLen = words[0].length();
            int keyCnt = words.length;
            int subStrLen = keyLen * keyCnt;

            int[] wordMap = new int[127]; //store the occurrence times of characters respectively in all words.
            int[] strMap = new int[127]; //store the occurrence times of characters respectively in substring of s.
            Map<String,Integer> wordMap2 = new HashMap<>();

            int i,j;
            for( i = 0; i < wordMap.length; i++ ) {
                wordMap[i] = 0;
                strMap[i] = 0;
            }

            //count the occurrence times of characters in all words
            for( String str: words ) {
                //str = str.toUpperCase();
                keyLen = str.length();
                for( i = 0; i < keyLen; i++ ) {
                    wordMap[str.charAt(i)]++;
                }
                //same word maybe exist move than once.
                if( wordMap2.containsKey( str ) )
                    wordMap2.put( str, wordMap2.get(str) + 1 );
                else
                    wordMap2.put( str, 1 );
            }

            for( i = 0; i < strLen; i++ ) {
                if( i < subStrLen - 1 ) {
                    strMap[s.charAt(i)]++;
                }
                else {

                    //move the window of length subStrLen,
                    strMap[s.charAt(i)]++;
                    if( i >= subStrLen )
                        strMap[s.charAt(i-subStrLen)]--;

                    //if the characters in the subStr, have same numbers of characters respectively.
                    if( isEqualMap( strMap, wordMap ) ) {
                        //then check substring has all same words

                        int begin = i - subStrLen + 1;
                        int end = begin + keyLen;

                        Map<String,Integer> wordMapTemp = new HashMap<>( wordMap2 );
                        for( j = 0; j < keyCnt; j++ ) {
                            String str = s.substring( begin, end );
                            Integer val = wordMapTemp.get( str );
                            if( val == null ) {
                                break;
                            }

                            //same word maybe exist more than once in array words.
                            if( val > 1 ) {
                                wordMapTemp.put( str, val - 1 );
                            }
                            else {
                                //remove from the wordMapTemp
                                wordMapTemp.remove( str );
                            }

                            begin += keyLen;
                            end = begin + keyLen;
                        }
                        //after double check, find a proper substring
                        if( j == keyCnt )
                            list.add(i - subStrLen + 1);
                    }
                }
            }

            return list;
        }

        private boolean isEqualMap( int[] map1, int[] map2 ) {
            for( int i = 0; i < map1.length; i++ ) {
                if( map1[i] != map2[i] ) {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.findSubstring( "barfoothefoobarman", new String[]{"bar","foo"} ) );


    }
}
