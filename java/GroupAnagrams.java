import java.util.*;

/**
 * Created by vingc on 2017/5/13.
 * ref: https://leetcode.com/problems/anagrams/#/description
 * num: 49
 Given an array of strings, group anagrams together.

 For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"],
 Return:

 [
 ["ate", "eat","tea"],
 ["nat","tan"],
 ["bat"]
 ]
 Note: All inputs will be in lower-case.
 */
public class GroupAnagrams {
    static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> totalList = new ArrayList<>();
            Map<String,List<String>> map = new HashMap<>();

            for( int i = 0; i < strs.length; i++ ) {
                //get the array representing the occurrence times of char in string.
                int[] charCnt = new int[26];
                for( int j = 0; j < strs[i].length(); j++ ) {
                    charCnt[ strs[i].charAt(j) - 'a' ]++;
                }
                //convert the array to string,
                String arr = Arrays.toString( charCnt );
                //add to the list have anagram strings.
                if( map.containsKey( arr ) ) {
                    List<String> subList = map.get( arr );
                    subList.add( strs[i] );
                }
                else {
                    List<String> subList = new ArrayList<>();
                    subList.add( strs[i] );
                    map.put( arr, subList );
                }
            }

            for( Map.Entry<String,List<String>> entry: map.entrySet() ) {
                totalList.add( entry.getValue() );
            }

            return totalList;
        }
    }
    public static void main( String[] args ) {
        Solution sol = new Solution();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        List<List<String>> list = sol.groupAnagrams( strs );
        for( List<String> sub: list ) {
            System.out.println( sub.toString() );
        }
    }
}
