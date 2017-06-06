import java.util.ArrayList;
import java.util.List;

/**
 * Created by vingc on 2017/6/6.
 * ref: https://leetcode.com/problems/permutation-sequence/#/description
 * num: 60
 The set [1,2,3,…,n] contains a total of n! unique permutations.

 By listing and labeling all of the permutations in order,
 We get the following sequence (ie, for n = 3):

 "123"
 "132"
 "213"
 "231"
 "312"
 "321"
 Given n and k, return the kth permutation sequence.

 Note: Given n will be between 1 and 9 inclusive.
 */
public class PermutationSequence {
    static class Solution {
        /* my solution likes the way of this guy: https://discuss.leetcode.com/topic/17348/explain-like-i-m-five-java-solution-in-o-n/3 */
        public String getPermutation(int n, int k) {

            List<Integer> list = new ArrayList<>();
            //get all, sort, then find the kth in list.

            //how to find the kth directly.

            int[] poly = new int[n];
            //k = x1*(n-1)! + x2*(n-2)! + x3*(n-3)! +....+ xt*0!;
            int remain = k-1;
            int divisor = 1; //will be (n-1)!
            for( int i = 1; i <= n-1; i++ ) {
                divisor *= i;
            }

            //k must be from 1 to n!;
            if( k > n * divisor ) {
                return null;
            }

            //poly[n-1] save the factor of (n-1)!
            int cnt = n-1;
            while( remain > 0 && cnt > 0 ) {
                poly[cnt] = remain / divisor;
                remain = remain % divisor;
                divisor /= cnt;
                cnt--;
            }

            poly[0] = 0;

            if( remain > 0 && cnt <= 0 ) {
                //something wrong.
                return null;
            }

            //easy to get the xth bigger number in 1..n
            cnt = 1;
            while( cnt <= n ) {
                list.add( cnt );
                cnt++;
            }

            StringBuffer sb =  new StringBuffer("");

            for( int i = n-1; i >= 0; i-- ) {
                sb.append( list.remove(poly[i]) );
            }

            return sb.toString();

        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        System.out.println( sol.getPermutation( 3, 1 ) );
    }
}
