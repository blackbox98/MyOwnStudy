import java.util.*;
import java.util.stream.*;
class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        Arrays.sort(lost);
        List<Integer> lostList = Arrays.stream(lost).boxed().collect(Collectors.toList());
        List<Integer> reserveList = new ArrayList<>();
        for (int i : reserve) {
            if (lostList.contains(i)) lostList.remove(lostList.indexOf(i));
            else reserveList.add(i);
        }
        int answer = n - lostList.size();
        for (int i : lostList) {
            if (reserveList.contains(i - 1)) {
                reserveList.remove(reserveList.indexOf(i - 1));
                answer++;
            }else if (reserveList.contains(i + 1)) {
                reserveList.remove(reserveList.indexOf(i + 1));
                answer++;
            }
        }
        return answer;
    }
}