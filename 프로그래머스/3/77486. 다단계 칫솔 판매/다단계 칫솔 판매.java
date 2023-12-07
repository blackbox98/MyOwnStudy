import java.util.*;

class Solution {
    String[] enroll;
    int[] answer;
    Map<String, int[]> map;
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        this.enroll = enroll;
        answer = new int[enroll.length];
        map = new HashMap<>();
        for (int i = 0; i < enroll.length; i++) {
            map.put(enroll[i], new int[]{i, -1});
        }
        for (int i = 0; i < enroll.length; i++) {
            if (referral[i].equals("-")) continue;
            int[] info = map.get(enroll[i]);
            info[1] = map.get(referral[i])[0];
        }
        for (int i = 0; i < seller.length; i++) {
            distribution(seller[i], amount[i] * 100);
        }
        return answer;
    }
    
    public void distribution(String name, double money) {
        int[] info = map.get(name);
        int idx = info[0];
        int next = info[1];
        int profit = (int) (money * 0.1);
        if (profit < 1) {
            answer[idx] += (int) money;
            return;
        } else answer[idx] += (int) money - profit;
        if (next == -1) return;
        distribution(enroll[next], profit);
    }
}