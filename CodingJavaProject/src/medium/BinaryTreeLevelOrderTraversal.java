package medium;

import utils.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeLevelOrderTraversal {

    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> nextLevel = new ArrayList<>();
        nextLevel.add(root);

        do {

            List<Integer> curLevelValues = new ArrayList<>();
            List<TreeNode> curLevel = nextLevel;
            nextLevel = new ArrayList<>();
            for (TreeNode node: curLevel) {
                curLevelValues.add(node.val);
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            result.add(curLevelValues);
        } while (nextLevel.size() > 0);

        return result;
    }
}
