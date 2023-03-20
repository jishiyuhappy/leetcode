package hard;

/*
    Given two sorted arrays nums1 and nums2 of size m and n respectively, return the median of the two sorted arrays.

    The overall run time complexity should be O(log (m+n)).
 */
public class SolutionFindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null) {
            //if (nums2 == null) // not exists: m+n >= 1
            return  findMedianInArray(nums2);
        } else if (nums2 == null) {
            return  findMedianInArray(nums1);
        }

        int totalNum = nums1.length + nums2.length; // should be >= 2

        int medianNum = totalNum/2;

        int currentIndex = 0;
        int currentIndexNums1 = 0;
        int currentIndexNums2 = 0;

        int firstMedian = 0;
        while (currentIndex < medianNum) {

            if (currentIndexNums1 == nums1.length) { // list 1 is end
                firstMedian = nums2[currentIndexNums2];
                currentIndexNums2++;
            } else if (currentIndexNums2 == nums2.length) { // list 2 is end
                firstMedian = nums1[currentIndexNums1];
                currentIndexNums1++;
            } else if (nums1[currentIndexNums1] <= nums2[currentIndexNums2]) { // both have value
                firstMedian = nums1[currentIndexNums1];
                currentIndexNums1++;
            } else {
                firstMedian = nums2[currentIndexNums2];
                currentIndexNums2++;
            }

            currentIndex++;
        }

        int secondMedian = 0;
        if (currentIndexNums1 == nums1.length) { // list 1 is end
            secondMedian = nums2[currentIndexNums2];
        } else if (currentIndexNums2 == nums2.length) { // list 2 is end
            secondMedian = nums1[currentIndexNums1];
        } else if (nums1[currentIndexNums1] <= nums2[currentIndexNums2]) {
            secondMedian = nums1[currentIndexNums1];
        } else {
            secondMedian = nums2[currentIndexNums2];
        }

        if (totalNum % 2 == 0) {
            return (firstMedian + secondMedian) / 2.0;
        } else {
            return secondMedian;
        }

    }

    double findMedianInArray(int[] nums) {
        int length = nums.length;
        if (length % 2 == 0) {
            return (nums[length/2 - 1] + nums[length/2]) / 2.0;
        } else {
            return nums[length/2];
        }
    }
}
