package tree;

import java.util.LinkedList;
import java.util.List;

public class LevelOrderTree {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int[][] LevelOrderTree(TreeNode root){
        if (root == null) return null;

        List<List<Integer>> list = new LinkedList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        while (!queue.isEmpty()){
            List<Integer> level = new LinkedList<>();
            int size = queue.size();
            for(int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            list.add(level);
        }
        return list.toArray(new int[list.size()][]);
    }
}
