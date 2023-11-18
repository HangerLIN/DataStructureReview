package QueueDemo;

public class PriorityQueue {
    //使用数列的形式实现优先队列，每个函数后面加上注释，说明该函数的作用
    private int[] array;
    private int size;

    public PriorityQueue(int capacity) {
        array = new int[capacity];
        size = 0;
    }

    public void offer(int value) {
        if (size == array.length) {
            throw new IllegalStateException("Queue is full");
        }
        array[size++] = value;
    }

    public Integer poll() {
        if (size == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        int result = array[maxIndex];
        array[maxIndex] = array[size - 1];
        size--;
        return result;
    }

    public Integer peek() {
        if (size == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 1; i < size; i++) {
            if (array[i] > array[maxIndex]) {
                maxIndex = i;
            }
        }
        return array[maxIndex];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue(5);
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        System.out.println(queue.poll());
}

}
