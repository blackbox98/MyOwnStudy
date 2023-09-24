import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int n, x, y, m, k, curIdx, curX;
        Cafe cafe;
        List<Cafe> list;
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();
            list.add(new Cafe(-1, 0));
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                x = Integer.parseInt(st.nextToken());
                y = Integer.parseInt(st.nextToken());
                list.add(new Cafe(x, y));
            }
            Collections.sort(list);
            int idx = 1;
            while (idx <= n) {
                cafe = list.get(idx);
                if (list.get(idx - 1).x == cafe.x || list.get(idx - 1).y == cafe.y) idx++;
                else {
                    curIdx = idx;
                    curX = list.get(idx).x;
                    while (idx <= n && list.get(idx).x == curX) idx++;
                    list.subList(curIdx, idx).sort(Comparator.reverseOrder());
                }
            }
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            for (int i = 0; i < m; i++) {
                k = Integer.parseInt(st.nextToken());
                cafe = list.get(k);
                System.out.printf("%d %d\n", cafe.x, cafe.y);
            }
        }
    }

    private static class Cafe implements Comparable<Cafe> {
        int x;
        int y;

        private Cafe(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Cafe o) {
            if (this.x == o.x) return this.y - o.y;
            else return this.x - o.x;
        }
    }
}