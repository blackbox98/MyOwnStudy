import java.io.*;
import java.util.*;

class Solution {
    int len;
    int[] info;
    List<Integer>[] list;
    
    public int solution(int[] info, int[][] edges) {
        this.info = info;
        len = info.length;
        list = new List[len];
        for (int i = 0; i < len; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < edges.length; i++) {
            list[edges[i][0]].add(edges[i][1]);
        }
        return dfs(list[0], 0, 0, 0);
    }
    
    public int dfs(List<Integer> curList, int cur, int sheep, int wolf) {
        if (info[cur] == 0) sheep++;
        else wolf++;
        int answer = sheep;
        if (sheep <= wolf) return answer;
        for (Integer next : curList) {
            List<Integer> nextList = new ArrayList<>(curList);
            nextList.remove(next);
            nextList.addAll(list[next]);
            answer = Math.max(answer, dfs(nextList, next, sheep, wolf));
        }
        return answer;
    }
}