package digui;
public class arrayNum {
    static int digits = 3;
    static int num = 0;
    static int []arr = new int[100];
    static int []book = new int[100];
    public static void main(String[] args) {

        dfs(1,digits);
        System.out.println();
    }

    public static void dfs(int step,int digits){
//        退出的条件
        if (step==digits+1){
            for (int i = 1; i <= digits; i++) {
                System.out.print(arr[i]);
                num++;
            }
            System.out.println();
            return;
        }
       //用来表示手上
        //测试五位数的全排列
        for (int i = 1; i <= digits; i++) {
            if(book[i]==0) {
                arr[step] = i;
                book[i] = 1;
                dfs(step + 1, digits); //但是为什么是在里面调用
                book[i] = 0;
            }
        }
       return;
    }
//
}
