package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int []arr = {13,7,8,3,29,6,1};
        node2 root = creatHuffmanTree(arr);
        preOrder(root);
//        测试
    }

    public static void preOrder(node2 root){
        if (root != null) {
            root.preOrder();
        }
        else {
            System.out.println("空");
        }
    }



    public static node2 creatHuffmanTree(int []arr) {
        List<node2> arraylist = new ArrayList<node2>();
        for (int value :
                arr) {
            arraylist.add(new node2(value));  //这一步的加入非常巧妙：把构造和存放放在了一起
        }
        while (arraylist.size() > 1) {
//        放入后先排序
            Collections.sort(arraylist); //为什么用collection就可以非晶态
//        System.out.println(arraylist+"node2");

//        取出权值最小的节点 和 权值第二小的
            node2 leftNode = arraylist.get(0); //里面装的是index
            node2 rightNode = arraylist.get(1);

            node2 parent = new node2(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;

//        从里面删除处理过的二叉树 但是原本
            arraylist.remove(leftNode);
            arraylist.remove(rightNode);

            arraylist.add(parent);//放在尾部
        }
        //最后返回树的root节点
//        //虽然list内的数据已经删完了 但是这两步 parent.left = leftNode;
//            parent.right = rightNode; 一样保持了原本每个node的联系 只是把arraylist里面的数据删完了 就当成一个计数器
        return arraylist.get(0);
    }
}



//为了能够让 node对象持续排序 collection集合排序 所以需要comparable接口
class node2 implements Comparable<node2>{
    int value;
    node2 left;
    node2 right;
public void preOrder(){

    System.out.println(this);

    if (this.left != null) {
        this.left.preOrder();
    }

    if (this.right != null) {
        this.right.preOrder();
    }
}
    public node2(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "node2{" +
                "value=" + value +
                '}';
    }

    public int compareTo(node2 o){
        return this.value - o.value;
        //直接写就是从小到大 否则加上符号就是从大到小
    }
}