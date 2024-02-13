import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static boolean isFinish;
    static int n;
    static Room[] rooms;

    private static class Room {
        char type;
        int cost;
        List<Integer> list;

        private Room(char type, int cost) {
            this.type = type;
            this.cost = cost;
            this.list = new ArrayList<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        String s;
        while ((s = br.readLine()) != null && !s.equals("0")) {
            isFinish = false;
            n = Integer.parseInt(s);
            rooms = new Room[n + 1];
            for (int i = 1; i <= n; i++) {
                String[] tmp = br.readLine().split(" ");
                char type = tmp[0].charAt(0);
                int cost = Integer.parseInt(tmp[1]);
                rooms[i] = new Room(type, type == 'T' ? -cost : cost);
                for (int j = 2; j < tmp.length - 1; j++) {
                    rooms[i].list.add(Integer.parseInt(tmp[j]));
                }
            }
            dfs(new boolean[n + 1], 1, 0);
            answer.append(isFinish ? "Yes" : "No").append("\n");
        }
        System.out.println(answer);
    }

    private static void dfs(boolean[] v, int cur, int cost) {
        if (isFinish) return;
        if (cur == n) {
            isFinish = true;
            return;
        }
        for (int next : rooms[cur].list) {
            if (v[next]) continue;
            int nextCost = cost;
            if (rooms[next].type == 'L') nextCost = Math.max(cost, rooms[next].cost);
            else nextCost += rooms[next].cost;
            if (nextCost < 0) continue;
            v[next] = true;
            dfs(v, next, nextCost);
            v[next] = false;
        }
    }
}