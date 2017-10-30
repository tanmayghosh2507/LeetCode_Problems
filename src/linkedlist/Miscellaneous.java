package linkedlist;

public class Miscellaneous {

	public static void main(String[] args) {
		ListNode l0 = new ListNode(1);
		// ListNode l1 = new ListNode(2);
		// ListNode l2 = new ListNode(3);
		// ListNode l3 = new ListNode(4);
		// ListNode l4 = new ListNode(5);
		// l0.next = l1;
		// l1.next = l2;
		// l2.next = l3;
		// l3.next = l4;
		l0.next = null;
		Miscellaneous miscellaneous = new Miscellaneous();
		// ListNode l = miscellaneous.reverseBetween(l0, 2, 4);
		ListNode l = miscellaneous.rotateRight(l0, 0);
		while (l != null) {
			System.out.print(l.val + " ");
			l = l.next;
		}
	}

	// Recursive
	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode reversed = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return reversed;
	}

	public ListNode reverseBetween(ListNode head, int m, int n) {
		int i = 1;
		if (head == null)
			return null;
		ListNode dummy = new ListNode(0); // create a dummy node to mark the head of this list
		dummy.next = head;
		ListNode prev = dummy;
		while (i < m) {
			prev = prev.next;
			i++;
		}

		ListNode current = prev.next;
		ListNode then = current.next;
		i = 1;
		while (i <= n - m) {
			current.next = then.next;
			then.next = prev.next;
			prev.next = then;
			then = current.next;
			i++;
		}

		return dummy.next;
	}

	public boolean hasCycle(ListNode head) {
		ListNode p1 = head;
		ListNode p2 = head;

		while (p1 != null && p2 != null && p2.next != null) {
			p1 = p1.next;
			p2 = p2.next.next;
			if (p1 == p2)
				return true;
		}
		return false;
	}

	public ListNode rotateRight(ListNode head, int k) {
		if (head == null || k == 0) {
			return head;
		}
		ListNode slow = head;
		ListNode fast = head;
		int i = 0;
		while (i < k && fast != null) {
			fast = fast.next;
			i++;
		}

		if (fast == null) {
			return rotateRight(head, k % i);
		}
		while (fast.next != null) {
			fast = fast.next;
			slow = slow.next;
		}
		ListNode next = slow.next;
		slow.next = null;
		fast.next = head;
		return next;
	}
}
