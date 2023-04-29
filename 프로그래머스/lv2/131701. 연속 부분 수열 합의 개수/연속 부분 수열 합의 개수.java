import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        Set<Integer> answer = new HashSet<>();
        int size = elements.length;
        int num = 0;
        while (num++ < size) {
            for (int start = 0; start < size; start++) {
                int sum = 0;
                for (int idx = start; idx < start + num; idx++) {
                    sum += elements[idx % size];
                }
                answer.add(sum);
            }
        }
        return answer.size();
    }
}