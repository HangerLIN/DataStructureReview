package Recursion;

public class Climb_stairs {
    private int count = 0;
    private int result = 0;
    //其实就是回溯算法
        public int climbStairs(int n) {
            //前序和后序都是可以的
            climb(0,n);
           return count;
        }

        public void climb(int count, int n) {
            if (count == n) {
                result++;
                return;
            }

            if (count < n) {
                climb(count + 1, n);
                climb(count + 2, n);
            }
        }
}
