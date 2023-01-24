class Solution {
    public boolean solution(int x) {
        int sum = 0;
        String s = String.valueOf(x);
        for (int i = 0; i < s.length(); i++) {
            sum += s.charAt(i) - '0';
        }
        return x % sum == 0;
    }
}