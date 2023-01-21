class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        String tmp;
        for (int i = 0; i < n; i++) {
            answer[i] = "";
            tmp = Integer.toBinaryString(arr1[i] | arr2[i]);
            if (tmp.length() < n) {
                for (int j = 0; j < n - tmp.length(); j++) {
                    answer[i] += " ";
                }
            }
            answer[i] += tmp;
            answer[i] = answer[i].replaceAll("0", " ");
            answer[i] = answer[i].replaceAll("1", "#");
        }
        return answer;
    }
}