/**
 * Created by vingc on 2017/6/9.
 * ref: https://leetcode.com/problems/valid-number/#/description
 * num: 65
 Validate if a given string is numeric.

 Some examples:
 "0" => true
 " 0.1 " => true
 "abc" => false
 "1 a" => false
 "2e10" => true
 Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 */

/*
We start with trimming.

If we see [0-9] we reset the number flags.
We can only see . if we didn't see e or ..
We can only see e if we didn't see e but we did see a number. We reset numberAfterE flag.
We can only see + and - in the beginning and after an e
any other character break the validation.
At the and it is only valid if there was at least 1 number and if we did see an e then a number after it as well.

So basically the number should match this regular expression:

[-+]?(([0-9]+(.[0-9]*)?)|.[0-9]+)(e[-+]?[0-9]+)?
*/
public class ValidNumber {
    static class Solution {
        private boolean isNumber( char ch ) {
            if( ch >= '0' && ch <= '9' ) {
                return true;
            }

            return false;
        }

        public boolean isNumber(String s) {
            if( s == null || s.length() == 0 ) {
                return false;
            }

            /* 1. all is number
            * 2.  decimal
            * 3. exponent */
            s = s.trim();
            if( s.length() == 0 ) {
                return false;
            }

            //+/-
            if( s.charAt( 0 ) == '+' || s.charAt( 0 ) == '-' ) {
                s = s.substring( 1 );
            }

            if( s.length() == 0 ) {
                return false;
            }

            boolean isExponent = false;
            boolean isDecimal = false;

            int len = s.length();
            for( int i = 0; i < len; i++ ) {
                if( isNumber( s.charAt( i ) ) ) {
                    continue;
                }
                else if( s.charAt( i ) == '+' || s.charAt( i ) == '-' ) {
                    if( i == 0 ) {
                        if( i+1 < len && ( isNumber( s.charAt( i+1) ) || s.charAt( i+1 ) == '.' ) ) {
                            continue;
                        }
                        return false;
                    }
                    if( s.charAt( i-1 ) == 'e' && i+1 < len && isNumber( s.charAt( i+1 ) ) ) {
                        continue;
                    }
                    return false;
                }
                else if( s.charAt( i ) == 'e' && isExponent == false ) {
                    if( i == 0 || i == len-1 ) {
                        return false;
                    }


                    //"23.e1",
                    if (( isNumber( s.charAt( i-1 ) ) || s.charAt( i-1 ) == '.' ) && ( isNumber( s.charAt( i+1 ) ) || (s.charAt( i+1 ) == '+' || s.charAt( i+1 ) == '-') )) {
                        isExponent = true;
                        continue;
                    }

                    return false;
                }
                else if( s.charAt(i) == '.' && isDecimal == false  && isExponent == false  ) {
                    if( i == 0 ) {
                        //".1"
                        if( i+1 < len && isNumber( s.charAt( i+1 ) ) ) {
                            isDecimal = true; //in case of two '.
                            continue;
                        }

                        //false
                        return false;

                    }
                    else if( i == len-1 ) {
                        //true， "1."
                        if( isNumber( s.charAt( i-1 ) ) ) {
                            continue;
                        }
                        //"1e."
                        return false;
                    }
                    else if ( isNumber( s.charAt( i-1 ) ) && ( isNumber( s.charAt( i + 1 ) ) || s.charAt( i+1 ) == 'e' ) ) {
                        isDecimal = true; //in case of two '.'
                        continue;
                    }
                    else {
                        return false;
                    }

                }
                else {
                    return false;
                }
            }

            return true;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.isNumber("" ) );
        System.out.println( sol.isNumber(" " ) );
        System.out.println( sol.isNumber("1" ) ); //true
        System.out.println( sol.isNumber("1.1" ) ); //true
        System.out.println( sol.isNumber("1e2" ) );//true
        System.out.println( sol.isNumber("e 3" ) );//false
        System.out.println( sol.isNumber(".1" ) );//true
        System.out.println( sol.isNumber("1." ) );//true
        System.out.println( sol.isNumber("1.e.1" ) );//false
        System.out.println( sol.isNumber("1.e+1" ) );//true
    }
}
