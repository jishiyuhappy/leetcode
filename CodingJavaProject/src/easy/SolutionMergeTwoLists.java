package easy;/*
    You are given the heads of two sorted linked lists list1 and list2.

    Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.

    Return the head of the merged linked list.
 */
import utils.ListNode;

public class SolutionMergeTwoLists {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list2 == null) {
            return  list1;
        } else if (list1 == null) {
            return list2;
        }

        ListNode mergedListHead = list1.val <= list2.val ? list1 : list2;
        ListNode mergedList = null;

        while (true) {
            if (list1.val <= list2.val) {
                if (mergedList == null) {
                    mergedList = list1;
                } else {
                    mergedList.next = list1;
                    mergedList = list1;
                }

                if (list1.next == null) {
                    mergedList.next = list2;
                    break;
                } else {
                    list1 = list1.next;
                }
            } else {
                if (mergedList == null) {
                    mergedList = list2;
                } else {
                    mergedList.next = list2;
                    mergedList = list2;
                }

                if (list2.next == null) {
                    mergedList.next = list1;
                    break;
                } else {
                    list2 = list2.next;
                }
            }
        }

        return mergedListHead;
    }


}