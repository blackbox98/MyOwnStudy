import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] sArr = s.split("},\\{");
        Arrays.sort(sArr, Comparator.comparingInt(String::length));
        int[] answer = new int[sArr.length];
        Set<String> set = new HashSet<>();
        String[][] tuple = new String[sArr.length][];
        for (int i = 0; i < tuple.length; i++) {
            tuple[i] = sArr[i].split(",");
            for (int j = 0; j < tuple[i].length; j++) {
                if (!set.contains(tuple[i][j])) {
                    set.add(tuple[i][j]);
                    answer[i] = Integer.parseInt(tuple[i][j]);
                    break;
                }
            }
        }
        return answer;
    }
}