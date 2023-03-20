package hard;
import utils.ListNode;
/**
 * Definition for singly-linked list.
 * public class utils.ListNode {
 *     int val;
 *     utils.ListNode next;
 *     utils.ListNode() {}
 *     utils.ListNode(int val) { this.val = val; }
 *     utils.ListNode(int val, utils.ListNode next) { this.val = val; this.next = next; }
 * }
 */

/*
Constraints:
The number of nodes in the list is n.
1 <= k <= n <= 5000
0 <= Node.val <= 1000
 */

public class ReverseKGroup {


    /*
        尝试精简代码 - 先算出长度，这样就不需要最后一段的回转
        第二次通过：Runtime 0ms/100%, Memory 42.2/44.95%

        简化 newHead 第一次判断，直接创建一个虚拟的head
        第三次通过：Runtime 0ms/100%, Memory 42.2/38.30%
     */

    public ListNode reverseKGroup2(ListNode head, int k) {
        int size = getSizeOfList(head);
        if (size < k || k <= 1) return head;

        ListNode newHead = new ListNode(-1);

        ListNode preNode = head; // 赋值无用
        ListNode nextNode = head; // 赋值无用
        ListNode preTailNode = newHead;


        int round = size/k;
        for (int l=0; l<round; l++) { // 一共需要swagger几次

            preNode = nextNode; // 第一个node
            nextNode = nextNode.next; // 第二个node

            ListNode newTailNode = preNode;// the first node

            for (int i=1; i<k; i++) { // swap k-1 times
                ListNode temp = nextNode.next;
                nextNode.next = preNode;
                preNode = nextNode;
                nextNode = temp;
            }

            preTailNode.next = preNode; // 上一个round连接下一个round
            preTailNode = newTailNode;

        }

        if (nextNode != null) {// 最后一轮，不需要反转
            preTailNode.next = nextNode;
        } else {
            preTailNode.next = null;
        }

        return newHead.next;
    }

    private int getSizeOfList(ListNode head) { // O(n)
        int size = 0;
        while (head != null) {
            head = head.next;
            size++;
        }
        return size;
    }





    /*
    写起来太费劲了，各种打补丁
    Error - [1,2], 2
    第一次通过：Runtime 0ms/100%, Memory 42.6/25.12%
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 1 || head == null || head.next == null) return head;

        ListNode newHead = head;
        boolean firstRound = true;

        ListNode preNode = head;
        ListNode nextNode = preNode.next;

        ListNode preTailNode = preNode;// the first node will be the last node

        while (nextNode != null) {

            int i=1;

            ListNode newTailNode = preNode;// the first node

            for (i=1; i<k && (nextNode != null); i++) { // swap k-1 times
                ListNode temp = nextNode.next;
                nextNode.next = preNode;
                preNode = nextNode;
                nextNode = temp;
            }

            if (i == k) { // 正常
                if (firstRound) {
                    firstRound = false;
                    newHead = preNode;

                    preTailNode = newTailNode;

                } else {
                    preTailNode.next = preNode; // 上一个round连接下一个round
                    preTailNode = newTailNode;
                }

                if (nextNode == null) {
                    preTailNode.next = null;
                    break;
                }
                // move forward +1 step
                preNode = nextNode;
                nextNode = nextNode.next;
                if (nextNode == null) { // only one node
                    preTailNode.next = preNode;
                    preNode.next = null;
                    break;
                }

            } else { // i < k 得重置 最后一次

                nextNode = preNode.next;
                preNode.next = null;
                for (; i>1; i--) { // swap k-1 times
                    ListNode temp = nextNode.next;
                    nextNode.next = preNode;
                    preNode = nextNode;
                    nextNode = temp;
                }

                preTailNode.next = preNode;
                break;
            }
        }

        return newHead;
    }
}
