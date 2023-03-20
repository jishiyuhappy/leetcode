package medium;

import java.util.ArrayList;
import java.util.List;

public class SpiralOrder {

    /*
        就各种算坐标，然后调试？？？
     */
    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> result = new ArrayList<Integer>();

        int row = matrix.length;
        int column = matrix[0].length;

        int roundsRow = row/2;
        int roundsColumn = column/2;
        int rounds = Math.min(roundsColumn, roundsRow);

        for (int i = 0; i < rounds; i++) {

            int curRow = row - i*2;
            int curColumn = column - i*2;

            for (int j = 0; j < curColumn; j++) result.add(matrix[i][i+j]);

            // 只过中间的一段
            for (int j = 0; j < curRow-2; j++) result.add(matrix[i+j+1][column-1-i]);

            for (int j = 0; j < curColumn; j++) result.add(matrix[row-1-i][column-1-i-j]);

            // 只过中间的一段
            for (int j = 0; j < curRow-2; j++) result.add(matrix[row-1-i-j-1][i]);
        }

        if (roundsRow <= roundsColumn) {
            if (row%2 == 1) {
                int curColumn = column - rounds*2;
                for (int i = 0; i < curColumn; i++) result.add(matrix[rounds][rounds+i]);
            }
        } else {
            if (column%2 == 1) {
                int curRow = row - rounds*2;
                for (int i = 0; i < curRow; i++) result.add(matrix[rounds+i][rounds]);
            }
        }

        return result;
    }
}
