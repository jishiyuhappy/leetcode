package medium;

public class SearchInRotatedSortedArray {

    /*
    第一次通过：Runtime 0ms/100%, Memory 42.4/18.89%
    比单纯的递增，多了一些判断条件。
    还是二分查找法
     */
    public int search(int[] nums, int target) {
        int length = nums.length;
        if (length==1) return nums[0] == target ? 0 : -1;

        // not rotate
//        if (nums[0] < nums[length-1]) {
//            return binarySearch(nums, 0, length-1, target);
//        } else {
//            return binarySearchRotated(nums, 0, length-1, target);
//        }

        return binarySearchRotated(nums, 0, length-1, target);
    }

    private int binarySearch(int[] nums, int left, int right, int target) {
        if (right < left) {
            return -1;
        } else if (right == left) {
            return nums[left] == target ? left : -1;
        } /*else if (right == left + 1) { // 2个值的case
            if (nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            } else {
                return -1;
            }
        }*/


        int mid = left + (right - left)/2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, left, mid-1, target);
        } else {
            return binarySearch(nums, mid+1, right, target);
        }
    }

    private int binarySearchRotated(int[] nums, int left, int right, int target) {
        if (right < left) {
            return -1;
        } else if (right == left) {
            return nums[left] == target ? left : -1;
        } else if (right == left + 1) { // 2个值的case
            if (nums[left] == target) {
                return left;
            } else if (nums[right] == target) {
                return right;
            } else {
                return -1;
            }
        }

        int mid = left + (right - left)/2;

        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {

            if (nums[left] == target) {
                return left;
            } else if (nums[left] > target) {
                //return binarySearchRotated(nums, mid+1, right, target);

                if (nums[mid] > nums[left]) { // go right
                    return binarySearchRotated(nums, mid+1, right, target);
                } else {
                    return binarySearchRotated(nums, left+1, mid-1, target);
                }

            } else {
                return binarySearchRotated(nums, left+1, mid-1, target);
            }
        } else if (nums[mid] < target) {



            if (nums[right] == target) {
                return right;
            } else if (nums[right] > target) { // right
                return binarySearchRotated(nums, mid+1, right-1, target);
            } else {

                //return binarySearchRotated(nums, left, mid-1, target);

                if (nums[mid] > nums[right]) { // go right
                    return binarySearchRotated(nums, mid+1, right-1, target);
                } else {
                    return binarySearchRotated(nums, left, mid-1, target);
                }
            }
        }
        return -1;
    }
}
