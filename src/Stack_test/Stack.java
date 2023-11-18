package Stack_test;

import java.util.ArrayDeque;

public class Stack<T> extends ArrayDeque<T> {
    private T element;

    public static int Priority(String s){
        if (s.equals('+') || s.equals('-')){
            return 0;
        }else if (s.equals('*') || s.equals('/')){
            return 1;
        }else {
            return -1;
        }
    }

    public static int cal(int num1, int num2, String s){
        if (s.equals("+")){
            return num1 + num2;
        } else if (s.equals("-")) {
            return num2-num1;
        }else if (s.equals("*")){
            return num1 * num2;
        }else if (s.equals("/")){
            return num2/num1;
        }else return Integer.MAX_VALUE;
    }

}

