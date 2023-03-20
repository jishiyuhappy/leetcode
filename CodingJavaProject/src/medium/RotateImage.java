package medium;

public class RotateImage {
    /*
        Pass 1: 0ms/100%, 42.9MB/12.52%
        算下标和替换顺序 花了不少时间
     */
    public void rotate(int[][] matrix) {
        int length = matrix.length;
        int layer = length/2;

        for (int i = 0; i < layer; i++) { // for each layer

            for (int j = 0; j < length - i*2 - 1; j++) { // rotate this layer, j is the length
                // 1st - fix horizontal axis - i
                //matrix[i][i+j];

                // 2nd - fix vertical axis - length - i - 1
                //matrix[i+j][length - i - 1]; // update 1st

                // 3rd - fix horizontal axis - length - i - 1
                //matrix[length - i - 1][length - i - 1 - j];

                // 4th - fix vertical axis - i
                // matrix[length - i - 1 - j][i];

                int temp = matrix[i][i+j];
                matrix[i][i+j] = matrix[length - i - 1 - j][i];
                matrix[length - i - 1 - j][i] = matrix[length - i - 1][length - i - 1 - j];
                matrix[length - i - 1][length - i - 1 - j] = matrix[i+j][length - i - 1];
                matrix[i+j][length - i - 1] = temp;
            }
        }
    }
}
