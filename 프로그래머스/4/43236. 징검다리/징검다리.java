import java.util.Arrays;

class Solution {
    int distance;
    int[] rocks;
    public int solution(int distance, int[] rocks, int n) {
        this.distance = distance;
        this.rocks = rocks;
        Arrays.sort(rocks);
        int answer = 0;
        int left = 1;
        int right = distance;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (getRemoveRock(mid) <= n) {
                answer = mid;
                left = mid + 1;
            } else right = mid - 1;
        }
        return answer;
    }
    
    public int getRemoveRock(int mid) {
        int cnt = 0;
        int last = 0;
        for (int rock : rocks) {
            if (rock - last < mid) {
                cnt++;
                continue;
            }
            last = rock;
        }
        if (distance - last < mid) cnt++;
        return cnt;
    }
}