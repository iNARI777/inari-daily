package work.inarigo.algorithm.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历一棵树，栈方法
 * @ref https://leetcode.cn/problems/binary-tree-inorder-traversal/submissions/
 */
public class InOrderTraverse {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        traverse(root, result);
        return result;
    }

    private static void traverse(TreeNode node, List<Integer> result) {
        List<TreeNode> restored = new LinkedList<>();
        addCurAndAllLeft(node, restored);
        while (restored.size() > 0) {
            node = restored.remove(restored.size() - 1);
            result.add(node.val);
            if (node.right != null) {
                addCurAndAllLeft(node.right, restored);
            }
        }
    }

    private static void addCurAndAllLeft(TreeNode node, List<TreeNode> restored) {
        restored.add(node);
        while (node.left != null) {
            // 别忘了
            node = node.left;
            restored.add(node);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.val = 1;
        TreeNode node1 = new TreeNode();
        node1.val = 2;
        root.left = node1;
        TreeNode node2 = new TreeNode();
        node2.val = 3;
        node1.right = node2;

        List<Integer> result = inorderTraversal(root);
        System.out.println(result);
    }
}
