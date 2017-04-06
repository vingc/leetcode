/**
 * Created by vingc on 2017/4/7.
 * ref: https://leetcode.com/problems/container-with-most-water/#/description
 * num: 11
 */
public class ContainerWithMostWater {
    static class Solution {
        private int min( int a, int b ) {
            if( a < b )
                return a;
            else
                return b;
        }

        //O(n^2)
        public int maxArea(int[] height) {
            int i,j;
            int maxCon; //the most container
            int i1,i2; //the two i indexes of max container
            int container;
            int vert;
            int hori;

            maxCon = 0;
            i1 = 0;
            i2 = 0;
            for( i = 0; i < height.length; i++ )
                for( j = i+1; j < height.length; j++ )
                {
                    vert = min( height[i], height[j] );
                    hori = Math.abs( i - j );
                    container = vert * hori;

                    if( container > maxCon ) {
                        maxCon = container;
                        i1 = i;
                        i2 = j;
                    }
                }

            return maxCon;
        }

        //O(n)
        public int maxArea2(int[] height) {

            int i = 0; //left line
            int j = height.length - 1; //right line

            int max = 0;
            int container;

            while ( i < j ) {
                if(  height[i] <= height[j] ) {
                    container = height[i] * (j - i);
                    if( max < container ) {
                        max = container;
                    }
                    //left line shorter than right line, so we don't need compute i & (i..j),
                    //i&j is the max when left line is i
                    //so move the left line
                    i++;
                }
                else {
                    container = height[j] * (j - i);
                    if( max < container ) {
                        max = container;
                    }
                    //right line shorter than left line, so we don't need compute (i..j) & j,
                    //i&j is the max when right line is j
                    //so move the right line
                    j--;
                }
            }

            return max;
        }
    }

    public static void main( String[] args ) {
        int[] ver = {1,2,3,4,5,6};
        Solution sol = new Solution();
        System.out.println( sol.maxArea2( ver ) );
    }
}
