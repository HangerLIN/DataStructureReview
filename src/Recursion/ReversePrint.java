package Recursion;

public class ReversePrint {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        reversePrint(arr);
    }

    public static void reversePrint(int[] arr){
        reversePrint(arr, 0);
    }

    private static void reversePrint(int[] arr, int index) {
        if (index == arr.length){
            return;
        }
        reversePrint(arr, index + 1);
        System.out.println(arr[index]);
    }
}

