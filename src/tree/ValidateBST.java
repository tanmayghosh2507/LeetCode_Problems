package tree;

public class ValidateBST {

	public static void main(String[] args) {
		TreeNode t = new TreeNode(-2147483648);
//		t.left = new TreeNode(7);
		t.right = new TreeNode(2147483647);
		System.out.println(isValidBST(t));
	}
	
	public static boolean isValidBST(TreeNode root) {
		if(root==null || (root.left==null && root.right==null))
			return true;
		System.out.println(Long.MAX_VALUE);
        return validateBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
	
	public static boolean validateBST(TreeNode node, long min, long max) {
		if(node == null)
			return true;
		
		if(node.val>min && node.val<max && validateBST(node.left, min, node.val) && validateBST(node.right, node.val, max))
			return true;
		else
			return false;
	}

}
