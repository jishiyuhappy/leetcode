package easy;

import utils.TreeNode;

public class IsSymmetricTree {

    public boolean isSymmetric(TreeNode root) {
        return browseDFS(root.left, root.right);
    }

    private boolean browseDFS(TreeNode leftTree, TreeNode rightTree) {

        if (leftTree != null && rightTree != null) {
            if (leftTree.val != rightTree.val) return false;
        } else if (leftTree == null && rightTree == null) {
            return true;
        } else {
            return false;
        }

        if(browseDFS(leftTree.left, rightTree.right) == false) {
            return false;
        }

        if (browseDFS(leftTree.right, rightTree.left) == false) {
            return false;
        }

        /*if (leftTree.left != null) {
            if (rightTree.right != null) {
                if(browseDFS(leftTree.left, rightTree.right) == false) {
                    return false;
                }
            } else {
                return false;
            }
        } else if (rightTree.right != null) {
            return false;
        }

        if (leftTree.right != null) {
            if (rightTree.left != null) {
                if (browseDFS(leftTree.right, rightTree.left) == false) {
                    return false;
                }
            } else {
                return false;
            }
        } else if (rightTree.left != null) {
            return false;
        }*/

        return true;
    }
}
