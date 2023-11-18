//package QueueDemo;
//
//import java.util.Iterator;
//
///**
// * 基于环形链表的双端队列
// * @param <E> 元素类型
// */
//
//
////简单来说，实际上就是一个环形链表，只不过是双向的
//public class LinkedListDeque<E> implements Iterator<E> {
//    public boolean offerFirst(E e) {
//        if (isFull()) {
//            return false;
//        }
//        size++;
//        Node<E> a = sentinel;
//        Node<E> b = sentinel.next;
//        Node<E> offered = new Node<>(a, e, b);
//        a.next = offered;
//        b.prev = offered;
//        return true;
//    }
//
//
//    public boolean offerLast(E e) {
//        if (isFull()) {
//            return false;
//        }
//        size++;
//        Node<E> a = sentinel.prev;
//        Node<E> b = sentinel;
//        Node<E> offered = new Node<>(a, e, b);
//        a.next = offered;
//        b.prev = offered;
//        return true;
//    }
//
//
//    public E pollFirst() {
//        if (isEmpty()) {
//            return null;
//        }
//        Node<E> a = sentinel;
//        Node<E> polled = sentinel.next;
//        Node<E> b = polled.next;
//        a.next = b;
//        b.prev = a;
//        size--;
//        return polled.value;
//    }
//
//
//    public E pollLast() {
//        if (isEmpty()) {
//            return null;
//        }
//        Node<E> polled = sentinel.prev;
//        Node<E> a = polled.prev;
//        Node<E> b = sentinel;
//        a.next = b;
//        b.prev = a;
//        size--;
//        return polled.value;
//    }
//
//
//    public E peekFirst() {
//        if (isEmpty()) {
//            return null;
//        }
//        return sentinel.next.value;
//    }
//
//
//    public E peekLast() {
//        if (isEmpty()) {
//            return null;
//        }
//        return sentinel.prev.value;
//    }
//
//    public boolean isEmpty() {
//        return size == 0;
//    }
//
//    public boolean isFull() {
//        return size == capacity;
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return new Iterator<E>() {
//            Node<E> p = sentinel.next;
//            @Override
//            public boolean hasNext() {
//                return p != sentinel;
//            }
//
//            @Override
//            public E next() {
//                E value = p.value;
//                p = p.next;
//                return value;
//            }
//        };
//    }
//
//    @Override
//    public boolean hasNext() {
//        return false;
//    }
//
//    @Override
//    public E next() {
//        return null;
//    }
//
//    static class Node<E> {
//        Node<E> prev;
//        E value;
//        Node<E> next;
//
//        public Node(Node<E> prev, E value, Node<E> next) {
//            this.prev = prev;
//            this.value = value;
//            this.next = next;
//        }
//    }
//
//    Node<E> sentinel = new Node<>(null, null, null);
//    int capacity;
//    int size;
//
//    //构造函数
//    //这里的capacity是指最大容量，不包括sentinel
//    public LinkedListDeque(int capacity) {
//        sentinel.next = sentinel;
//        sentinel.prev = sentinel;
//        this.capacity = capacity;
//    }
//}