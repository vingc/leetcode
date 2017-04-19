/**
 * Created by vingc on 2017/4/19.
 * ref: https://leetcode.com/problems/swap-nodes-in-pairs/#/description
 * num: 24

 Given a linked list, swap every two adjacent nodes and return its head.

 For example,
 Given 1->2->3->4, you should return the list as 2->1->4->3.

 Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
 */
public class SwapNodesInPairsOfList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode swapPairs(ListNode head) {

            if( head == null || head.next == null ) {
                return head;
            }

            ListNode res = null;
            ListNode next = null;

            next = head.next.next;  //store the remain of list except the first pair.
            res = head.next; //redirect the head to be the second node.
            res.next = head; //relocation the point of new head node.
            res.next.next = swapPairs( next ); //relocation the point of first node.
            return res;




        }

        public ListNode swapPairs2(ListNode head) {

            if( head == null || head.next == null ) {
                return head;
            }

            ListNode res = null;
            ListNode remain = null;
            ListNode cur = null;

            remain = head;
            res = head;
            while (remain != null && remain.next != null ) {
                cur = remain;
                remain = remain.next.next;
                if( res == head ) {
                    head = cur.next;
                    head.next = cur;
                    res = head.next;
                }
                else {
                    res.next = cur.next; //connect last pair and cur pair.
                    res.next.next = cur;
                    res = res.next.next;
                }

                res.next = remain;
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

        ListNode res = sol.swapPairs2( head );
        while( res != null ) {
            System.out.println( res.val );
            res = res.next;
        }
    }
}
