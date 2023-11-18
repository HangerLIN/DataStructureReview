package Dynamic;

public class bag01 {
    public static void main(String[] args) {
        //set up two array, the weigh and value of each bag is 1/1500,4/3000,3/2000
        int[] w = {1, 4, 3};
        int[] val = {1500, 3000, 2000};
        int m = 4;
        int n = val.length;

        //create a two-dimensional array, in which store the max value
        int[][] v = new int[n + 1][m + 1];
        //初始化第一行和第一列列为0, because in fact the first column is two-dimensional array's index
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0; //set the first column to 0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;//set the first row to 0
        }

        //start for dynamic programming
        for(int i = 1; i < v.length; i++){
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j){
                    v[i][j] = v[i-1][j];
                }else {
                    v[i][j] = Math.max(v[i-1][j],val[i-1] + v[i-1][j-w[i-1]]);
                    //实际上，我们可以直接记住老韩的那个图片是怎么来的，是怎么从表格的右下角找到第一列第二行的位置
                    //此外，还需要物体的下标考虑，是从 0 开始的还是匹配生活，从1开始的下标
                }
            }
        }
        //print the two-dimensional array
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //print out the situation that what kind of element in the bag
        //
        //这个状态转移方程是动态规划算法的核心，它将原问题分解成了子问题，并使用一个二维数组来保存子问题的解，从而避免了重复计算。
        //
        //因此，在背包问题中，动态规划算法的核心并不是选取最右侧的物品，
        // 而是根据状态转移方程依次填充二维数组v，找到前i个物品放入容量为j的背包中的最大价值。
        // 最终，根据数组v[n][C]得到的最大价值，可以输出所选物品的编号以及它们的总价值。
        //老韩说：实际上，判断哪一行使用并且尝试成功了最新的加入情况，就把一个状态数组path设置为1；最后使用这行代码打印出来
        int i = v.length - 1;
        int j = v[0].length - 1;
        while (i > 0 && j > 0){
            if (v[i][j] != v[i-1][j]){
                System.out.printf("the element in the bag is %d\n", i);
                j -= w[i-1];
            }
            i -= 1;
        }
        //v[i][j] = max(v[i-1][j], val[i]+v[i-1][j-w[i]])


    }
}
