package TreeLeetcode;

import java.util.*;
import java.util.stream.IntStream;

public class TreeDemo {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            result.add(level);
        }
        return result;
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            List<Integer> level = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                level.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            result.add(level);
        }
        Collections.reverse(result);
        return result;
    }

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                if (i == size - 1) result.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return result;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++){
                TreeNode cur = queue.poll();
                sum += cur.val;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            result.add(sum / size);
        }
        return result;
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                max = Math.max(max, cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            result.add(max);
        }
        return result;
    }

//    public Node connect(Node root) {
//        if (root == null) return null;
//        Queue<Node> queue = new LinkedList<>();
//        queue.add(root);
//        while (!queue.isEmpty()) {
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                Node node_a = queue.poll();
//                if (i < size - 1) {
//                    Node node_b = queue.peek();
//                    node_a.next = node_b;
//                }
//                if (node_a.left != null) queue.add(node_a.left);
//                if (node_a.right != null) queue.add(node_a.right);
//            }
//        }
//        return root;
//    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null && cur.right == null) return count;
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
        }
        return count;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null) return maxDepth(root.right) + 1;
        else if (root.right == null) return maxDepth(root.left) + 1;
        else return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }

    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    public boolean isMirror(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return isMirror(left.left, right.right) && isMirror(left.right, right.left);
    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;

        int leftVal = sumOfLeftLeaves(root.left);
        int rightVal = sumOfLeftLeaves(root.right);

        int temp = 0;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            temp = root.left.val;
        }
        return temp + leftVal + rightVal;
    }

    int maxDepth = 0;
    int result = Integer.MIN_VALUE;

    public int findBottomLeftValue(TreeNode root) {
        if (root == null) return 0;
        helper(root, 0);
        return result;
    }

    public void helper(TreeNode root, int depth) {
        if (root.left
                == null && root.right == null) {
            if (depth > maxDepth) {
                maxDepth = depth;
                result = root.val;
            }
        }
        if (root.left != null) {
            helper(root.left, depth + 1);
        }
        if (root.right != null) {
            helper(root.right, depth + 1);
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        pathSumHelper(root, sum, path, result);
        return result;
    }

    private void pathSumHelper(TreeNode root, int sum, List<Integer> path, List<List<Integer>> result) {
        if (root == null) return;
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            result.add(new ArrayList<>(path));
        } else {
            pathSumHelper(root.left, sum - root.val, path, result);
            pathSumHelper(root.right, sum - root.val, path, result);
        }
        path.remove(path.size() - 1);
    }

    void traverse(ListNode head){
//        倒序打印单链表
        if(head == null) return;
        traverse(head.next);
        System.out.println(head.val);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        //获取最后的结果：后序遍历
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //本来的题目使用的：正常的后序遍历，实际上前序遍历也可以（而不是使用层序遍历->空间复杂度大）
    Node connect(Node root) {
        if (root == null) return null;
        traverse(root.left, root.right);
        return root;
    }
    //
    void traverse(Node node1, Node node2) {
        if (node1 == null || node2 == null) return;

        node1.next = node2;

        traverse(node1.left, node1.right);
        traverse(node1.right, node2.left);
        traverse(node2.left, node2.right);
    }

    //给你二叉树的根结点 root ，请你将它展开为一个单链表
    //展开后的单链表应该同样使用 TreeNode ，其中 right子指针指向链表中下一个结点，而左子指针始终为 null 。
    //展开后的单链表应该与二叉树,前序遍历,顺序相同。

    //public List<TreeNode> list = new LinkedList<>();
//    public void flatten(TreeNode root) {
//        LinkedList<TreeNode> list = new LinkedList<>();
//        make_list(root, list);
//    }
//    void make_list(TreeNode root, LinkedList<TreeNode> list) {
//        if (root == null) return;
//
//        list.add(root);
//
//        make_list(root.left, list);
//        make_list(root.right, list);
//    }

    public TreeNode node = new TreeNode(-1);

    TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0) return null;
        int max_num = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[max_num]) {
                max_num = i;
            }
        }
        int max = nums[max_num];
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, max_num));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, max_num + 1, nums.length));
        return root;
    }

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        if (preorder.length == 0 && postorder.length == 0) return null;
        if (preorder.length == 1 && postorder.length == 1) return new TreeNode(preorder[0]);
        TreeNode root = new TreeNode(preorder[0]);
        int index = 0;
        for (int i = 0; i < postorder.length; i++) {
            if (postorder[i] == preorder[1]) {
                index = i;
                break;
            }
        }
        root.left = constructFromPrePost(Arrays.copyOfRange(preorder, 1, index + 2), Arrays.copyOfRange(postorder, 0, index + 1));
        root.right = constructFromPrePost(Arrays.copyOfRange(preorder, index + 2, preorder.length), Arrays.copyOfRange(postorder, index + 1, postorder.length - 1));
        return root;
        //由于这次需要的内容还是发生了改变：这次改变的是传输的数值数组的上限or下限
    }
    // 记录所有子树以及出现的次数
    HashMap<String, Integer> memo = new HashMap<>();
    // 记录重复的子树根节点
    LinkedList<TreeNode> res = new LinkedList<>();

    /* 主函数 */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        traverse(root);
        return res;
    }

    String traverse(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = traverse(root.left);
        String right = traverse(root.right);
        System.out.println( left + "," + right + "," + root.val);
        String subTree = left + "," + right + "," + root.val;

        int freq = memo.getOrDefault(subTree, 0);
        // 多次重复也只会被加入结果集一次
        if (freq == 1) {
            res.add(root);
        }
        // 给子树对应的出现次数加一
        memo.put(subTree, freq + 1);
        return subTree;
    }

        private static int []temp;
        public static void Sort(int[] arr) {
            temp = new int[arr.length];
            Sort(arr, 0, arr.length - 1);
        }

        private static void Sort(int[] arr, int left, int right) {
            if (left >= right) return; //大于或者等于都可以推出
            int mid = left + (right - left) / 2;
            Sort(arr, left, mid);
            Sort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }

        private static void merge(int[] arr, int left, int mid, int right) {
            int i = left, j = mid + 1, k = 0; //k是temp的下标
            while (i <= mid && j <= right) {
                if (arr[i] <= arr[j]) {
                    temp[k++] = arr[i++];
                } else {
                    temp[k++] = arr[j++];
                }
            }
            while (i <= mid) {
                temp[k++] = arr[i++];
            }
            while (j <= right) {
                temp[k++] = arr[j++];
            }
            if (k >= 0) System.arraycopy(temp, 0, arr, left, k);
        }
/*
    public void flatten(TreeNode root) {
        //先递归到树的最底部
        if (root == null) return;
        makeList(root);
    }

    public TreeNode makeList(TreeNode root){
        if (root == null) return null;

        if (root.right == null && root.left == null) return root;

        TreeNode temp = root.right;
        root.right = makeList(root.left);
        root.right.right = temp;
        root.right.left = null;
        root.left = null;
        return root;
    }
*/

    public void flatten(TreeNode root) {
        if (root == null) return;
        makeList(root);
    }

    public TreeNode makeList(TreeNode root) {
        if (root == null) return null;

        TreeNode leftLastNode = makeList(root.left);
        TreeNode rightLastNode = makeList(root.right);

        if (leftLastNode != null) {
            leftLastNode.right = root.right;
            root.right = root.left;
            root.left = null;
        }

        if (rightLastNode != null) {

            return rightLastNode;
        } else if (leftLastNode != null) {
            return leftLastNode;
        } else {
            return root;
        }
    }


    public static void main(String[] args) {
        // You can add test cases here to test the methods implemented above.

    }
}
