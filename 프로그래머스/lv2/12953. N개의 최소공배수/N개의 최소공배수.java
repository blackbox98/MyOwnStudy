class Solution {
    public int solution(int[] arr) {
        if (arr.length == 1) return arr[0];
        int answer = 0;
        int min = arr[0] > arr[1] ? arr[1] : arr[0];
        int gcd = 0;
        for (int j = min; j >= 1; j--) {
            if (arr[0] % j == 0 && arr[1] % j == 0) {
                gcd = j;
                break;
            }
        }
        answer = (arr[0] * arr[1]) / gcd;
        min = answer;
        for (int i = 2; i < arr.length; i++) {
            min = min > arr[i] ? arr[i] : min;
            gcd = 0;
            for (int j = min; j >= 1; j--) {
                if (answer % j == 0 && arr[i] % j == 0) {
                    gcd = j;
                    break;
                }
            }
            answer = (answer * arr[i]) / gcd;
            min = answer;
        }
        return answer;
    }
}