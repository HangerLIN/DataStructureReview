package KQuestion;

import LinkedListLeetcode.LinkedListDemo;

import java.util.*;

public class KDemo {
    public int findKthLargest(int[] nums, int k) {
        MinHeap heap = new MinHeap(nums);
        int num = -1;
        for (int i = 0; i <= k; i++) {
            num = heap.poll();
        }
        return num;
    }

    // 215. 数组中的第K个最大元素（手动建堆）== 小顶堆
    static class MinHeap {
        public int[] data;
        public int size;

        public MinHeap(int[] arr) {
            this.data = arr;
            this.size = arr.length;
            heapFy();
        }

        public void heapFy() {
            for (int i = size / 2 - 1; i >= 0; i--) {
                shiftDown(i);
            }
        }

        public int poll() {
            if (size <= 0) {
                return -1;
            }
            int res = data[0];
            swap(0, size--);
            shiftDown(0);
            return res;
        }

        public void offer(int val) {
            if (size == data.length) {
                throw new IllegalStateException("FULL Queue!");
            }
            data[size++] = val;
            shiftUp(val);
        }

        public void shiftDown(int parent) {
            int left_child = parent * 2 + 1;
            int right_child = parent * 2 + 2;
            int min = parent;

            //比较左右大小
            if (left_child < size && data[left_child] < data[parent]) min = left_child;
            if (right_child < size && data[right_child] < data[parent]) min = right_child;
            if (min != parent) {
                swap(parent, min);
                shiftDown(min);
            }
        }

        public void shiftUp(int index) {
            if (index <= 0) return;
            int parentIndex = (index - 1) >> 1;
            if (data[parentIndex] > data[index]) {
                swap(index, parentIndex);
                shiftUp(parentIndex);
            }
        }

        public void swap(int i, int j) {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
    }


//        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
//            Queue<List<Integer>> queue = new PriorityQueue<>(
//                    (o1, o2) -> (o1.get(0) + o1.get(1)) - (o2.get(0) + o2.get(1))
//            );
//
//            List<List<Integer>> res = new ArrayList<>();
//
//            if (nums1.length == 0 || nums2.length == 0 || k == 0) {
//                return res;
//            }
//
//            for (int i = 0; i < nums1.length; i++) {
//                for (int j = 0; j < nums2.length; j++) {
//                    List<Integer> pair = new ArrayList<>();
//                    pair.add(nums1[i]);
//                    pair.add(nums2[j]);
//                    queue.offer(pair);
//                }
//            }
//
//            int count = 0;
//            while (!queue.isEmpty() && count < k) {
//                res.add(queue.poll());
//                count++;
//            }
//
//            return res;
//        }
//    }

        /*
        public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            //滑动窗口
            List<List<Integer>> res = new ArrayList<>();
            if (nums1.length == 0 || nums2.length == 0 || k == 0) {
                return res;
            }

            int counter = 0;
            int i = 0,j = 0;
            while (counter < k)
                if (nums1[i + 1] > nums2[j]) {
                    //如果nums1[i+1]的数据比当前的nums2[j]的数据要更大，移动nums2的窗口
                    res.add(counter++,List.of(nums1[i],nums2[j++]));
                }
                else{
                    //否则移动nums1的窗口
                    res.add(counter++,List.of(nums1[i++],nums2[j]));
                }
            return res;
        }
        *
         */

            public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
                // 存储三元组 (num1[i], nums2[i], i)
                // i 记录 nums2 元素的索引位置，用于生成下一个节点
                PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> (a[0] + a[1])));
                // 按照 23 题的逻辑初始化优先级队列
                for (int i = 0; i < nums1.length; i++) {
                    pq.offer(new int[]{nums1[i], nums2[0], 0});
                }

                List<List<Integer>> res = new ArrayList<>();
                // 执行合并多个有序链表的逻辑
                while (!pq.isEmpty() && k > 0) {
                    int[] cur = pq.poll();
                    k--;
                    // 链表中的下一个节点加入优先级队列
                    int next_index = cur[2] + 1;
                    if (next_index < nums2.length) {
                        pq.add(new int[]{cur[0], nums2[next_index], next_index});
                    }

                    List<Integer> pair = new ArrayList<>();
                    pair.add(cur[0]);
                    pair.add(cur[1]);
                    res.add(pair);
//                    pair.clear();
                }
                return res;
            }

        public ListNode mergeKLists(ListNode[] lists) {
            //仿照刚在合并两个有序链表的思路，使用优先级队列（有序数组的思路）
            //优先级队列的元素是链表的头节点
            PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
            //将所有链表的头节点加入优先级队列
            for (ListNode node : lists) {
                if (node != null) {
                    pq.offer(node);
                }
            }
            //创建一个dummy
            ListNode dummy = new ListNode(-1);
            ListNode cur = dummy;
            while (!pq.isEmpty()){
                ListNode node = pq.poll();
                cur.next = node;
                cur = cur.next;
                if (node.next != null) {
                    pq.add(node.next);
                }
            }
            return dummy.next;
        }

    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new LinkedList<>();
        if (k == 0 || nums.length == 0 || k > nums.length) return list.stream().mapToInt(Integer::intValue).toArray();

        //如果需要指定容量，可以在构造函数中传入一个整数值来指定队列的容量
        int back = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int front = k; front < nums.length ; front++) {
            //加入到优先队列里
            for (int i = back; i < front; i++) {
                pq.add(nums[i]);
                if (pq.size() == k) {
                    list.add(pq.poll());
                    back++;
                }
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }



}

