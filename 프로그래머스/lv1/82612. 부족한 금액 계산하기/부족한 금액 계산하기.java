class Solution {
    public long solution(int price, int money, int count) {
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += i;
        }
        long answer = money - ((long) price * sum);
        return answer >= 0 ? 0 : answer * -1;
    }
}