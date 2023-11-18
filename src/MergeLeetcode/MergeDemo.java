package MergeLeetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MergeDemo {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
    public ListNode sortList(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode p = head;
        int length = 0;
        //计算链表长度
        while (p != null) {
            length++;
            p = p.next;
        }

//每一次都将链表拆分成若干个长度为size的子链表，然后按照每两个子链表一组进行合并
        for (int size = 1; size < length; size <<= 1) {
            ListNode cur = dummyHead.next;
            ListNode tail = dummyHead;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(left, size);
                cur = cut(right, size);

                tail.next = merge(left, right);
                while (tail.next != null) {
                    tail = tail.next;
                //tail指向合并后的链表的最后一个节点
                }
            }
        }
        return dummyHead.next;
    }

    //cut(l, n)，可能有些同学没有听说过，它其实就是一种 split 操作，即断链操作。
    //不过我感觉使用 cut 更准确一些，它表示，将链表 l 切掉前 n 个节点，并返回后半部分的链表头。
    private ListNode cut(ListNode head, int n) {
        ListNode p = head;
        //往后切割（但是返回后面的那段链表的头结点）
        while (--n > 0 && p != null) {
            p = p.next;
        }

        if (p == null) return null;

        ListNode next = p.next;
        p.next = null;
        return next;
    }

    //合并两个有序链表
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = l2;
                l2 = l2.next;
            }
        }
        p.next = l1 != null ? l1 : l2;
        return dummyHead.next;
    }

//    public int[][] merge(int[][] intervals) {
//        // 将区间数组按照起始值进行排序
//        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
//
//        // 初始化一个结果列表
//        LinkedList<int[]> merged = new LinkedList<>();
//
//        // 遍历排序后的区间数组
//        for (int[] interval : intervals) {
//            // 如果结果列表为空，或者当前区间与结果列表中最后一个区间不重叠
//            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
//                // 将当前区间添加到结果列表中
//                merged.add(interval);
//            } else {
//                // 如果当前区间与结果列表中最后一个区间重叠，合并这两个区间
//                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
//            }
//        }
//
//        // 将结果列表转换为二维数组并返回
//        return merged.toArray(new int[merged.size()][]);
//    }
    //常规做法

//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int[] nums1_copy = new int[m];
//        System.arraycopy(nums1, 0, nums1_copy, 0, m);
//
//        int p1 = 0;
//        int p2 = 0;
//        int p = 0;
//
//        while ((p1 < m) && (p2 < n))
//            nums1[p++] = (nums1_copy[p1] < nums2[p2]) ? nums1_copy[p1++] : nums2[p2++];
//
//        if (p1 < m)
//            System.arraycopy(nums1_copy, p1, nums1, p1 + p2, m + n - p1 - p2);
//        if (p2 < n)
//            System.arraycopy(nums2, p2, nums1, p1 + p2, m + n - p1 - p2);
//    }

//    public void merge(int[] nums1, int m, int[] nums2, int n) {
//        int nums1Index = m - 1; // 指向 nums1 中最后一个有效元素
//        int nums2Index = n - 1; // 指向 nums2 中最后一个有效元素
//        int resultIndex = nums1.length - 1; // 指向 nums1 中插入新元素的位置
//
//        // 从后向前遍历两个数组，将较大的元素插入到 nums1 的合适位置
//        while (nums1Index > 0 || nums2Index > 0) {
//            if (nums1[nums1Index] > nums2[nums2Index]) nums1[resultIndex--] = nums1[nums1Index--];
//            else nums1[resultIndex--] = nums2[nums2Index--];
//        }
//
//        if (nums1Index > 0){
//            while (nums1Index > 0){
//                nums1[resultIndex--] = nums1[nums1Index--];
//            }
//        }
//        else {
//            while (nums2Index > 0){
//                nums1[resultIndex--] = nums2[nums2Index--];
//            }
//        }

    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return new int[0][];
        }

        // 根据区间的起始值进行排序
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 归并排序后的区间列表
        LinkedList<int[]> merged = new LinkedList<>();

        // 递归合并区间
        mergeIntervals(intervals, 0, intervals.length - 1, merged);

        // 将结果列表转换为二维数组并返回
        return merged.toArray(new int[merged.size()][]);
    }

    private void mergeIntervals(int[][] intervals, int start, int end, LinkedList<int[]> merged) {
        if (start == end) {
            merged.add(intervals[start]);
        } else {
            int mid = start + (end - start) / 2;

            // 递归合并左边区间
            mergeIntervals(intervals, start, mid, merged);

            // 递归合并右边区间
            mergeIntervals(intervals, mid + 1, end, merged);

            // 合并相邻重叠的区间
            mergeAdjacent(merged);
        }
    }

    private void mergeAdjacent(LinkedList<int[]> merged) {
        int i = 0;
        while (i < merged.size() - 1) {
            int[] curr = merged.get(i);
            int[] next = merged.get(i + 1);

            if (curr[1] >= next[0]) {
                // 如果当前区间与下一个区间重叠，合并这两个区间并移除下一个区间
                curr[1] = Math.max(curr[1], next[1]);
                merged.remove(i + 1);
            } else {
                // 如果当前区间与下一个区间不重叠，继续检查下一个区间
                i++;
            }
        }
    }






}




