import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        List<Integer> lottoList = Arrays.stream(lottos).boxed().collect(Collectors.toList());
        int zeroCnt = (int) lottoList.stream().filter(n -> n == 0).count();
        if (zeroCnt == 6) return new int[]{1, 6};
        List<Integer> winNumList = Arrays.stream(win_nums).boxed().collect(Collectors.toList());
        if (lottoList.containsAll(winNumList)) return new int[]{1, 1};
        lottoList.retainAll(winNumList);
        int similarity = lottoList.size();
        int top = 7 - zeroCnt - similarity;
        int low = 7 - similarity;
        return new int[]{top == 7 ? 6 : top, low == 7 ? 6 : low};
    }
}