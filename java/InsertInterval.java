import java.util.ArrayList;
import java.util.List;

/**
 * Created by vingc on 2017/6/5.
 * ref: https://leetcode.com/problems/insert-interval/#/description
 * num: 57
 Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

 You may assume that the intervals were initially sorted according to their start times.

 Example 1:
 Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

 Example 2:
 Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

 This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].
 */
public class InsertInterval {
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    /**
     * Definition for an interval.
     * public class Interval {
     *     int start;
     *     int end;
     *     Interval() { start = 0; end = 0; }
     *     Interval(int s, int e) { start = s; end = e; }
     * }
     */
    static class Interval {
         int start;
         int end;
         Interval() { start = 0; end = 0; }
         Interval(int s, int e) { start = s; end = e; }
     }

    static class Solution {
        public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

            //check input
            if( intervals == null || newInterval == null ) {
                return null;
            }

            if( intervals.size() == 0 ) {
                intervals.add( newInterval );
                return intervals;
            }

            List<Interval> ret = new ArrayList<>();
            Boolean isInsert = false;

            for( int i = 0; i < intervals.size(); i++ ) {
                Interval cur = intervals.get( i );

                //newInterval is on right entirely
                if( cur.end < newInterval.start ) {
                    ret.add( new Interval( cur.start, cur.end ) );
                    continue;
                }

                //on left entirely.
                if( newInterval.end < cur.start ) {
                    //insert
                    if( isInsert == false ) {
                        ret.add(new Interval(newInterval.start, newInterval.end));
                        isInsert = true;
                    }
                    ret.add( new Interval( cur.start, cur.end ) );
                    continue;
                }

                //overlap
                newInterval.start = Math.min( newInterval.start, cur.start );
                newInterval.end = Math.max( newInterval.end, cur.end );
            }

            if( isInsert == false ) {
                ret.add( new Interval( newInterval.start, newInterval.end ) );
            }
            return ret;
        }
    }

    public static void main( String[] args ) {
        Solution        sol = new Solution();

        //example 1
        List<Interval> list = new ArrayList<>();

        list.add( new Interval( 1, 3 ) );
        list.add( new Interval( 6, 9 ) );

        List<Interval> ret = sol.insert( list, new Interval( 2, 5 ) );
        for( Interval inv: ret ) {
            System.out.println( "[" + inv.start + ", " + inv.end + "]" );
        }

        System.out.println( "==========================================" );

        //example 2
        list = new ArrayList<>();
        list.add( new Interval( 1, 2 ) );
        list.add( new Interval( 3, 5 ) );
        list.add( new Interval( 6, 7 ) );
        list.add( new Interval( 8, 10 ) );
        list.add( new Interval( 12, 16 ) );

        ret = sol.insert( list, new Interval( 4, 9 ) );
        for( Interval inv: ret ) {
            System.out.println( "[" + inv.start + ", " + inv.end + "]" );
        }
    }
}
