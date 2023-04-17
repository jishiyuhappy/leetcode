package easy;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    public int singleNumber(int[] nums) {
        int result = 0;
        for (int value: nums) {
            result ^= value;
        }
        return result;
    }

    public int singleNumber2(int[] nums) {

        Map<Integer, Boolean> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.remove(nums[i]);
            } else {
                map.put(nums[i], true);
            }
        }

        for (Integer key: map.keySet()) {
            return key;
        }

        return 0;// should not be here
    }
}
