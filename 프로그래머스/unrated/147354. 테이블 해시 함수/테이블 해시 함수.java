import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        Arrays.sort(data, (o1, o2) -> {
            if (o1[col - 1] == o2[col - 1]) return o2[0] - o1[0];
            return o1[col - 1] - o2[col - 1];
        });
        for (int i = 0; i < row_end - row_begin + 1; i++) {
            int idx = row_begin + i;
            int S_i = 0;
            for (int n : data[idx - 1]) S_i += n % idx;
            answer ^= S_i;
        }
        return answer;
    }
}