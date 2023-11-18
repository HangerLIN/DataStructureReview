package QueueDemo;

import java.util.Iterator;

public class CircleDeque<E> implements Iterable<E>{

    private int head = 0;
    private int tail = 0;
    private final E[] array;
    private final int length;

    @SuppressWarnings("all")
    public CircleDeque(int capacity) {
        length = capacity + 1;
        array = (E[]) new Object[length];
    }


    public boolean offer(E value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = (tail + 1) % length;
        return true;
    }


    public E poll() {
        if (isEmpty()) {
            return null;
        }
        E value = array[head];
        head = (head + 1) % length;
        return value;
    }


    public E peek() {
        if (isEmpty()) {
            return null;
        }
        return array[head];
    }


    public boolean isEmpty() {
        return tail == head;
    }


    public boolean isFull() {
        return (tail + 1) % length == head;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int p = head;
            @Override
            public boolean hasNext() {
                return p != tail;
            }

            @Override
            public E next() {
                E value = array[p];
                p = (p + 1) % array.length;
                return value;
            }
        };
    }
}