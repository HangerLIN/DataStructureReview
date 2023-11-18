import java.util.*;

public class Main {
    public static int helper(int[] memo, int n){
        if(n == 1 || n == 2){
            return 1;
        }
        if (memo[n] != 0 ) {
            System.out.println("i am return");
            return memo[n];
        }
        System.out.println("i am in");
        memo[n] = helper(memo,n-1)+helper(memo,n-2);
        return memo[n];
    }

    public static int fib(int N){
        if (N == 0) {
            return  0;
        }
        int[] memo = new int[N+1];
        Arrays.fill(memo, 0);
        return helper(memo,N);
    }

    public static void main(String[] args) {
        //output the start time
        //write your code here
        long start = System.currentTimeMillis();
        int n = fib(7);
        System.out.println(n);
        //output the end time
        //write your code here
        long end = System.currentTimeMillis();
        System.out.println("time: " + (end - start));
    }
}
