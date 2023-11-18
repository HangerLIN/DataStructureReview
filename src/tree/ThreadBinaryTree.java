
// 主类 ThreadBinaryTree
public class ThreadBinaryTree {

    public static void main(String[] args) {
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        BinaryTree binaryTree = new BinaryTree(root);

        root.left = node2;
        root.right = node3;
        node2.left = node4;
        node2.right = node5;

        binaryTree.threadNodes(root);

        System.out.println("前序遍历");
//        binaryTree.preOrder();
        binaryTree.threadList();
    }



// BinaryTree 类
static class BinaryTree {
    private HeroNode root;
    public HeroNode pre = null;

    public BinaryTree(HeroNode root) {
        this.root = root;
    }

    public void threadNodes(HeroNode node) {
        if (node == null) {
            return;
        }

        threadNodes(node.left);

        if (node.left == null) { //这里是操作的node.left，node.left == null 说明是叶子节点，需要线索化
            node.left = pre;
            node.leftType = 1;
        }

        if (pre != null && pre.right == null) { //这里是操作的pre.right；pre.right == null 说明是叶子节点，需要线索化
            pre.right = node;
            pre.rightType = 1;
        }

        pre = node;

        threadNodes(node.right);
    }

    /**
     * 遍历线索化二叉树
     */
    public void threadList() {
        HeroNode node = root;
        while (node != null) {
            //后面随着遍历而变化，因为当leftType==1时，说明该节点是按照线索化
            //处理后的有效节点
            while (node.leftType == 0) {
                node = node.left;
            }

            //打印当前这个节点
            System.out.println(node);

            //如果当前节点的右指针指向的是后继节点，就一直输出
            while (node.rightType == 1) {
                //获取到当前节点的后继节点
                node = node.right;
                System.out.println(node);
            }

            //替换这个遍历的节点，实现遍历
            //如果当前节点的右指针指向的是子树，就替换成右子树
            node = node.right;
        }
    }

    public void deleteNode(int no) {
        if (root == null) {
            System.out.println("空树，无法删除");
            return;
        }

        // 如果只有一个 root 节点, 这里判断 root 是不是就是要删除节点
        if (root.no == no) {
            root = null;
            return;
        }

        // 使用递归删除
        root.delete(no);
    }



    public void preOrder(HeroNode root) {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("empty");
        }
    }
    }
}

    // HeroNode 类
    class HeroNode {
        public int no;
        public String name;
        public HeroNode left;
        public HeroNode right;
        public int leftType;
        public int rightType;

        public HeroNode(int no, String name) {
            this.no = no;
            this.name = name;
        }

        public void preOrder() {
            System.out.println(this);
            if (this.left != null) {
                this.left.preOrder();
            }
            if (this.right != null) {
                this.right.preOrder();
            }
        }

        //    删除节点 ==和链表一样 不能直接删除
//    五步走  整体的方法和 查找不太一样
        public void delete(int no) {
//        注意：&&有短路的性质
            if (this.left != null && this.left.no == no) {
                this.left = null;
                return;
                // 本处的return 结束递归 退出all
            }

            if (this.right != null && this.right.no == no) {
                this.right = null;
                return;
                // 本处的return 结束递归
            }

//        如果上面的代码都没有删除 开始左右递归删除
            if (this.left != null) {
                this.left.delete(no);
            }

            if (this.right != null) {
                this.right.delete(no);
            }
        }

        // HeroNode 类中的删除方法
        public void delete(int no) {
            // 如果当前节点的左子节点不为空，并且左子节点就是要删除的节点
            if (this.left != null && this.left.no == no) {
                this.left = null;
                return;
            }

            // 如果当前节点的右子节点不为空，并且右子节点就是要删除的节点
            if (this.right != null && this.right.no == no) {
                this.right = null;
                return;
            }

            // 向左子树进行递归删除
            if (this.left != null) {
                this.left.delete(no);
            }

            // 向右子树进行递归删除
            if (this.right != null) {
                this.right.delete(no);
            }
        }


        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
