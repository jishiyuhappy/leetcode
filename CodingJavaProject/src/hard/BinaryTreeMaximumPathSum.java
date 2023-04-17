package hard;

import utils.TreeNode;

public class BinaryTreeMaximumPathSum {

    // 如何去理解递归的思路

    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {

        maxGain(root);
        return maxSum;
    }

    private int maxGain(TreeNode node) {

        if (node == null) return 0;

        int leftGain = Math.max(maxGain(node.left), 0); // 0 意味着不加上子节点，因为是负的
        int rightGain = Math.max(maxGain(node.right), 0);

        int curGain = node.val + leftGain + rightGain;
        maxSum = Math.max(maxSum, curGain);

        return node.val + Math.max(leftGain, rightGain);
    }
}
