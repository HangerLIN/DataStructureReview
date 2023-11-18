package QueueDemo;




public class SmallHeap {
    private int[] array;
    private int size;
//忘记了：capacity是容量的上限；size的数值是目前数组容纳数目
    private SmallHeap(int capacity){
        array = new int[capacity];
        this.size = 0;
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean isFull(){
        return size == array.length;
    }

    public int poll(){
        if(isEmpty()){
            return -1;
        }
        int ret = array[0];
        swap(0,size-1);
        size--; //直接把最后的那个位置的引用给假删除
        int parent = 0;
        int child =  1;
        while (child < size){
            //左右比较
            if (child + 1 < size && array[child] > array[child + 1]) child++;
            if (array[parent] < array[child]) break;
            //如果已经可以达到了父亲的节点已经比后面的孩子节点还已经小了，那么就已经符合了最小堆的定义
            swap(parent,child);
            parent = child;
            child*=2 + 1;
        }
        return ret;
    }

    public boolean offer(int val){
        if (size == array.length) {
            return false;
        }
        int child = size;
        int parent = (child - 1) / 2;
        while (child > 0 && array[child] > array[parent]){
            //不满足条件就需要一直调整
            swap(child,parent);
            child = parent;
            parent = (child - 1) / 2;
        }
        array[child] = val; //最后在合适的地方插入下标
        size++;
        return true;
    }

    public int peek(){
        if (isEmpty()) {
            return -1;
        }
        return array[0];
    }
}
