import java.util.*;

class Solution {
    int k, answer;
    List<Counsel>[] counselList;
    
    public class Counsel {
        int start;
        int finish;
        
        public Counsel(int start, int finish) {
            this.start = start;
            this.finish = finish;
        }
    }
    
    public int solution(int k, int n, int[][] reqs) {
        this.k = k;
        answer = Integer.MAX_VALUE;
        counselList = new List[k + 1];
        for (int i = 1; i <= k; i++) {
            counselList[i] = new ArrayList<>();
        }
        for (int[] req : reqs) {
            counselList[req[2]].add(new Counsel(req[0], req[0] + req[1]));
        }
        int[][] timeTable = new int[k + 1][n - k + 2];
        for (int i = 1; i <= k; i++) {
            for (int j = 1; j <= n - k + 1; j++) {
                if (counselList[i].size() == 0) continue;
                timeTable[i][j] = getTime(counselList[i], j);
            }
        }
        int[] counselorArr = new int[k + 1];
        for (int i = 1; i <= k; i++) {
            counselorArr[i]++;
        }
        int cnt = n - k;
        while (cnt-- > 0) {
            int gapMax = 0;
            int plusIdx = -1;
            for (int i = 1; i <= k; i++) {
                if (counselorArr[i] == n - k + 1) continue;
                int gap = Math.abs(timeTable[i][counselorArr[i]] - timeTable[i][counselorArr[i] + 1]);
                if (gapMax < gap) {
                    gapMax = gap;
                    plusIdx = i;
                }
            }
            if (gapMax == 0) break;
            counselorArr[plusIdx]++;
        }
        int answer = 0;
        for (int i = 1; i <= k; i++) {
            answer += timeTable[i][counselorArr[i]];
        }
        return answer;
    }
    
    public int getTime(List<Counsel> list, int Counselors) {
        int result = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Counsel c : list) {
            if (pq.isEmpty() || pq.size() < Counselors) {
                pq.offer(c.finish);
            } else {
                int time = pq.poll();
                if (time <= c.start) {
                    pq.offer(c.finish);
                } else {
                    result += time - c.start;
                    pq.offer(c.finish + (time - c.start));
                }
            }
        }
        return result;
    }
}