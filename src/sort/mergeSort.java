package sort;

public class mergeSort {
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 6, 3};
        int[] temp = new int[arr.length];
        MergeSort(arr, 0, arr.length - 1, temp);
        for (int item :
                arr) {
            System.out.print(item);
        }
    }

    public static int[] MergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            MergeSort(arr, left, mid, temp);
            MergeSort(arr, mid + 1, right, temp);
            Merge(arr, left, mid, right, temp);
        }
        return arr;
    }

    public static void Merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
    }
}
