import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        StringBuilder answer = new StringBuilder();
        String[] arr = new String[numbers.length];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = String.valueOf(numbers[i]);
        }
        Arrays.sort(arr, (o1, o2) -> (o2 + o1).compareTo(o1 + o2));
        for (String s : arr) {
            answer.append(s);
        }
        if (answer.charAt(0) == '0') answer = new StringBuilder().append(0);
        return answer.toString();
    }
}