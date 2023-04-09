package easy;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public List<List<Integer>> generate(int numRows) {

        List<List<Integer>> results = new ArrayList<>();

        List<Integer> preLevelList = new ArrayList<>();;
        for (int i = 0; i < numRows; i++) {

            List<Integer> curLevelList = new ArrayList<>();
            curLevelList.add(1); // the fist value always 1

            int calCount = i + 1 - 2;
            if (calCount > 0) {
                for (int j = 1; j <= calCount; j++) {
                    curLevelList.add(preLevelList.get(j) + preLevelList.get(j-1));
                }
            }

            if (i > 0) curLevelList.add(1); // the last value always 1

            results.add(curLevelList);
            preLevelList = curLevelList;
        }

        return results;
    }
}
