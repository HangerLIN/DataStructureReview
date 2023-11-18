package search;

public class binarySearch {
    static int[] arr = {1, 8, 10, 89, 1000, 1234,1500};

    public static void main(String[] args) {

        System.out.println(binaryS(0,arr.length-1,10));

        System.out.println(binaryS2(0,arr.length,10));
    }

    public static int binaryS(int left, int right, int val) {
//        都说了 前提必须是有序数组
//第一类：左闭右闭
        if (left > right){
            return -1;
        }

        int mid = (right + left) / 2;

        if (val < arr[mid]) {
            return binaryS(left, mid - 1, val);
        }

        else if (val>arr[mid]){
            return binaryS(mid + 1, right, val);
        }
        else {
            return mid;
        }
    }

    public static int binaryS2(int left, int right, int val) {
//        都说了 前提必须是有序数组
//第一二类：左闭右开
        if (left >= right){
            return -1;
        }

        int mid = (right + left) / 2;

        if (val < arr[mid]) {
            return binaryS(left, mid, val);
        }

        else if (val>arr[mid]){
            return binaryS(mid + 1, right, val);
        }
        else {
            return mid;
        }
    }
}



