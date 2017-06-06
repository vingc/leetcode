/**
 * Created by vingc on 2017/6/6.
 * ref: https://leetcode.com/problems/rotate-list/#/description
 * num: 61
 Given a list, rotate the list to the right by k places, where k is non-negative.

 For example:
 Given 1->2->3->4->5->NULL and k = 2,
 return 4->5->1->2->3->NULL.
 */
public class RotateList {
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
         ListNode(int x) { val = x; }
     }


    static class Solution {
        public ListNode rotateRight(ListNode head, int k) {
            ListNode first = head;
            ListNode second = head;

            if( head == null || k == 0 ) {
                return head;
            }

            int number = 0;
            while( first != null ) {
                first = first.next;
                number++;
            }

            //let the first skip k node,move first
            int cnt = k % number;
            if( cnt == 0 ) {
                return head;
            }
            else {
                first = head;

                while( cnt > 0 ) {
                    first = first.next;
                    cnt--;
                }
            }


            //then ,move first and second at the same time, when first reach
            //the end, the second reach the previous node of kth from end.
            while( first.next != null ) {
                first = first.next;
                second = second.next;
            }

            ListNode newHead = second.next;
            second.next = null;
            first.next = head;

            return newHead;
        }
    }

    public static void main( String[] args ) {
        Solution sol = new Solution();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode ret = sol.rotateRight( head, 4 );
        while( ret != null ) {
            System.out.print( ret.val + "," );
            ret = ret.next;
        }
    }
}
