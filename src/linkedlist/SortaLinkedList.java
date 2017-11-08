package linkedlist;

public class SortaLinkedList {

	public static void main(String[] args) {
		ListNode l0 = new ListNode(1);
		// ListNode l1 = new ListNode(5);
		// ListNode l2 = new ListNode(3);
		// ListNode l3 = new ListNode(2);
		// ListNode l4 = new ListNode(4);
		// l0.next = l1;
		// l1.next = l2;
		// l2.next = l3;
		// l3.next = l4;
		l0.next = null;
		ListNode res = sortList(l0);
		while (res != null) {
			System.out.println(res.val);
			res = res.next;
		}
	}

	public static ListNode sortList(ListNode head) {
		if (head == null || head.next == null)
			return head;

		ListNode middle = getMiddle(head);
		ListNode right = sortList(middle.next);
		middle.next = null;
		ListNode left = sortList(head);

		return merge(left, right);
	}

	private static ListNode merge(ListNode left, ListNode right) {
		ListNode result = null;
		if (left == null)
			return right;
		if (right == null)
			return left;
		if (left.val >= right.val) {
			result = right;
			result.next = merge(left, right.next);
		} else {
			result = left;
			result.next = merge(left.next, right);
		}
		return result;
	}

	private static ListNode getMiddle(ListNode node) {
		if (node == null)
			return node;
		ListNode slowptr = node;
		ListNode fastptr = node.next;
		while (fastptr != null) {
			fastptr = fastptr.next;
			if (fastptr != null) {
				fastptr = fastptr.next;
				slowptr = slowptr.next;
			}
		}
		return slowptr;
	}

}
