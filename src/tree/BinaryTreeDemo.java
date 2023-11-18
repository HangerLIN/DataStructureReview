package tree;

public class BinaryTreeDemo {
    public static void main(String[] args) {
        binaryTree binaryTree = new binaryTree();
        //创建需要的结点
         node root = new node(1, "宋江");   //头节点 不放在🌲的里面
         node node2 = new node(2, "吴用");
         node node3 = new node(3, "卢俊义");
         node node4 = new node(4, "林冲");
         node node5 = new node(5, "关胜");

         root.setLeft(node2);
         root.setRight(node3);
         node3.setRight(node4);
         node3.setLeft(node5);
         binaryTree.setRoot(root);

         //主要明白这个递归的思想：会不断的从满足条件的底层调用，直到输出完叶子
//         前序遍历
         binaryTree.getRoot().preOrder();

//         中序遍历（先看的是左边）
  //      binaryTree.getRoot().infixOrder();
//          后续遍历
        //binaryTree.getRoot().postOrder();
//       node n = binaryTree.getRoot().preOrderSearch(3);
//        System.out.println(n);

//        System.out.println();
//        binaryTree.deleteNode(5); //后面如果是附属的都被删掉
//        binaryTree.getRoot().preOrder();

    }
}
class binaryTree{
    private node root;

    public node getRoot() {
        return root;
    }

    public void setRoot(node root) {
        this.root = root;
    }

//    遍历节点（3类型）
    public void preOrder(){
        if (this.root!= null){
            this.root.preOrder();
        }
        else {
            System.out.println("empty");
        }
    }

    public void infixOrder(){
        if (this.root == null) {this.root.postOrder();
        }
        else {
            System.out.println("empty");
        }
    }

    public void postOrder(){
        if (this.root == null) {this.root.postOrder();
        }
        else {
            System.out.println("empty");
        }
    }

//    查找的代码（前序查找）== 在heronode里面

//    删除节点 ===感觉一定要防止空指针的出现
    public void deleteNode(int no ){
        if(root!=null){
//            判断root是不是就要删除的节点
//            类外可以调用类内的public的成员函数 , 否则就要创建示例化对象
            if (root.getNo() == no){
                root = null;
            }

            else {
                root.delete(no); //调用下面类内
            }
        }
        else {
            System.out.println("树空，不能删除");
        }
    }

}



class node {
    public int no;
    public String name;
//  public null
    public node left;
    public node right;

//    如果是0 表示指向子树 1表示指向前后节点
    private int lefttype;
    private int rightType;

    public node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public node getLeft() {
        return left;
    }

    public void setLeft(node left) {
        this.left = left;
    }

    public node getRight() {
        return right;
    }

    public void setRight(node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

//    前序遍历的方法
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
        //递归向左子树中序遍历
        if (this.left != null){
            this.left.infixOrder();
        }

        System.out.println(this);

        if (this.right != null){
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
//搜索方法
    public node preOrderSearch(int no){
//        比较当前的节点是不是
        if (this.no == no){
            return this;
        }
//        在做左右查找的时候 要先判断是不是为空的节点————否则会 空指针异常
        node res = null;

        if (this.left != null) {
            res = this.left.preOrderSearch(no);
        }

//
        if (res != null) {
            return res;
        }
//        如果前面没有找到 就右递归开始查找
        if (this.right != null) {
            res = this.right.preOrderSearch(no);
        }

       return res;
    }

//    public node infixOrderSearch(int no){
//        node res = null;
//        if (this.left != null){
//
//        }
//    }

//    删除节点 ==和链表一样 不能直接删除
//    五步走  整体的方法和 查找不太一样
    public void delete(int no){
//        注意：&&有短路的性质
        if (this.left != null && this.left.no == no){
            this.left = null;
            return;
            // 本处的return 结束递归 退出all
        }

        if (this.right != null && this.right.no == no){
            this.right = null;
            return;
            // 本处的return 结束递归
        }

//        如果上面的代码都没有删除 开始左右递归删除
        if (this.left != null ){
            this.left.delete(no);
        }

        if (this.right != null) {
            this.right.delete(no);
        }


    }
}
