package BinarySearchTree;

public class BinarySortTree {
    public static void main(String[] args) {
        int[] arr = {50,72,96,107,26,12,11,9,2,10,25,51,16,17,95};
        BinaryTree binaryTree = new BinaryTree();
//        循环添加
        for (int i = 0; i < arr.length; i++) {
            binaryTree.add(new Node(arr[i]));
        }

//        排序前 (用二叉排序树使用的是中序排列
        System.out.println("pre");
        binaryTree.preOrder();

        System.out.println("中序");
        binaryTree.infixOrder();

        System.out.println("后续");
        binaryTree.postOrder();

    }
}

class BinaryTree{
    private Node root;
    public void add(Node node){
        if (root == null) {
            root = node;
        }else {
            root.add(node);
        }
    }

    public void infixOrder(){
        if (root == null) {
            System.out.println("空树");
        }else {
            root.infixOrder();
        }
    }

    public void preOrder(){
        if (root == null) {
            System.out.println("空树");
        }else {
            root.preOrder();
        }
    }

    public void postOrder(){
        if (this.root != null) {
            this.root.postOrder();
        }
        else {
            System.out.println("empty");
        }
    }
}

class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
    
//    添加节点 == 递归
    public void add(Node node){
        if (node == null) {
            return;
        }

//        判断传入节点的值和当前子树的根节点的关系
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node; //要注意是空还是非空 不然就覆盖了原来的那个节点了
            }
            else {
                this.left.add(node); //还是在左边继续添加
            }
        }

        else {
            if (this.right == null) {
                this.right = node;
            }
            else {
                this.right.add(node);
            }
        }
    }

    public void searchNum(int num){
        if (this.value == num) {
            System.out.println("find");
        } else if (this.value < num) {
            this.left.searchNum(num);
        }
        else {
            this.right.searchNum(num);
        }
    }

    public void preOrder(){
        System.out.println(this);  //先输出父节点
//        递归向左边的子树前序遍历
        if (this.left != null){
            this.left.preOrder();
        }
        if (this.right != null){
            this.right.preOrder();
        }
    }

    public void infixOrder(){
//        中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public void postOrder(){

        if (this.left != null){
            this.left.postOrder();
        }
        if (this.right != null){
            this.right.postOrder();
        }

        System.out.println(this);

    }
}

