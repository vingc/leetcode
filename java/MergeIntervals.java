import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by vingc on 2017/5/26.
 * ref: https://leetcode.com/problems/merge-intervals/#/description
 * num: 56
 Given a collection of intervals, merge all overlapping intervals.

 For example,
 Given [1,3],[2,6],[8,10],[15,18],
 return [1,6],[8,10],[15,18].
 */

public class MergeIntervals {
    static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    static class Solution {
        public List<Interval> merge(List<Interval> intervals) {
            //n*n
            List<Interval> res = new ArrayList<>();
            intervals.sort( new Comparator<Interval>() {
                 public int compare( Interval o1, Interval o2 ) {
                    return o1.start - o2.start;
                }
            } );

            if( intervals.size() == 0 ) {
                return res;
            }

            Interval inv1 = intervals.get( 0 );
            for( int i = 1; i < intervals.size(); i++ ) {

                Interval inv2 = intervals.get( i );
                if( inv1.end < inv2.start ) {
                    res.add( new Interval( inv1.start, inv1.end ) );
                    inv1 = inv2;
                    continue;
                }

                inv1.end = Math.max( inv1.end, inv2.end );
            }

            if( inv1 != null ) {
                res.add( new Interval( inv1.start, inv1.end ) );
            }

            return res;
        }

       /* public List<Interval> merge2(List<Interval> intervals) {

            List<Interval> res = new ArrayList<>();

        }*/
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        //[[2,3],[4,5],[6,7],[8,9],[1,10]]
        List<Interval> list = new ArrayList<>();
        list.add( new Interval(2,3) );
        list.add( new Interval(4,5) );
        list.add( new Interval(6,7) );
        list.add( new Interval(8,9) );
        list.add( new Interval(1,10) );
        List<Interval> res = sol.merge( list );
        for( Interval in: res ) {
            System.out.println( "["+in.start+","+in.end+"]" );
        }
    }
}
