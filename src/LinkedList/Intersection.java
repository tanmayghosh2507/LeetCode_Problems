package LinkedList;

public class Intersection {

	public static void main(String[] args) {
		
		ListNode l1 = new ListNode(3);
		ListNode l2 = new ListNode(2);
		ListNode l3 = new ListNode(3);
		l2.next = l3;
		Intersection intersection = new Intersection();
		System.out.println(intersection.getIntersectionNode(l1, l2).val);
		
	}
	
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if(headA==null || headB == null)
			return null;
		
		if(headA==headB)
			return headA;
    
		ListNode tempheadA = headA;
		ListNode tempheadB = headB;
		int countA = 0, countB = 0;
		while(tempheadA!=null) {
			tempheadA = tempheadA.next;
			countA++;
		}
		
		while(tempheadB!=null) {
			tempheadB = tempheadB.next;
			countB++;
		}
		
		tempheadA = headA;
		tempheadB = headB;
		
		int diff = 0;
		if(countA > countB) {
			diff = countA - countB;
			for(int i=0; i<diff; i++) {
				tempheadA = tempheadA.next;
			}	
		} else if(countB > countA) {
			diff = countB - countA;
			for(int i=0; i<diff; i++) {
				tempheadB = tempheadB.next;
			}
		}
		
		while(tempheadA!=null && tempheadB!=null) {
			if(tempheadA.val == tempheadB.val) {
				return tempheadA;
			}
			tempheadA = tempheadA.next;
			tempheadB = tempheadB.next;
		}
		return null;
    }
}
