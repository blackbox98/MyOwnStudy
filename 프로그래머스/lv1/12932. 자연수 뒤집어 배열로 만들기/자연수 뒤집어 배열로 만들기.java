import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
class Solution {
    public int[] solution(long n) {
        StringBuilder sb = new StringBuilder(String.valueOf(n)).reverse();
        char[] arr = sb.toString().toCharArray();
        List<Integer> answer = new ArrayList<>();
        for (char c : arr) {
            answer.add(c - '0');
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}