package medium;

import utils.TreeNode;

public class FlattenBinaryTreeToLinkedList {

    public void flatten(TreeNode root) {
        dfsPreOrder(root);
    }

    private TreeNode dfsPreOrder(TreeNode root) {

        if (root == null) return null;

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;

        TreeNode curNode = root;
        curNode.left = null;
        curNode.right = null;

        if (leftNode != null) {
            curNode.right = leftNode;
            curNode = dfsPreOrder(leftNode);
        }

        if (rightNode != null) {
            curNode.right = rightNode;
            curNode = dfsPreOrder(rightNode);
        }

        return curNode;
    }
}
