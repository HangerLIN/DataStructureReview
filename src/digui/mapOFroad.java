package digui;

public class mapOFroad {

        public static void main(String[] args)
        {
            int[][] map = new int[8][7];
// 使用 1 表示墙
// 上下全部置为 1
            for (int i = 0; i < 7; i++) {
                map[0][i] = 1;
                map[7][i] = 1; }
// 左右全部置为 1
            for (int i = 0; i < 8; i++) {
                map[i][0] = 1;
                map[i][6] = 1;
            }
//设置挡板, 1 表示
            map[3][1] = 1; map[3][2] = 1;
// map[1][2] = 1;
// map[2][2] = 1;
//输出地图
            System.out.println("地图的情况");
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 7; j++)
                {
                    System.out.print(map[i][j] + " ");
                }
                System.out.println();
            }

            //定义一个方向数组
        }
    }

