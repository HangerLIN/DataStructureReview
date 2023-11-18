package tree;

import java.util.Arrays;

public class HeapSort
{
    public static void main(String[] args) {
//        升序=大；降序=小
        int []arr = {4,6,8,5,9};
        heapsort(arr);
    }

    public static void heapsort(int[] arr){
        System.out.println("堆排序");

//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次"+ Arrays.toString(arr));  第一次直到这种打印模式
//
//        adjustHeap(arr,0,arr.length); //第二次
//
//        一步的操作 ：从最后的一个非叶子节点开始 arr.length/2 - 1一次次减小 往上面替换数据的大小
        for (int i = arr.length/2 - 1; i >= 0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }


        for (int j = arr.length-1; j >0 ; j--) //需要循环arr.length-1次
        {
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;

            adjustHeap(arr,0,j); //把0依次和下面的调换
            //每一轮j减少的原因是因为已经把最大的数字放到了尾部 只需要对前面的数据来排序就可以了
        }

        System.out.println(Arrays.toString(arr));

//        将一个数组，构建成一个大顶堆 == 从左到右 从下到上
    }

    public static void adjustHeap(int arr[],int i, int length){
//        i:表示非叶子节点在数组中的索引
//        length 表示对多少个元素进行调整（做完排序的值，做了形式上的出列）
//        完成：将以i为对应的非叶子节点 调整成大顶堆
        int temp = arr[i];

        for (int j = i*2+1; j < length; j = j*2+1)
        {
            if ( j+1 < length && arr[j]<arr[j+1])
            {
                j++; //说明（移到下面之后）右边的值要比左边大
            }

            //不是else if （这是多个选择进入的连续结）
            if (arr[j] > temp){
                arr[i] = arr[j]; // 当前的位置再次指向arr【j】，内容交换
                i = j; //使得i指向j
            }
            //这个else紧跟这上面
            else {
                break;
            }
        }
        arr[i] = temp;  //最终实现了头节点与最下面的最大节点的交换
}
}
