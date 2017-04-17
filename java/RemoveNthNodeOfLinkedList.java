/**
 * Created by vingc on 2017/4/17.
 * ref: https://leetcode.com/problems/remove-nth-node-from-end-of-list/#/description
 * num: 19
 * Given a linked list, remove the nth node from the end of list and return its head.
 */

/*
For example,

   Given linked list: 1->2->3->4->5, and n = 2.

   After removing the second node from the end, the linked list becomes 1->2->3->5.
*/

public class RemoveNthNodeOfLinkedList {
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode(int x) { val = x; }
     * }
     */
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {

            ListNode cur, last;
            int cnt = 0;

            //count the total number of nodes in list
            cur = head;
            while( cur != null ) {
                cnt++;
                cur = cur.next;
            }

            if( n > cnt ) {
                return head;
            }

            //find the nth node from the end;
            cur = head;
            last = null;
            int i = 0;
            while( cur != null ) {
                if( i == cnt - n ) {
                    //find the node
                    if( last == null ) {
                        head = cur.next; //remove the first node
                    }
                    else {
                        last.next = cur.next; //remove the cur node;
                    }
                    break;
                }
                else {
                    last = cur;
                    cur = cur.next;
                    i++;
                }
            }

            return head;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        ListNode head = new ListNode( 1 );
        head.next = new ListNode( 2 );
        head.next.next = new ListNode( 3 );
        head.next.next.next = new ListNode( 4 );
        head.next.next.next.next = new ListNode( 5 );

        head = sol.removeNthFromEnd( head, 5 );
        while( head != null ) {
            System.out.println( head.val );
            head = head.next;
        }
    }
}
