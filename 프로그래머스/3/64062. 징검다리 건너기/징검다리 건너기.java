class Solution {
    public int solution(int[] stones, int k) {
        int answer = 0;
        int left = 0;
        int right = Integer.MAX_VALUE;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(check(stones, k, mid)) {
                left = mid + 1;
                answer = mid;
            } else right = mid - 1;
        }
        return answer;
    }
    
    public boolean check(int[] stones, int k, int mid) {
        int count = 0;
        for (int stone : stones) {
            if (stone < mid) {
                count++;
                if (count >= k) return false;
            } else count = 0;
        }
        return true;
    }
}