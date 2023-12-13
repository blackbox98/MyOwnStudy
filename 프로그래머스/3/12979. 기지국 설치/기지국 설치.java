class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int start = 1;
        int len = w * 2 + 1;
        for (int station : stations) {
            if (station - w - start > 0) answer += ((station - w - start - 1) / len) + 1;
            start = station + w + 1;
        }
        if (n - (stations[stations.length - 1] + w) > 0) answer += ((n - (stations[stations.length - 1] + w) - 1) / len) + 1;
        return answer;
    }
}