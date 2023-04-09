package medium;

import utils.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class BuildTreePreorderInorderTraversal {


    /*
        // 一开始不会
        不停分割左右子树 -> 位置是有错位的
        调整位置花了很多时间 -> 最后还是加上了inorder，在运行中计算
     */

    private Map<Integer, Integer> inorderIndexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {

        inorderIndexMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return buildSubTrees(preorder, 0, preorder.length-1, 0, inorder.length-1);
    }

    private TreeNode buildSubTrees(int[] preorder, int left, int right, int inLeft, int inRight) {
        if (left > right) return null;

        int rootValue = preorder[left];
        TreeNode root = new TreeNode(rootValue);
        if (left == right) return root;


        int inorderIndex = inorderIndexMap.get(rootValue);
        int size = inorderIndex - inLeft;
        root.left = buildSubTrees(preorder, left+1, left+size, inLeft, inorderIndex-1);
        root.right = buildSubTrees(preorder, left+size+1, right, inorderIndex+1, right);

        return root;
    }
}
