import java.util.*;

class Solution {
    int answer, len;
    String target;
    String[] words;
    
    public int solution(String begin, String target, String[] words) {
        answer = 0;
        boolean flag = false;
        for (String word : words) {
            if (target.equals(word)) {
                flag = true;
                break;
            }
        }
        if (!flag) return answer;
        answer = Integer.MAX_VALUE;
        len = words.length;
        this.target = target;
        this.words = words;
        conversion(new boolean[len], begin, 0);
        return answer;
    }
    
    public void conversion(boolean[] v, String cur, int cnt) {
        if (target.equals(cur)) {
            answer = Math.min(answer, cnt);
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (!v[i] && getDiff(cur, words[i]) == 1) queue.offer(i);
        }
        while (cnt < len && !queue.isEmpty()) {
            int idx = queue.poll();
            v[idx] = true;
            conversion(v, words[idx], cnt + 1);
            v[idx] = true;
        }
    }
    
    public int getDiff(String cur, String word) {
        int cnt = 0;
        for (int i = 0; i < cur.length(); i++) {
            if (cur.charAt(i) != word.charAt(i)) cnt++;
        }
        return cnt;
    }
}