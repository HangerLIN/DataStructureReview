package QueueDemo;

import java.util.Iterator;

public class LinkedListQueueDemo<E> implements Iterable<E>{
    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;
    private int capacity = Integer.MAX_VALUE;

    public LinkedListQueueDemo(int capacity) {
        this.capacity = capacity;
        head = new Node<>(null, null);
        tail = head;
    }


    public boolean add(E e) {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full");
        }
        //tail point to the last node and tail.next point to the new node
        tail.next = new Node<>(e, null);
        //move tail to the new node (update itself)
        tail = tail.next;
        size++;
        return true;
    }

    //poll the first elements in the queue
    public E poll() {
        if (size == 0) {
            return null;
        }
        //head.next point to the first node
        //this head is a dummy node, so the first node is head.next
        Node<E> first = head.next;
        //head.next point to the second node
        head.next = first.next;
        //always make sure the dummy node point to the first node of the queue
        //if the first node is the last node, move tail to the head(also update itself)
        if (first == tail) {
            tail = head;
            //The line if (first == tail) { tail = head; } is checking if the first node is also the tail node, meaning that there's only one element in the queue. If this is true, it sets the tail pointer to the dummy head node. This is done because after removing the only element, the queue will be empty, and the tail should point to the dummy head node.
            //meaning that the queue is empty, so the tail should point to the dummy head node
        }
        size--;
        return first.value;
    }


    public E peek() {
        if (size == 0) {
            return null;
        }
        //not need to control the tail and first pointer
        return head.next.value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == tail;
        //just like a flag that this queue is empty
    }


    public boolean isFull() {
        return size == capacity;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> p = head.next;

            @Override
            public boolean hasNext() {
                return p != head;
            }

            @Override
            public E next() {
                E value = p.value;
                p = p.next;
                return value;
            }
        };
    }
}

