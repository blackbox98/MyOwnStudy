class Solution {
    public int solution(int n) {
        StringBuilder sb = new StringBuilder();
        return Integer.parseInt(sb.append(Integer.toString(n, 3)).reverse().toString(), 3);
    }
}