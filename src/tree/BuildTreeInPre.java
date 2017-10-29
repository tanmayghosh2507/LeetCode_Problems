package tree;

public class BuildTreeInPre {

	static int preIndex = 0;

	public static void main(String[] args) {
		int[] pre = { 1, 2 };
		int[] in = { 2, 1 };

		// int[] pre = {1, 2, 4, 5, 3, 6};
		// int[] in = {4, 2, 5, 1, 3, 6};

		BuildTreeInPre buildTreeInPre = new BuildTreeInPre();
		TreeNode tr = buildTreeInPre.buildTree(pre, in);
		System.out.println(tr.val);
		System.out.println(tr.right.val);
	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		if (preorder.length == 0) {
			return null;
		}
		return buildTreeRecurse(preorder, inorder, 0, inorder.length - 1);
	}

	public TreeNode buildTreeRecurse(int[] preorder, int[] inorder, int start, int end) {
		if (start > end)
			return null;

		TreeNode startNode = new TreeNode(preorder[preIndex++]);

		if (start == end)
			return startNode;

		int index = 0;

		int data = startNode.val;
		for (int j = start; j < end; j++) {
			if (inorder[j] == data) {
				index = j;
				break;
			}
		}

		startNode.left = buildTreeRecurse(preorder, inorder, start, index - 1);
		startNode.right = buildTreeRecurse(preorder, inorder, index + 1, end);
		return startNode;
	}
}
