package digui;

public class eightking {
    static int max = 12;
    static int tries = 0;
    static int []arr = new int[max];

    public static void main(String[] args) {
        eightKingSort(0);
        System.out.println(tries);
    }

    public static void eightKingSort(int n){
//        退出的条件
        if (n == max){
            for (int item:
                 arr) {
                System.out.print(item);
            }
            System.out.println();

        }

        else {
            for (int i = 0; i < max; i++) {
                arr[n] = i;//在这一层做尝试
                if (judge(n)){

                    eightKingSort(n+1); //但是不需要归位
                }
            }

        }

    }

    public static boolean judge(int n){
//        注意这个for的上下限：都是为了防止前面下好的 不能重复
        for (int i = 0; i < n; i++) {
            if (arr[i] == arr[n] || Math.abs(arr[n]-arr[i]) == Math.abs(n-i)){
                tries++; //
                return false;
            }
        }
        return true;
    }
//小结一下 1，八皇后的每次都是先放下棋子，如果不合适 就下一次的for把它覆盖；直到完全能够符合之前的情况。而回溯的原理 也是如果不符合条件就会从前面的for退出 去往底层的for
}
