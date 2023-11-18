package sort;

import java.util.Arrays;

public class all {
    public static void main(String[] args) {

        int[] arr = {2, 3, 4, 6,3};
//        Random temper = new Random();
//        int randomIndex = temper.nextInt(arr.length);//获得一个随机数方法
//        QuickSort(arr,0,arr.length-1);
//        ShellSort_plus(arr);
        int[] temp = new int[arr.length];
        MergeSort(arr, 0, arr.length - 1, temp);
//        RadixSort(arr);
//        System.out.println(arr.length);
/*
                InsertSort(arr);
                SelectSort(arr);
*/
//        for (int item :
//                arr) {
//            System.out.print(item);
//        }

        System.out.println(Arrays.toString(MergeSort(arr, 0, arr.length - 1, temp)));
    }

    //选择排序：可以选择正向还是逆向
    public static int[] SelectSort(int []arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++)
            {
                int smallest_index = i;
                if (arr[smallest_index]<arr[j])
                {
                    smallest_index = j;
                }
                int temp = arr[smallest_index];
                arr[smallest_index] = arr[i];
                arr[i]=temp;
            }
        }
        return arr;
    }

    //    插入排序
    public static int[] InsertSort(int []arr){
        for (int i = 0; i < arr.length ; i++)
        {
            for (int j = i; j > 0 ; j--)
            {
                if (arr[j-1]>arr[j])
                {
//一直前后交换
                    int temp = arr[j-1];
                    arr[j-1]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }

    public static int[] ShellSort(int[] arr) {
        //1.按照数组的长度确定初始值
        int h = 0;
        while (h < arr.length / 2) {
            h = 2 * h + 1;
        }

//        根据分组的大小来开始排序
        while (h >= 1) {
//            从0开始往上面走
//            for(int i = 0; i <= h; i++)
            for (int i = h; i < arr.length; i++)//两个逻辑相似:上面的那个是我自己写的 一直到前面的
            {
                for (int j = i; j > 0; j -= h) {
                    if (arr[j - 1] > arr[j]) {
                        int temp = arr[j - 1];
                        arr[j - 1] = arr[j];
                        arr[j] = temp;
                    } else {
                        break;
                    }
                }
            }
            h /= 2;
        }
        return arr;
    }

    //    shell排序的第二个版本 == 其实是我的思路问题：还是只有一个方法！是根据h的长度来分组，然后再每一组里面做插入排序
    public static int[] ShellSort_plus(int[] arr) {
        int h = 0;
        while (h < arr.length / 2) {
            h = 2 * h + 1;
        }
        while (h >= 1) {
            for (int i = 0; i <= arr.length - h-1; i++)
            {
//        第一轮排序：使得按照h的区间来比较
                if (arr[i + h] < arr[i]) {
                    int temp = arr[i + h];
                    arr[i + h] = arr[i];
                    arr[i] = temp;
                }
            }
            h /= 2;
        }
        return arr;
    }

    public  static void QuickSort(int[] arr,int left,int right)
    {
//没有必要去写出right和left两处的值，只需要在需要的时候传递参数就行
//        递归的推出条件：防止死归
        int left_p = left+1;
        int right_p = right;

        if (left_p>right_p){
        return ;
    }

        int temp = arr[left];

        while (left_p<right_p) //这个是前提的运行条件 防止递归过程中出错
        {
//            下面的两个边界不能出现等于，如果是等于将会一直无法更换
            while (right_p>left_p){
                if (arr[right_p]<temp)
                {
                    break;
                }
                right_p--;
            }

            while (left_p<right_p){
                if(arr[left_p]>temp)
                {
                    break;
                }
                left_p++;
            }
            if(left_p<right_p){
                int temp2 = arr[left_p];
                arr[left_p] = arr[right_p];
                arr[right_p] = temp2;
            }
//            重新设置这个初始比较的值 为下一次的循环做准备
        }
        arr[left] = arr[left_p];//使左边数据重新是新的数据
        arr[left_p] = temp;//使得指针处的数据是原来左边的数据

        QuickSort(arr,left,left_p-1);
        //后面的不断递归中的left 已经不再是原本的那个left了 不断的被更新中
        QuickSort(arr,left_p+1,right);
//这里不能去return整个的计算！！！
    }
//    同时也看出来了：如果最差的情况就是要全部挨个换

    public static void swap(int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }
    public static boolean bigger(int a,int b)
    {
        return a>b;
    }

    public static int[] MergeSort(int[] arr,int left,int right,int []temp) {
        if (left + 1 < right) {
            int mid = (right + left) / 2 ;

//        先分解
            MergeSort(arr, left, mid, temp);
            MergeSort(arr, mid + 1, right, temp);

            merge(arr, left, mid, right, temp);
        }
        return arr;
    }

//    合并的算法

    public static void merge(int []arr,int left,int mid, int right,int []temp)
    {
        int i = left;
        int j = mid + 1;
        int newArrayPointer = 0;

        while (i <= mid &&  j<= right){
            if (arr[i]<arr[j]){
                temp[newArrayPointer]= arr[i];
                i++;
            }
            else {
                temp[newArrayPointer]= arr[j];
                j++;
            }
            newArrayPointer++; //我觉得不需要对new做限制 因为它一定不会越界
        }

        while (i<=mid){
            temp[newArrayPointer]= arr[i];
            i++;
            newArrayPointer++;
        }

        while (j<=right){
            temp[newArrayPointer]= arr[j];
            j++;
            newArrayPointer++;
        }

//        全部迁移到原来的数组(x)
//        for (int k = 0; k < arr.length; k++) {
//            arr[k] = temp[k];
//        }

//        每次只会对确定范围的数据重新拷贝==具体看上面的left和right 每一次出来都会全部调用
        //System.out.println("left:" + left +"right:"+right);

        //每次拷贝的数值只是对应的范围，而不是全部的（从0开始的）数值
        int t = 0;
        for(int left_pointer = left;left_pointer<=right;left_pointer++){
            arr[left_pointer]=temp[t];
            t++;
        }

    }

    public static int[]RadixSort(int []arr) {
//        先获得最大的位数 （由于我们只需要获取整个的字符串长度就OK！！（关键
        int max_num = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max_num) {
                max_num = arr[i];
            }
        }
        int lengthOfMax = (max_num + "").length();

        int[][] bucketArr = new int[10][arr.length];

//        这是第一层：按照位数循环的次数
        int n = 1;
        for (int i = 0; i <lengthOfMax; i++,n*=10) {
//        for把数据放入🪣中 这是第二层
            int[] bucketCount = new int[10];
            for (int k : arr) {
//            第一轮：
                int temp = k / n % 10;
                bucketCount[temp]++;
                bucketArr[temp][bucketCount[temp]] = k;
            }

//            入桶了之后 开始把数据挨个拿出来 放回原来的数组
            int index = 0;
            while (index < arr.length) { //这个while的循环感觉也可有可无
                for (int j = 0; j < 10; j++) {
                    while (bucketCount[j] != 0) {
                        arr[index] = bucketArr[j][bucketCount[j]];
                        index++;
                        bucketCount[j]--;
                    }
                    bucketCount[j]=0; //重新初始化
                }
            }
        }
        return arr;
    }
/*小结一下：
1。为了能够使得基数排序对任何情况下都能够使用，int lengthOfMax = (max_num + "").length(); 来获取长度
2。每一轮放完需要重新初始化计数桶
 */

}

