package LinkedList;

public class Add2Numbers {

	public static void main(String[] args) {
		ListNode l10 = new ListNode(2);
		ListNode l11 = new ListNode(4);
		ListNode l12 = new ListNode(3);
		l10.next = l11;
		l11.next = l12;
		ListNode l20 = new ListNode(5);
		ListNode l21 = new ListNode(6);
		ListNode l22 = new ListNode(4);
		l20.next = l21;
		l21.next = l22;
		
		Add2Numbers add2Numbers = new Add2Numbers();
		ListNode l = add2Numbers.addTwoNumbers(l10, l20);
		while(l != null) {
			System.out.print(l.val+ " ");
			l=l.next;
		}
	}
	
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
		ListNode dummyHead = new ListNode(0);
	    ListNode curr = dummyHead;
	    int carry = 0;
	    while (l1 != null || l2 != null) {
	        int x = (l1 != null) ? l1.val : 0;
	        int y = (l2 != null) ? l2.val : 0;
	        int sum = carry + x + y;
	        carry = sum / 10;
	        curr.next = new ListNode(sum % 10);
	        curr = curr.next;
	        if (l1 != null) l1 = l1.next;
	        if (l2 != null) l2 = l2.next;
	    }
	    if (carry > 0) {
	        curr.next = new ListNode(carry);
	    }
	    return dummyHead.next;
    }

}
