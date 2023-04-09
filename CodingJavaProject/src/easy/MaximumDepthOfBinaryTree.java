package easy;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    public int maxDepth9(TreeNode root) {


        if (root == null) return 0;

        List<TreeNode> nextLevels = new ArrayList<>();
        nextLevels.add(root);

        int depth = 0;
        do {
            depth++;

            List<TreeNode> curLevels = nextLevels;
            nextLevels = new ArrayList<>();
            for (TreeNode node: curLevels) {
                if (node.left != null) nextLevels.add(node.left);
                if (node.right != null) nextLevels.add(node.right);
            }
        } while (nextLevels.size() > 0);

        return depth;
    }
}
