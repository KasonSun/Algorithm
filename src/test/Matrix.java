package test;

public class Matrix {
    public static void main(String[] args) {
        int[][] matrix = {{1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}};
        int target = 5;
        int target1= 20;
        System.out.println(judgeInteger01(matrix, target1));
    }

    public static boolean judgeInteger(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean judgeInteger01(int[][] matrix, int target) {
        for (int i = matrix.length-1; i>=0; i--) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == target) {
                    return true;
                } else if (matrix[i][j] > target) {
                    break;
                }else{
                    continue;
                }
            }
        }
        return false;
    }
}
