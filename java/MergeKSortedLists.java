/**
 * Created by vingc on 2017/4/18.
 * ref: https://leetcode.com/problems/merge-k-sorted-lists/#/description
 * num: 23
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 */
public class MergeKSortedLists {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        private ListNode mergeLists( ListNode list1, ListNode list2 ) {
            if( list1 == null ) {
                return list2;
            }
            if( list2 == null ) {
                return list1;
            }

            ListNode head = null;
            ListNode last = null;
            if( list1.val <= list2.val ) {
                head = list1;
                last = head;

                list1 = list1.next;
            }
            else {
                head = list2;
                last = head;
                list2 = list2.next;
            }

            while( list1 != null && list2 != null ) {
                if( list1.val <= list2.val ) {
                    last.next = list1;
                    last = last.next;
                    list1 = list1.next;
                }
                else {
                    last.next = list2;
                    last = last.next;
                    list2 = list2.next;
                }
            }

            if( list1 != null ) {
                last.next = list1;
            }
            else {
                last.next = list2;
            }

            return head;
        }

        public ListNode mergeKLists( ListNode[] lists ) {

            //merge and sort
            //sort and merge
            ListNode ret = null;
            int i = 0;
            for( i = 0; i < lists.length; i++ ) {
                ret = mergeLists( ret, lists[i] );
            }

            return ret;
        }

        //O(nklogk)
        public ListNode mergeKLists2( ListNode[] lists ) {

            //merge and sort
            //sort and merge
            if( lists.length == 1 ) {
                return lists[0];
            }
            else  if( lists.length == 0 ) {
                return null;
            }
            ListNode[] ret;

            if( lists.length % 2 != 0 )
                ret = new ListNode[ lists.length / 2 + 1 ];
            else
                ret = new ListNode[ lists.length / 2 ];

            int i = 0;
            for( i = 0; i < lists.length; i+=2 ) {
                if( i + 1 < lists.length )
                    ret[i/2] = mergeLists( lists[i], lists[i+1] );
                else
                    ret[i/2] = lists[i];
            }

            return mergeKLists2( ret );
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        ListNode head = new ListNode( 1 );
        head.next = new ListNode( 2 );
        head.next.next = new ListNode( 3 );
        ListNode head2 = new ListNode( 4 );
        head2.next = new ListNode( 5 );
        head2.next.next = new ListNode( 6 );

        ListNode ret = sol.mergeKLists2( new ListNode[]{ head, head2 } );
        while( ret != null ) {
            System.out.println( ret.val );
            ret = ret.next;
        }
    }

}

