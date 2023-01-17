import java.util.*;
class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Map<Integer, Double> failure = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            failure.put(i, 0.0);
        }
        Arrays.sort(stages);
        for (int i = 1; i <= N; i++) {
            int cnt = 0;
            int reached = 0;
            for (int j = stages.length - 1 ; j >= 0; j--) {
                if (i > stages[j]) break;
                if (i <= stages[j]){
                    reached++;
                    if (i == stages[j]) cnt++;
                }
            }
            if (reached == 0) {
                failure.put(i, (double) 0);
                continue;
            }
            failure.put(i, (double) cnt / reached);
        }
        List<Integer> list = new ArrayList<>(failure.keySet());
        list.sort((o1, o2) -> failure.get(o2).compareTo(failure.get(o1)));
        for (int i = 0; i < N; i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}