package medium;

import utils.TreeNode;

public class ValidateBinarySearchTree {

    // [2,2,2] 也不算
    // [2147483647] 是最大值
    // [2147483647,2147483647]
    public boolean isValidBST(TreeNode root) {

        return browserDFS(root, null, null);
    }

    private boolean browserDFS(TreeNode node, TreeNode smallValue, TreeNode bigValue) {

        // cur node
        //if (smallValue >= node.val || bigValue <= node.val) return false;
//        if (smallValue >= node.val && smallValue != Integer.MIN_VALUE) return false;
//        if (bigValue <= node.val && bigValue != Integer.MAX_VALUE) return false;

        if (smallValue != null && smallValue.val >= node.val) return false;
        if (bigValue != null && bigValue.val <= node.val) return false;


        if (node.left != null) {
            if (browserDFS(node.left, smallValue, node) == false) {
                return false;
            }
        }

        if (node.right != null) {
            if (browserDFS(node.right, node, bigValue) == false) {
                return false;
            }
        }

        return true;
    }
}
