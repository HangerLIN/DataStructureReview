//package tree;
//
//public class ClueBinary {
//}
//
//class BinaryTree{
//    private HeroNode root;
//
//    public HeroNode getRoot(){
//        return root;
//    }
//
//    public void threadNodes(HeroNode node){
//        if (node == null) {
//            return;
//            //不能线索化 直接返回
//        }
////        前序化 线索化
//
//
//    }
//}
//
//class Node{
//    public int no;
//    private String name;
//    //    分别默认是null
//    private HeroNode left;
//    private HeroNode right;
//
//    //    如果是0 表示指向子树 1表示指向前后节点
//    private int lefttype;
//    private int rightType;
//
//    public Node(int no, String name) {
//        super();
//        this.no = no;
//        this.name = name;
//    }
//
//    public int getNo() {
//        return no;
//    }
//
//    public void setNo(int no) {
//        this.no = no;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public HeroNode getLeft() {
//        return left;
//    }
//
//    public void setLeft(HeroNode left) {
//        this.left = left;
//    }
//
//    public HeroNode getRight() {
//        return right;
//    }
//
//    public void setRight(HeroNode right) {
//        this.right = right;
//    }
//
//    @Override
//    public String toString() {
//        return "HeroNode{" +
//                "no=" + no +
//                ", name='" + name + '\'' +
//                '}';
//    }
//
//    //    前序遍历的方法
//    public void preOrder(){
//        System.out.println(this);  //先输出父节点
//
////        递归向左边的子树前序遍历
//        if (this.left != null){
//            this.left.preOrder();
//        }
//        if (this.right != null){
//            this.right.preOrder();
//        }
//    }
//
//    public void infixOrder(){
//        //递归向左子树中序遍历
//        if (this.left != null){
//            this.left.infixOrder();
//        }
//
//        System.out.println(this);
//
//        if (this.right != null){
//            this.right.infixOrder();
//        }
//
//    }
//
//    public void postOrder(){
//
//        if (this.left != null){
//            this.left.postOrder();
//        }
//        if (this.right != null){
//            this.right.postOrder();
//        }
//
//        System.out.println(this);
//
//    }
//
//    //搜索方法
//    public HeroNode preOrderSearch(int no){
////        比较当前的节点是不是
//        if (this.no == no){
//            return this;
//        }
//
////        在做左右查找的时候 要先判断是不是为空的节点————否则会 空指针异常
//        HeroNode res = null;
//
//        if (this.left != null) {
//            res = this.left.preOrderSearch(no);
//        }
//
//        if (res != null) {
//            return res;
//        }
//
////        如果前面没有找到 就右递归开始查找
//        if (this.right != null) {
//            res = this.right.preOrderSearch(no);
//        }
//        return res;
//    }
//
////    public HeroNode infixOrderSearch(int no){
////        HeroNode res = null;
////        if (this.left != null){
////
////        }
////    }
//
//    //    删除节点 ==和链表一样 不能直接删除
////    五步走  整体的方法和 查找不太一样
//    public void delete(int no){
////        注意：&&有短路的性质
//        if (this.left != null && this.left.getNo() == no){
//            this.left = null;
//            return;
//            // 本处的return 结束递归 退出all
//        }
//
//        if (this.right != null && this.right.getNo() == no){
//            this.right = null;
//            return;
//            // 本处的return 结束递归
//        }
//
////        如果上面的代码都没有删除 开始左右递归删除
//        if (this.left != null ){
//            this.left.delete(no);
//        }
//
//        if (this.right != null) {
//            this.right.delete(no);
//        }
//
//
//    }
//}