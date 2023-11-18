package MergeLeetcode;

import java.util.Random;

import static com.sun.java.util.jar.pack.ConstantPool.partition;

public class QuickSort {
    //better 双路快排
    private final static Random rand = new Random(System.currentTimeMillis());

    public int[] sortArray(int[] nums) {
        QuickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public void QuickSort(int []nums, int left, int right){
        if(left >= right) return;

        int pivotIndex = partition(nums, left, right);
        QuickSort(nums, left,pivotIndex - 1);
        QuickSort(nums, pivotIndex + 1, right);
    }

    public void quickSort(int[] nums, int left, int right) {
        int randomIndex = left + rand.nextInt(right - left + 1); //左闭右开，为了能够取到最右边的数据，所以需要多加上1
        swap(nums, left, randomIndex);
        int pivot = nums[left];

        int lt = left + 1;// i: [left + 1, i) <= pivot;
        int gt = right; // (j, right] >= pivot
        // [left + 1, lt) < pivot; the middle part: [lt, i) is equal to pivot; (gt, right] > pivot
        // 因为是开区间，所以在gt的位置的时候，在gt位置的数值是等于pivot的，所以在右边的数值——从 gt + 1——是大于pivot的
       int i = left + 1;
        while(i < gt){
            if(nums[i] < pivot){
                swap(nums, i, lt);
                lt++;
                i++;
            }else if(nums[i] > pivot){
                swap(nums, i, gt);
                gt--;
            }else{
                // nums[i] == pivot, do nothing
                i++;
            }
        }
        swap(nums, left, lt - 1); //交换第一个区间的最后一个元素和pivot

        //注意好循环的不变量
        QuickSort(nums, left, lt - 2);//左边的区间
        QuickSort(nums, gt + 1, right);//右边的区间
    }


    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}
