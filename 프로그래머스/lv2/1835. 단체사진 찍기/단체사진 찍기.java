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
            StringBuilder result = new StringBuilder(new String(sel));
            boolean flag = true;
            for (String condition : dataCopy) {
                if (!flag) break;
                int dif = Math.abs(result.indexOf(condition.substring(0, 1)) - result.indexOf(condition.substring(2, 3))) - 1;
                switch (condition.charAt(3)) {
                    case '=':
                        if (dif != condition.charAt(4) - '0') flag = false;
                        break;
                    case '<':
                        if (dif >= condition.charAt(4) - '0') flag = false;
                        break;
                    case '>':
                        if (dif <= condition.charAt(4) - '0') flag = false;
                        break;
                }
            }
            if (flag) answer++;
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