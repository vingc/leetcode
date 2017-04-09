/**
 * Created by vingc on 2017/4/10.
 * ref: https://leetcode.com/problems/integer-to-roman/#/description
 * num: 12
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 */

/**
* Roman:
* I(1)，V(5)，X(10)，L(50)，C(100)，D(500)，M(1000)
* 相同的数字连写，所表示的数等于这些数字相加得到的数，如 Ⅲ=3；
* 小的数字在大的数字的右边，所表示的数等于这些数字相加得到的数，如 Ⅷ=8、Ⅻ=12；
* 小的数字（限于 Ⅰ、X 和 C）在大的数字的左边，所表示的数等于大数减小数得到的数，如 Ⅳ=4、Ⅸ=9；
* 同样单位只能连续出现3次，如40不能表示为XXXX，而要表示为XL
**/

public class IntegerToRoman {
    static class Solution {
        public String intToRoman(int num) {

            if( num < 1 || num > 3999 ) {
                return null;
            }

            int i,k;
            int multi  = 0;
            char[] romain = new char[100]; //store the romain number

            k = 0; //the index of char array;

            /* check 1000 */
            multi = num / 1000;
            num = num % 1000;
            for( i = 0; i < multi; i++ ) {
                romain[k++] = 'M';
            }

            /* check 900 */
            if( num >= 900 ) {
                romain[k++] = 'C';
                romain[k++] = 'M';
                num = num % 900;
            }

            /* check 500 */
            multi = num / 500;
            num = num % 500;
            if( multi == 1 ) {
                romain[k++] = 'D';
            }

            /* check 100 */
            multi = num / 100;
            num = num % 100;
            if( multi == 4 ) {
                romain[k++] = 'C';
                romain[k++] = 'D';
            }
            else {
                for( i = 0; i < multi; i++ ) {
                    romain[k++] = 'C';
                }
            }

            /* check 90 */
            if( num >= 90 ) {
                romain[k++] = 'X';
                romain[k++] = 'C';
                num = num % 90;
            }

            /* check 50 */
            multi = num / 50;
            num = num % 50;
            if( multi == 1 ) {
                romain[k++] = 'L';
            }


            /* check 10 */
            multi = num / 10;
            num = num % 10;
            if( multi == 4 ) {
                romain[k++] = 'X';
                romain[k++] = 'L';
            }
            else {
                for( i = 0; i < multi; i++ ) {
                    romain[k++] = 'X';
                }
            }

            /* check 9 */
            if( num == 9 ) {
                romain[k++] = 'I';
                romain[k++] = 'X';
                num = 0;
            }

            /* check 5 */
            multi = num / 5;
            num = num % 5;
            if( multi == 1 ) {
                romain[k++] = 'V';
            }

            /* check 1 */
            multi = num;
            if( multi == 4 ) {
                romain[k++] = 'I';
                romain[k++] = 'V';
            }
            else {
                for( i = 0; i < multi; i++ ) {
                    romain[k++] = 'I';
                }
            }

            return  new String(romain,0,k);
        }

        public static String intToRoman2(int num) {
            String M[] = {"", "M", "MM", "MMM"};
            String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
            String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
            String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
            return M[num/1000] + C[(num%1000)/100] + X[(num%100)/10] + I[num%10];
        }

        public String intToRoman3(int num) {
            int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
            String[] strs = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

            StringBuilder sb = new StringBuilder();

            for(int i=0;i<values.length;i++) {
                while(num >= values[i]) {
                    num -= values[i];
                    sb.append(strs[i]);
                }
            }
            return sb.toString();
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();

        System.out.println( sol.intToRoman(1) );
        System.out.println( sol.intToRoman(2) );
        System.out.println( sol.intToRoman(3) );
        System.out.println( sol.intToRoman(4) );
        System.out.println( sol.intToRoman(5) );
        System.out.println( sol.intToRoman(6) );
        System.out.println( sol.intToRoman(7) );
        System.out.println( sol.intToRoman(8) );
        System.out.println( sol.intToRoman(9) );
        System.out.println( sol.intToRoman(10) );
        System.out.println( sol.intToRoman(11) );
        System.out.println( sol.intToRoman(12) );
        System.out.println( sol.intToRoman(51) );
        System.out.println( sol.intToRoman(99) );
        System.out.println( sol.intToRoman(199) );
        System.out.println( sol.intToRoman(299) );
        System.out.println( sol.intToRoman(1299) );
        System.out.println( sol.intToRoman(1999) );
    }
}
