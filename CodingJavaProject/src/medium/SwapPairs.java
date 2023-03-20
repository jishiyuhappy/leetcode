package medium;
import utils.ListNode;

public class SwapPairs {
    // 比 ReverseKGroup 简单很多
    public ListNode swapPairs(ListNode head) {

        ListNode newHead = new ListNode(-1);
        ListNode preTail = newHead;

        while (head != null && head.next !=null) {// 2 nodes

            ListNode secondNode = head.next;

            preTail.next = secondNode;
            preTail = head;

            head = secondNode.next;
            secondNode.next = preTail;
        }

        if (head != null) {
            preTail.next = head;
        } else {
            preTail.next = null;
        }

        return newHead.next;
    }
}
