package medium;
import utils.ListNode;
/*
Constraints:

The number of nodes in the list is sz.
1 <= sz <= 30
0 <= Node.val <= 100
1 <= n <= sz
 */

public class SolutionRemoveNthFromEnd {

    /*
        双指针，没有考虑到这个情况 // n is the length, remove the first node
        第一次通过：Runtime 0ms/100%, Memory 40.7MB/31.6%
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }

        ListNode firstNode = head; // the 0th node
        for (int i = 0; i < n; i++) {// the nth node
            firstNode = firstNode.next;
        }

        if (firstNode == null) { // n is the length, remove the first node
            return head.next;
        }

        ListNode secondNode = head; // the 0th node
        while (firstNode.next != null) {
            firstNode = firstNode.next;
            secondNode = secondNode.next;
        }
        secondNode.next = secondNode.next.next;
        return head;
    }
}

//Definition for singly-linked list.
/*public class utils.ListNode {
      int val;
      utils.ListNode next;
      utils.ListNode() {}
      utils.ListNode(int val) { this.val = val; }
      utils.ListNode(int val, utils.ListNode next) { this.val = val; this.next = next; }
 }*/
