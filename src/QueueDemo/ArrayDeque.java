//import java.util.Deque;
//
///**
// * 基于循环数组实现, 特点
// * <ul>
// *     <li>tail 停下来的位置不存储, 会浪费一个位置</li>
// * </ul>
// * @param <E>
// */
//public class ArrayDeque<E> implements Iterable<E> {
//
//    /*
//                    h
//            t
//        0   1   2   3
//        b           a
//     */
//
//    public boolean offerFirst(E e) {
//        if (isFull()) {
//            return false;
//        }
//        head = dec(head, array.length);
//        array[head] = e;
//        return true;
//    }
//
//    @Override
//    public boolean offerLast(E e) {
//        if (isFull()) {
//            return false;
//        }
//        array[tail] = e;
//        tail = inc(tail, array.length);
//        return true;
//    }
//
//    public E pollFirst() {
//        if (isEmpty()) {
//            return null;
//        }
//        E e = array[head];
//        array[head] = null;
//        head = inc(head, array.length);
//        return e;
//    }
//
//    public E pollLast() {
//        if (isEmpty()) {
//            return null;
//        }
//        tail = dec(tail, array.length);
//        E e = array[tail];
//        array[tail] = null;
//        return e;
//    }
//
//    @Override
//    public E peekFirst() {
//        if (isEmpty()) {
//            return null;
//        }
//        return array[head];
//    }
//
//    @Override
//    public E peekLast() {
//        if (isEmpty()) {
//            return null;
//        }
//        return array[dec(tail, array.length)];
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return head == tail;
//    }
//
//    @Override
//    public boolean isFull() {
//        if (tail > head) {
//            return tail - head == array.length - 1;
//        } else if (tail < head) {
//            return head - tail == 1;
//        } else {
//            return false;
//        }
//    }
//
//    @Override
//    public Iterator<E> iterator() {
//        return new Iterator<E>() {
//            int p = head;
//            @Override
//            public boolean hasNext() {
//                return p != tail;
//            }
//
//            @Override
//            public E next() {
//                E e = array[p];
//                p = inc(p, array.length);
//                return e;
//            }
//        };
//    }
//
//    E[] array;
//    int head;
//    int tail;
//
//    @SuppressWarnings("unchecked")
//    public ArrayDeque1(int capacity) {
//        array = (E[]) new Object[capacity + 1];
//    }
//
//    static int inc(int i, int length) {
//        if (i + 1 >= length) {
//            return 0;
//        }
//        return i + 1;
//    }
//
//    static int dec(int i, int length) {
//        if (i - 1 < 0) {
//            return length - 1;
//        }
//        return i - 1;
//    }
//}