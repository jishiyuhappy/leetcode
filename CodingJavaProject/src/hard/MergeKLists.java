package hard;
import utils.ListNode;

import java.util.List;

public class MergeKLists {

    /*
    第二次通过：Runtime 2ms/91.82%, Memory 44.8/16.55%
    为何这一次这么快呢？因为分治法减少了比较次数吗？
     */
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        int count = lists.length;

        // 分治法，两两merge
        while (count>1) {
            int half = (count+1)/2;//奇数列单独到下一轮
            for (int i=0; i<count/2; i++) {
                lists[i] = mergeTwoList(lists[i], lists[i+half]);
            }
            count = half;
        }

        return lists[0];
    }

    private ListNode mergeTwoList(ListNode firstList, ListNode secondList) {
        if (firstList == null) return secondList;
        if (secondList == null) return firstList;

        ListNode newList = new ListNode(-1);
        ListNode curNode = newList;
        while (firstList != null && secondList != null) {
            if (firstList.val <= secondList.val) {
                curNode.next = firstList;
                firstList = firstList.next;
            } else {
                curNode.next = secondList;

                secondList = secondList.next;
            }
            curNode = curNode.next;
        }

        if (firstList == null) {
            curNode.next = secondList;
        } else {
            curNode.next = firstList;
        }

        return newList.next;
    }






    /*
    error: [[],[]] - 空list的情况？？？
    error: [[],[1]]
    第一次通过：Runtime 318ms/7.26%, Memory 44.8/16.55%
    一开始的想法就有了，但是写出了花了很多时间、、、、、、、、、
    我这个方法应该是"Compare one by one"
     */
    /*public utils.ListNode mergeKLists(utils.ListNode[] lists) {

        if (lists == null || lists.length == 0) return null;
        if (lists.length == 1) return lists[0];

        int count = lists.length;

        int firstNodeIndex = 0;
        boolean allEmpty = true;
        for (int i=0; i<count; i++) {
            if (lists[i] != null) {
                if (allEmpty) {
                    allEmpty = false;
                    firstNodeIndex = i;
                } else if ( lists[firstNodeIndex].val > lists[i].val) {
                    firstNodeIndex = i;
                }
            }
        }
        if (allEmpty) return null;

        // set the first/smallest node
        utils.ListNode firstNode = lists[firstNodeIndex];
        lists[firstNodeIndex] = lists[firstNodeIndex].next; // move 1 step

        utils.ListNode curNode = firstNode;
        //int nextNodeIndex = firstNodeIndex;
        while (true) {

            boolean noLeftNode = true;
            int nextNodeIndex = -1;

            for (int i = 0; i < count; i++) {
                if (lists[i] != null) { // still valid
                    if (nextNodeIndex == -1) { // first valid node
                        nextNodeIndex = i;
                        noLeftNode = false;
                    } else if (lists[nextNodeIndex].val > lists[i].val) { // find the next smallest
                        nextNodeIndex = i;
                    }


                } //else: skip this empty list
            }

            if (noLeftNode) break;

            // one loop end, find out the current smallest
            curNode.next = lists[nextNodeIndex];
            curNode = curNode.next;
            lists[nextNodeIndex] = lists[nextNodeIndex].next;// move to next

            while (lists[nextNodeIndex] != null) {// if the same value
                if (lists[nextNodeIndex].val == curNode.val) {
                    curNode = lists[nextNodeIndex];
                    lists[nextNodeIndex] = lists[nextNodeIndex].next;
                } else {
                    break;
                }
            }
        }

        return firstNode;
    }*/
}

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
