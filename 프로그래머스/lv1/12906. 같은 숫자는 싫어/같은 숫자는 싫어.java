import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        List<Integer> answer = new ArrayList<>();
        int tmp = -1;
        for (int n : arr) {
            if (n != tmp) {
                answer.add(n);
                tmp = n;
            }
        }
        return answer.stream().mapToInt(n -> n).toArray();
    }
}