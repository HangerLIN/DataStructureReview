//package tree;
//
//import javax.swing.text.AbstractDocument;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class HuffmanCode {
//    public static void main(String[] args) {
//     String str = "i like like like java do you like java";
//     byte[] contentByte = str.getBytes();  //getbyte转化成对应的 子节数
//     List<Node> nodes = getNodes(contentByte);
//        System.out.println(nodes);
//
//    }
//
//
////    接受的一个字符串，里面返回的是node的一个权重 加 ascii的值
//    public static List<Node> getNodes(byte[] bytes){
//        ArrayList<Node>nodes = new ArrayList<Node>();
//        Map<Byte,Integer> counts = new HashMap<>();
//        //遍历传入的数组 bytes
//        for (byte b:
//             bytes) {
//            Integer count = counts.get(b);
//            if (count == null) {
//                counts.put(b,1);
//            }else {
//                counts.put(b,b+1);
//            }
////            把每个键值转换成一个Node对象，并且加入到nodes集合
//
////            遍历map 的方法
//            for (Map.Entry<Byte,Integer> entry:
//                 counts.entrySet())
//            {
//                nodes.add(new Node(entry.getKey(),entry.getValue()));
//            }
//        }
//return nodes;
//    }
//}
//
//
//
//class Node implements Comparable<Node>{
//    Byte data;
//    int weight;
//    Node left;
//    Node right;
//
//    public Node(Byte data, int weight) {
//        this.data = data;
//        this.weight = weight;
//    }
//
//    @Override
//    public int compareTo(Node o) {
//        return this.weight-o.weight;
//    }
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "data=" + data +
//                ", weight=" + weight +
//                '}';
//    }
//}