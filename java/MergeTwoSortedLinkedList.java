/**
 * Created by vingc on 2017/4/17.
 * ref: https://leetcode.com/problems/merge-two-sorted-lists/#/description
 * num: 21
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.
 */
public class MergeTwoSortedLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }


    static class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

            if( l2 == null ) {
                return l1;
            }

            if( l1 == null ) {
                return l2;
            }

            ListNode ret;
            ListNode last = null;
            if( l1.val <= l2.val ) {
                ret = new ListNode( l1.val );
                last = ret;
                l1 = l1.next;
            }
            else {
                ret = new ListNode( l2.val );
                last = ret;
                l2 = l2.next;
            }

            while( l1 != null && l2 != null ) {
                if( l1.val <= l2.val ) {
                    last.next = new ListNode( l1.val );
                    last = last.next;
                    l1 = l1.next;
                }
                else {
                    last.next = new ListNode( l2.val );
                    last = last.next;
                    l2 = l2.next;
                }
            }

            while( l1 != null ) {
                last.next = new ListNode( l1.val );
                last = last.next;
                l1 = l1.next;
            }

            while( l2 != null ) {
                last.next = new ListNode( l2.val );
                last = last.next;
                l2 = l2.next;
            }

            return ret;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        ListNode l1 = new ListNode( 1 );
        l1.next = new ListNode( 3 );
        ListNode l2 = new ListNode( 2 );
        l2.next = new ListNode( 4 );
        l2.next.next = new ListNode( 5 );

        ListNode l3 = sol.mergeTwoLists( null, l2 );
        while( l3 != null ) {
            System.out.print( l3.val + "->" );
            l3 = l3.next;
        }
    }
}
