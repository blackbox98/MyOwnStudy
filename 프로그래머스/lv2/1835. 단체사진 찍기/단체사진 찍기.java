class Solution {
    static int answer, N;
    static String[] dataCopy;
    static char[] friends = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    
    public int solution(int n, String[] data) {
        answer = 0;
        N = n;
        dataCopy = data;
        solve(new char[friends.length], new boolean[friends.length], 0);
        return answer;
    }
    
    static void solve(char[] sel, boolean[] v, int k) {
        if (k == sel.length) {
            String result = String.valueOf(sel);
            for (String condition : dataCopy) {
                int dif = Math.abs(result.indexOf(condition.charAt(0)) - result.indexOf(condition.charAt(2))) - 1;
                switch (condition.charAt(3)) {
                    case '=':
                        if (dif != condition.charAt(4) - '0') return;
                        break;
                    case '<':
                        if (dif >= condition.charAt(4) - '0') return;
                        break;
                    case '>':
                        if (dif <= condition.charAt(4) - '0') return;
                        break;
                }
            }
            answer++;
            return;
        }
        for (int i = 0; i < v.length; i++) {
            if (!v[i]) {
                v[i] = true;
                sel[k] = friends[i];
                solve(sel, v, k + 1);
                v[i] = false;
            }
        }
    }
}