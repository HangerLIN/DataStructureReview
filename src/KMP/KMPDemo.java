package KMP;

import java.util.Arrays;

public class KMPDemo {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        System.out.println("arrays:"+ Arrays.toString(kmpNext("AAB")));
        System.out.println(kmpSearch(str1,str2,kmpNext(str2)));

//        获取到一个字符串的部分匹配值

    }

    public static int kmpSearch(String str1, String str2, int[] next) {
        for (int i = 0, j = 0; i < str1.length(); i++) {
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j==str2.length()){
                return i-j+1; //已经到了最后，找到了结果
            }
        }
        return -1;
    }


    public static int[] kmpNext(String dest) {
//创建一个 next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0; //如果字符串是长度为 1 部分匹配值就是 0
        for (int i = 1, j = 0; i < dest.length(); i++) {
//当 dest.charAt(i) != dest.charAt(j) ，我们需要从 next[j-1]获取新的 j
// 直到我们发现 有 dest.charAt(i) == dest.charAt(j)成立才退出
// 这时 kmp 算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)) {
                j = next[j - 1]; //开始回溯，
            }
//当 dest.charAt(i) == dest.charAt(j) 满足时，部分匹配值就是+1
            if (dest.charAt(i) == dest.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}



