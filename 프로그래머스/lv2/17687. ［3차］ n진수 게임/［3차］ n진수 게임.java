class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        int idx = 1;
        int num = 0;
        String numToString;
        if (m == p) p = 0;
        L : while (true) {
            numToString = Integer.toString(num++, n).toUpperCase();
            for (int i = 0; i < numToString.length(); i++) {
                if (idx++ % m == p) {
                    answer.append(numToString.charAt(i));
                    if (answer.length() == t) break L;
                }
            }
        }
        return answer.toString();
    }
}