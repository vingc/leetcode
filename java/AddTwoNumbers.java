/**
 * Created by vingc on 2017/3/31.
 * ref: https://leetcode.com/problems/add-two-numbers/#/description
 * num: 2
 */

public class AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        if( l1 == null ) {
            return l2;
        }

        if( l2 == null ) {
            return l1;
        }

        int carry = 0;
        int sum = 0;
        ListNode ret = null;
        ListNode tail = null;

        ListNode tmp1, tmp2;
        tmp1 = l1;
        tmp2 = l2;
        int val1,val2;

        while( tmp1 != null || tmp2 != null ) {

            //retrieve and add
            val1  = ( tmp1 == null ? 0 : tmp1.val );
            val2  = ( tmp2 == null ? 0 : tmp2.val );

            sum   = val1 + val2 + carry;
            carry = sum / 10;
            sum   = sum % 10; //mod

            //store current digit
            if( ret == null )
            {
                ret = new ListNode( sum );
                tail = ret;
            }
            else {
                tail.next = new ListNode( sum );
                tail = tail.next;
            }

            //move forward.
            if( tmp1 != null )
                tmp1 = tmp1.next;
            if( tmp2 != null )
                tmp2 = tmp2.next;
        }

        if( carry != 0 )
        {
            tail.next = new ListNode( carry );
        }

        return ret;
    }


    public static void main( String[] args ) {
        AddTwoNumbers atn = new AddTwoNumbers();
        ListNode l1 = new ListNode( 9 );
        ListNode l2 = new ListNode( 1 );
        l2.next = new ListNode( 8 );

        ListNode ret = atn.addTwoNumbers( l1, l2 );
        while( ret != null ) {
            System.out.print( ret.val + " " );
            ret = ret.next;
        }

    }
}
