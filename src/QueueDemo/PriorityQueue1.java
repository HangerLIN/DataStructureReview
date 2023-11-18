package QueueDemo;
public class PriorityQueue1 {
    //this version of PriorityQueue uses a heap to implement the queue
    //Max heap, to make it more easy, i make all element in this array are integer
    public int[] array;
    private int size;

    public PriorityQueue1(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    public boolean offer(int value) {
        if (size == array.length) {
            return false;
        }
        int child = size;
        int parent = (child - 1) / 2; //反正都是向下取整（向下取整的结果是一样的）

        //如果child的优先级大于parent的优先级，那么就交换（一直往上交换，直到完全满足条件）
        while (child > 0 && array[parent] < value) {
            swap(child, parent);
            child = parent;
            parent = (child - 1) / 2;               //迭代更新
        }
        array[child] = value;
        size++;
        return true;
    }
    public int poll(){
        if (isEmpty()) {
            return -1;
        }
        int ret = array[0];
        swap(0, size - 1);
        size--;
        //从根节点开始向下调整
        int parent = 0;
        int child =  1;
        while (child < size) {
            //找到左右孩子中较大的那个（作比较）
            if (child + 1 < size && array[child + 1] > array[child]) {
                child = child + 1;
            }
            //如果parent的优先级大于child的优先级，那么就不用交换了
            if (array[parent] >= array[child]) {
                break;
            }
            //否则就交换
            swap(parent, child);
            parent = child;
            child = parent * 2 + 1;
        }
        return ret;

        //值得一提的是，先把最后一个元素放到根节点，然后再从根节点向下调整；最后再把根节点的元素返回
    }

    public int peek() {
        if (isEmpty()) {
            return -1;
        }
        return array[0];
    }


    //写一个泛型的swap方法(按照下标交换数组中的元素)
    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public boolean isEmpty() {
        return size == 0;
    }



    public static void main(String[] args) {
//test the class
        PriorityQueue1 queue = new PriorityQueue1(10);
        queue.offer(1);
        queue.offer(3);
        queue.offer(9);
        queue.offer(4);
        queue.offer(5);

        System.out.println(queue.poll());

        //peek()
        System.out.println(queue.peek());

        System.out.println(queue.poll());

        //peek()
        System.out.println(queue.peek());

        //use assert to test the class
        assert queue.poll() == 3;
        assert queue.poll() == 2;

    }

}