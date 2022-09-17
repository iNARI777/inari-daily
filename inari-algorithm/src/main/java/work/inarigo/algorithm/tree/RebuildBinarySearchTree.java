package work.inarigo.algorithm.tree;

/**
 * 根据后序遍历的结果还原一个二叉搜索树
 * @ref https://www.bilibili.com/video/BV1rT411u7b9?p=2&vd_source=4274bde643620cf4156821cb46fd6b9b
 */
public class RebuildBinarySearchTree {
    public static TreeNode rebuild(int[] input, int left, int right) {
        if (left > right) return null;
        TreeNode root = new TreeNode();
        root.val = input[right];
        if (left == right) return root;

        // 注意只有左子树或只有右子树的情况
        int mid = right - 1;
        if (mid < 0) return null;
        while (input[mid] > input[right]) {
            mid--;
            if (mid < 0) break;
        }

        TreeNode leftSubTree = rebuild(input, left, mid);
        TreeNode rightSubTree = rebuild(input, mid + 1, right -1);
        root.left = leftSubTree;
        root.right = rightSubTree;

        return root;
    }

    public static void main(String[] args) {
        int[] input = {1, 3, 2, 5, 7, 6, 4};
        TreeNode root = rebuild(input, 0, input.length - 1);
        System.out.println(root);
    }
}
