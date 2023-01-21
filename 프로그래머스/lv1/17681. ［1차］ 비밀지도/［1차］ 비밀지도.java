class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String s, tmp;
        for (int i = 0; i < n; i++) {
            s = Integer.toBinaryString(arr1[i] | arr2[i]);
            tmp = "";
            if (s.length() != n) {
                for (int j = 0; j < n - s.length(); j++) {
                    tmp += " ";
                }
            }
            for (int j = 0; j < s.length(); j++) {
                if (s.charAt(j) == '0') tmp += " ";
                else tmp += "#";
            }
            answer[i] = tmp;
        }
        return answer;
    }
}