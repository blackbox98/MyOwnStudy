import java.util.Arrays;
class Solution {
    public long solution(long n) {
        char[] arr =Long.toString(n).toCharArray();
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder(new String(arr));
        return Long.valueOf(sb.reverse().toString());
    }
}