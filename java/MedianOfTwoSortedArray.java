/**
 * Created by vingc on 2017/3/31.
 * ref: https://leetcode.com/problems/median-of-two-sorted-arrays/#/description
 * num: 4
 */
public class MedianOfTwoSortedArray {
    //O(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        //1. merge the sorted arrays
        int i,j,k;
        int m = nums1.length;
        int n = nums2.length;
        int[] merge = new int[ m+n ];

        i = 0;
        j = 0;
        k = 0;

        while( i < m && j < n ){
            if( nums1[i] < nums2[j] ) {
                merge[k++] = nums1[i++];
            }
            else {
                merge[k++] = nums2[j++];
            }
        }

        while( i < m )
            merge[k++] = nums1[i++];
        while( j < n )
            merge[k++] = nums2[j++];

        //2. caculate the median
        int km = k/2;
        if( k % 2 == 0 ) {
            //even
            return (double)( merge[km - 1] + merge[km] ) / 2;
        }
        else {
            return (double)merge[km];
        }
    }

   //ref: https://discuss.leetcode.com/topic/4996/share-my-o-log-min-m-n-solution-with-explanation
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {

        int m = nums1.length;
        int n = nums2.length;

        /* let m to be the smaller one */
        if( m > n )
        {
            return findMedianSortedArrays2( nums2, nums1 );
        }

        int max_left;
        int min_right;

        int min = 0;
        int max = m;
        int med = ( m + n + 1 ) / 2;
        int i; // the index of nums1;
        int j; // the index of nums2

        while( min <= max ) {
            i = (min+max)/2;
            j = med - i;

            //let i+j = med, and all left smaller than all right
            if ( i > 0 && nums1[ i-1 ] > nums2[ j ] ) {
                // i is too big, decrease it.
                max = i-1;
            }
            else if ( i < m && nums2[ j-1 ] > nums1[ i ] ) {
                //i is too small, increase it
                min = i+1;
            }
            else if ( ( i == 0 || j == n || nums1[ i-1 ] < nums2[ j ] ) &&
                      ( j == 0 || i == m || nums2[ j-1 ] < nums1[ i ] ) ) {
                    //i is perfect
                if( i == 0 )
                    max_left = nums2[j-1];
                else if ( j == 0 )
                    max_left = nums1[i-1];
                else
                    max_left = Math.max( nums2[j-1], nums1[i-1] );

                //the num in left part is more than right part by one.
                if( (m+n) % 2 == 1 )
                    return max_left;

                if( i == m )
                    min_right = nums2[j];
                else if( j == n )
                    min_right = nums1[i];
                else
                    min_right = Math.min( nums2[j], nums1[i] );

                 return ( max_left + min_right ) / 2.0;
            }
        }

        return 0;
    }

    public static void main( String[] args ) {
        int[] a = {6,8,9};
        int[] b = {1,2,3,4,5,7,10};
        MedianOfTwoSortedArray mots = new MedianOfTwoSortedArray();

        System.out.println( mots.findMedianSortedArrays2( a, b ) );

        a = new int[]{1,3,5};
        b = new int[]{2,4};
        System.out.println( mots.findMedianSortedArrays2( a, b ) );
    }
}
