class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        int size = cards.length;
        for (int i = 0; i < size; i++) {
            boolean[] v = new boolean[size];
            int group1 = check(cards, v, i);
            if (group1 == size) continue;
            int group2 = 0;
            for (int j = 0; j < size; j++) {
                if (v[j]) continue;
                boolean[] copyV = v.clone();
                group2 = Math.max(group2, check(cards, copyV, j));
            }
            answer = Math.max(answer, group1 * group2);
        }
        return answer;
    }
    
    private static int check(int[] cards, boolean[] v, int start) {
        v[start] = true;
        int next = cards[start] - 1;
        int cnt = 1;
        while (!v[next]) {
            v[next] = true;
            next = cards[next] - 1;
            cnt++;
        }
        return cnt;
    }
}