/**
 * Created by vingc on 2017/4/19.
 * ref: https://leetcode.com/problems/reverse-nodes-in-k-group/#/description
 * num: 25

 Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 You may not alter the values in the nodes, only nodes itself may be changed.

 Only constant memory is allowed.

 For example,
 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5

 */
public class ReverseNodesInKGroup {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    static class Solution {
        public ListNode reverseKGroup(ListNode head, int k) {

            if( head == null || k < 2 ) {
                return head;
            }

            ListNode newEnd = head;
            ListNode newHead = head;

            //move to the newHead, and count the number of nodes.
            int i = 0;
            for( i = 0; i < k - 1; i++ ) {
                if( newHead != null && newHead.next != null )
                    newHead = newHead.next;
                else
                    return head; //don't have more than k nodes. do nothing.
            }

            //reverse the first k nodes
            ListNode last = null;
            ListNode cur = null;
            ListNode remain = null;

            last = newHead.next;
            cur = head;
            i = 0;
            while( i < k ) {
                remain = cur.next;
                cur.next = last; //reverse the point of cur node
                last = cur; //store the last node reversed.
                cur = remain; //move forward.
                i++;
            }

            head = newHead;
            newEnd.next = reverseKGroup( newEnd.next, k );
            return head;
        }


        public ListNode reverseKGroup2(ListNode head, int k) {

            if( head == null || k < 2 ) {
                return head;
            }

            ListNode lastEnd = null;//the end nodes of last k nodes
            ListNode begin = head; //begin of k nodes
            ListNode end = head; //end of k nods


            ListNode last = null;
            ListNode cur = null;
            ListNode remain = null;
            int i;

            head = null;
            while( true ) {

                //find the end of k nodes
                for( i = 0; i < k-1; i++ ) {
                    if (end != null && end.next != null) {
                        end = end.next;
                    }
                    else {
                        if( head == null ) {
                            head = begin;
                        }
                        return head; //don't have more than k nodes. do nothing.
                    }
                }

                //if find the first k nodes, change the head point.
                if( head == null ) {
                    head = end;
                }

                //reverse the k nodes
                last = end.next;
                cur = begin;
                i = 0;
                while( i < k ) {
                    remain = cur.next;
                    cur.next = last; //reverse the point of cur node
                    last = cur; //store the last node reversed.
                    cur = remain; //move forward.
                    i++;
                }

                if( lastEnd != null ) {
                    lastEnd.next = end;
                }

                lastEnd = begin;
                begin = remain; //move foreward.
                end = remain;

            }

        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        ListNode head = new ListNode( 1 );
        head.next = new ListNode( 2 );
        head.next.next = new ListNode( 3 );
        head.next.next.next = new ListNode( 4 );
        head.next.next.next.next = new ListNode( 5 );

        ListNode res = sol.reverseKGroup2( head,2 );
        while( res != null ) {
            System.out.println( res.val );
            res = res.next;
        }
    }
}
