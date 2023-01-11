class Solution {
    public int solution(int a, int b, int n) {
        int answer = 0;
        int newCola;
        while (n >= a) {
            newCola = n / a * b;
            n = n % a + newCola;
            answer += newCola;
        }
        return answer;
    }
}