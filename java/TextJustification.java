import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vingc on 2017/6/15.
 * ref: https://leetcode.com/problems/text-justification/#/description
 * num: 68
 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

 You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

 Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

 For the last line of text, it should be left justified and no extra space is inserted between words.

 For example,
 words: ["This", "is", "an", "example", "of", "text", "justification."]
 L: 16.

 Return the formatted lines as:
 [
 "This    is    an",
 "example  of text",
 "justification.  "
 ]
 Note: Each word is guaranteed not to exceed L in length.
 */
public class TextJustification {
    static class Solution {
        public List<String> fullJustify( String[] words, int maxWidth ) {
            List<String> list = new ArrayList<String>();

            if( words == null || words.length == 0  ) {
                return list;
            }

            if(  maxWidth <= 0 ) {
                list.add( "" );
                return list;
            }

            if( words.length == 1 && words[0].length() == 0 ) {
                list.add( getSpaceStr( maxWidth ) );
                return list;
            }

            String[] line =  new String[ words.length * 2 ]; //save words and empty spaces in current line
            int len = 0; //length of current line.
            int cnt = 0; //count of words in current line.
            int index = 0;

            Arrays.fill( line, null );

            for( String word: words ) {
                if( word.length() == 0 ) {
                    continue;
                }

                if( cnt == 0 ) {
                    line[index++] = word;
                    len += word.length();
                    cnt++;
                    //check next;
                    continue;
                }
                else if( len + 1 + word.length() <= maxWidth ) {
                    line[index++] = " ";
                    line[index++] = word;
                    len += 1 + word.length();
                    cnt++;
                    //check next;
                    continue;
                }

                //full,don't include this word in current line;
                int remain = maxWidth - len;
                if( remain == 0 ) {
                    StringBuffer strb = new StringBuffer("");
                    for( int i = 0; i < index; i++ ) {
                        strb.append( line[i] );
                    }
                    list.add( strb.toString() );
                }
                else {
                    int space = cnt-1;
                    if( cnt == 1 ) {
                        space = 1;
                        line[index++] = "";
                    }
                    int avg = remain / space;
                    remain = remain % space;
                    String strAvg = getSpaceStr( avg );
                    String strExtra = getSpaceStr( avg+1 );
                    for( int i = 1; i <= index; i += 2 ) {
                        if( remain > 0 ) {
                            line[i] = line[i] + strExtra;
                            remain--;
                        }
                        else
                            line[i] = line[i] + strAvg;
                    }

                    StringBuffer strb = new StringBuffer("");
                    for( int i = 0; i < index; i++ ) {
                        strb.append( line[i] );
                    }
                    list.add( strb.toString() );
                }

                //initiate variable
                index = 0;
                line[index++] = word;
                cnt = 1;
                len = word.length();

            }

            //last line
            if( index > 0 ) {

                StringBuffer strb = new StringBuffer("");
                for( int i = 0; i < index; i++ ) {
                    strb.append( line[i] );
                }
                strb.append( getSpaceStr( maxWidth - len ) );
                list.add( strb.toString() );
            }

            return list;
        }

        private String getSpaceStr( int x ) {
            if( x == 0 ) {
                return "";
            }

            StringBuffer strb = new StringBuffer("");
            for( int i = 0; i < x; i++ ) {
                strb.append(" ");
            }

            return strb.toString();
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        List<String> ret = sol.fullJustify( words, 16 );
        for( String str: ret ) {
            System.out.println( str );
        }
    }
}
