package QueueDemo;

import java.lang.reflect.Array;
import java.util.Arrays;

import static sort.all.swap;

public class NewHeap {
    int[] array;
    int size;

    public NewHeap(int[] arr) {
        this.array = arr;
        this.size = arr.length; //你他妈的，忘记了这个size，导致了下面的heapFy()方法中的for循环的条件写错了，导致无论如何都是从0开始的，导致了下面的Down()方法中的if判断条件写错了，导致了下面的swap()方法中的参数写错了，导致了最后的结果不对
        heapFy();
    }

    public void heapFy() {
        for (int i = size/2 - 1; i >= 0; i--) {
            Down(i); // 从最后一个非叶子节点开始下沉(从右往左, 从下往上) == 时间复杂度 O(n)
        }
    }

    public void offer(int val) {
        if (size >= array.length) {
            return;
        }
        array[size++] = val;
        Up(val); // 从最后一个元素开始上浮 == 时间复杂度 O(logn)
    }

    public int poll() {
        if (size <= 0) {
            return -1;
        }
        int ret = array[0];
        array[0] = array[--size];
        Down(0); // 从根节点开始下沉 == 时间复杂度 O(logn)
        return ret;
    }

    // 将 parent 索引处的元素下潜: 直到满足最大堆的性质
    public void Down(int parent) {
        int left_child = parent * 2 + 1;
        int right_child = parent * 2 + 2;
        int max = parent;

        if (left_child < size && array[left_child] > array[max]) {
            max = left_child;
        }
        if (right_child < size && array[left_child] < array[right_child]) {
            max = right_child;
        }
        if (max != parent) //如果发现没有更新，说明已经满足最大堆的性质；否则就交换，继续下沉
        {
            swap(parent, max);
            Down(max);
        }
    }

    // 将 offered 元素上浮: 直至 offered 小于父元素或到堆顶
    public void Up(int offered) {
        int child = size - 1;
        int parent = (child - 1) / 2;
        //不断把目标值从底下往上方浮动，直到它比父节点大或者到达堆顶
        while (child > 0 && array[parent] < offered) {
            //不要使用 swap() 方法，因为它会导致数组中的元素交换两次
//            swap(parent, child);
//            child = parent;
//            parent = (child - 1) / 2;
            array[child] = array[parent]; // 把父节点的值赋值给子节点（把父节点往下沉，实际上是offer的值往上浮）
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = offered; // 最后就是把加入的数值赋值给最后的位置
    }

    //swap the index of i and j
    public void swap(int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = {2,3,1,7,6,4,5};
        System.out.println(Arrays.toString(arr));
        NewHeap heap = new NewHeap(arr);
        System.out.println(Arrays.toString(heap.array));
        while (heap.size > 1) {
            heap.swap(0, heap.size - 1);
            heap.size--;
            heap.Down(0);
        }
        System.out.println(Arrays.toString(heap.array));
    }
}

/*在这段代码中，实现了堆排序算法。关于你的疑问，我逐一解释如下：

为什么当 size-- 之后我还可以照样打印出这个数组呢？
size 变量表示堆中的元素数量，而非数组本身的长度。当执行 size-- 时，只是改变了堆中元素的数量，数组本身并没有改变。实际上，数组中的元素还在原来的位置，只是我们不再考虑那些已经排好序的元素。当你打印数组时，它仍然包含所有元素，只是我们只关心 size 之前的那些元素。

我每次下沉到最下面的那个元素又被放在哪里了呢？
在 while 循环中，每次循环的第一步是交换堆顶元素（索引为 0 的元素）和当前堆的最后一个元素（索引为 size - 1 的元素）。这样，最大元素被放置在堆的末尾，并从堆中排除（通过减小 size 实现）。接着，新的堆顶元素需要下沉以恢复堆的性质。在下沉过程中，元素会根据其值向下移动，直到找到合适的位置。因此，每次下沉后的元素仍然在数组中，只是它们的位置发生了改变。

总结一下，堆排序算法的过程是通过不断提取堆顶元素（最大元素），将其放置在堆的末尾，然后恢复堆的性质。在整个过程中，数组的长度不变，而堆中的元素数量逐渐减小
*/