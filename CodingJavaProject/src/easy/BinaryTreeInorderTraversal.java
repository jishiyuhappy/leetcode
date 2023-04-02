package easy;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeInorderTraversal {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root != null) browserDFS(root, result);
        return result;
    }

    private void browserDFS(TreeNode node, List<Integer> result) {

        if (node.left != null) browserDFS(node.left, result);
        result.add(node.val);
        if (node.right != null) browserDFS(node.right, result);
    }
}
