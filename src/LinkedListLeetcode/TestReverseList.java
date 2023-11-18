package LinkedListLeetcode;

public class TestReverseList {
    public static void main(String[] args) {

        // Test case 1
        ListNode testCase1 = createList(new int[]{1, 2, 3, 4, 5});
        printList(reverseList(testCase1));

        // Test case 2
        ListNode testCase2 = createList(new int[]{1, 2, 3});
        printList(reverseList(testCase2));

        // Test case 3
        ListNode testCase3 = createList(new int[]{1});
        printList(reverseList(testCase3));

        // Test case 4
        ListNode testCase4 = createList(new int[]{});
        if (testCase4 != null) {
            printList(reverseList(testCase4));
        }
    }


    static class ListNode {
     int val;
     ListNode next;
     ListNode(int x) { val = x; }
 }
    public static ListNode reverseList(ListNode head) {
        if(head.next == null) return head;

        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }
    private static ListNode createList(int[] values) {
        if (values.length == 0) {
            return null;
        }
        ListNode head = new ListNode(values[0]);
        ListNode currentNode = head;
        for (int i = 1; i < values.length; i++) {
            currentNode.next = new ListNode(values[i]);
            currentNode = currentNode.next;
        }
        return head;
    }

    private static void printList(ListNode head) {
        ListNode currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.val + " -> ");
            currentNode = currentNode.next;
        }
        System.out.println("null");
    }
}
