class Solution {
    static int[] answer;
    
    public int[] solution(int[][] arr) {
        answer = new int[2];
        div(arr, 0, 0, arr.length);
        return answer;
    }
    
    private static void div(int[][] arr, int sr, int sc, int size) {
        if (check(arr, sr, sc, size)) {
            answer[arr[sr][sc]]++;
            return;
        }
        size /= 2;
        div(arr, sr, sc, size);
        div(arr, sr + size, sc, size);
        div(arr, sr, sc + size, size);
        div(arr, sr + size, sc + size, size);
    }
    
    private static boolean check(int[][] arr, int sr, int sc, int size) {
        boolean flag = true;
        L : for (int r = sr; r < sr + size; r++) {
            for (int c = sc; c < sc + size; c++) {
                if (arr[sr][sc] != arr[r][c]) {
                    flag = false;
                    break L;
                }
            }
        }
        return flag;
    }
}