import java.util.*;
import java.util.stream.*;
class Solution {
    public List<Integer> solution(int[] numbers) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                list.add(numbers[i] + numbers[j]);
            }
        }
        List<Integer> answer = list.stream().distinct().collect(Collectors.toList());
        Collections.sort(answer);
        return answer;
    }
}