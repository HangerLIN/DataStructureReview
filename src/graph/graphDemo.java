package graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class graphDemo {
    private ArrayList<String> vertexList;
    private int[][] edge;
    private int numOffEdges;//表示边的数目

    private boolean[] isVisited;

    public static void main(String[] args) {
        //测试一把 图是否创建ok
        int n = 5;
        String[] vertexs = {"A", "B", "C", "D", "E"};
        graphDemo graphDemo = new graphDemo(n);
//       循环的用字符数组 添加节点
        for (String vertex :
                vertexs) {
            graphDemo.insertVertex(vertex);
        }

//        添加边（按照上方的顺序字符数组的下标放入）
//        A-B A-C B-C B-D B-E
        graphDemo.insertEdge(0, 1, 1);
        graphDemo.insertEdge(0, 2, 1);
        graphDemo.insertEdge(1, 2, 1);
        graphDemo.insertEdge(1, 3, 1);
        graphDemo.insertEdge(1, 4, 1);

//        显示矩阵
        graphDemo.showGraph();

        graphDemo.dfs();

        graphDemo.bfs();
    }

    public graphDemo(int n) {
        edge = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOffEdges = 0;
        boolean[] isVisited = new boolean[n];
    }
//    1可以直接连接，0不能直接连接
//    图中常用的方法

    //    1。返回节点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //    2.返回边数目
    public int getNumOfEdge() {
        return numOffEdges;
    }

    //    插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    //    返回下标对应的数据
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    //    添加一条边
//    v1 和 v2 分别表示顶点对应的下标 weight表示权值
    public void insertEdge(int v1, int v2, int weight) {
        edge[v1][v2] = weight;
        edge[v2][v1] = weight;
        numOffEdges++;
    }

    //    获得图对应的矩阵
    public void showGraph() {
        for (int[] link :
                edge) {
            System.err.println(Arrays.toString(link)); //按照行输出一行数据
        }
    }

    //    返回各自的权值
    public int getWeight(int v1, int v2) {
        return edge[v1][v2];
    }

    //    两个遍历的获取的前方法
//    1.同一边上 是否有存在
    public int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edge[index][i] > 0) {
                return i;
            }
        }
        return -1;
//        tips:只要return 就直接退出程序 所以可以在循环中的多个位置
    }

    //    通过前置的那个来获取下一个
//    v1：行 v2：列的
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            if (edge[v1][i] > 0) {
                return i;
            }

        }
        return -1;
    }

    //    以下是深度优先
//    单个方法
    public void dfs(boolean[] isVisited, int i) {
        System.out.print(getValueByIndex(i) + "=>");
//        把这个节点设置成已经访问
        isVisited[i] = true;

        int w = getFirstNeighbor(i); //而我感觉这个 getFirstNeighbor的作用不大 因为都需要进入下面的回溯来使用（还是再听听课。。。）虽然前后对的答案都一样
//        如果临界节点存在
        while (w != -1) {
            if (!isVisited[w]) { //如果还没有被访问
                dfs(isVisited, w);
            }
            w = getNextNeighbor(i, w); //直到当-1出现的时候 表明此全一行的都已经被尝试过了 而且都没有能够去连接的
        }
    }

    public void dfs() {
        isVisited = new boolean[vertexList.size()]; //
        // 遍历所有的结点，进行 dfs[回溯]
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
//            实际上 貌似只有第一行有用
                dfs(isVisited, i);
            }
        }
    }


    //    以下是广度优先的方法:预先将A加入队列，循环队列不为空，弹出A，查看A的邻接节点，将A的所有邻接节点加入队列尾部，然后弹出队列首部B,找B的所有邻接节点加入到队列尾部
    public void bfs(boolean[] isVisit, int i) {
        int u; //队列头节点的下标
        int w; //邻节点w
        LinkedList queue = new LinkedList<>();
        //访问节点 输出节点的顺序
        System.out.print(getValueByIndex(i) + "=>");
//        标志成已经访问
        isVisit[i] = true;
//        节点放入最后 加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            u = (Integer) queue.removeFirst();//取出来的是一个object类型
            w = getFirstNeighbor(u);

            while (w != -1) {
                if (!isVisit[w]) {
                    System.out.print(getValueByIndex(w) + "=>");
                    isVisit[w] = true;
                    queue.addLast(w);//入队
                }
//                以u为当前节点，找w后面的下一个邻节点 == 这一步体现广度优先
                w = getNextNeighbor(u, w);
            }
        }
    }

    //    遍历所有的节点，都进行广度优先搜索
    public void bfs() {
        isVisited = new boolean[vertexList.size()] ;
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }
}

