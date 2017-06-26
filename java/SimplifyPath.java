/**
 * Created by vingc on 2017/6/26.
 * ref: https://leetcode.com/problems/simplify-path/#/description
 * num: 71
 Given an absolute path for a file (Unix-style), simplify it.

 For example,
 path = "/home/", => "/home"
 path = "/a/./b/../../c/", => "/c"
 */
public class SimplifyPath {
    static class Solution {
        public String simplifyPath(String path) {
            if( path == null || path.length() == 0 ) {
                return path;
            }

            int len = path.length();
            char[] ret = new char[len];
            int[] slash = new int[len];
            int retCnt = 0;
            int slashCnt = 0;

            int idx = 0;
            while( idx < len ) {
                char ch = path.charAt( idx );
                if( ch == '/' ) {
                    if( retCnt > 0 && ret[retCnt-1] == '/' ) {
                        //ignore repeated slash
                    }
                    else if( retCnt > 1 && ret[retCnt-1] == '.' && ret[retCnt-2] == '/' ){
                        //deal with '/./',ignore current slash, back retCnt to last slash.
                        retCnt--;
                    }
                    else if( retCnt > 2 && ret[retCnt-1] == '.' && ret[retCnt-2] == '.' && ret[retCnt-3] == '/' ) {
                        //deal with "/../", back retCnt to upper directory,
                        if( slashCnt == 0 || slashCnt == 1 ) {
                            //current directory is root or null
                            retCnt = 1; //only keep the root '/'
                        }
                        else {
                            retCnt = slash[slashCnt-2]+1; //next space position in ret.
                            slashCnt = slashCnt-2+1; //next space position in slash
                        }
                    }
                    else {
                        ret[retCnt++] = ch;
                        slash[slashCnt++] = retCnt-1; //record the position of slash
                    }
                }
                else if( ch == '.' ) {

                    ret[retCnt++] = ch;

                }
                else {
                    ret[retCnt++] = ch;
                }

                idx++;
            }

            //check last '/..' or '/.'
            if( retCnt > 2 && ret[retCnt-1] == '.' && ret[retCnt-2] == '.' && ret[retCnt-3] == '/' ) {
                //deal with "/../", back retCnt to upper directory,
                if( slashCnt == 0 || slashCnt == 1 ) {
                    //current directory is root or null
                    retCnt = 1; //only keep the root '/'
                }
                else {
                    retCnt = slash[slashCnt-2]+1; //next space position in ret.
                    slashCnt = slashCnt-2+1; //next space position in slash
                }
            }
            else if( retCnt > 1 && ret[retCnt-1] == '.' && ret[retCnt-2] == '/' ) {
                retCnt--;
            }

            //remove last slash
            if( retCnt > 1 && ret[retCnt-1] == '/' ) {
                retCnt--;
            }

            return new String( ret, 0, retCnt );


        }
    }

    public static void main( String[] argc ) {
        Solution sol = new Solution();
        System.out.println( sol.simplifyPath("/home/") );
        System.out.println( sol.simplifyPath("/a/./b/../../c/") );
        System.out.println( sol.simplifyPath("/../") );
        System.out.println( sol.simplifyPath("/home//foo/") );
        System.out.println( sol.simplifyPath("/...") );
    }
}
