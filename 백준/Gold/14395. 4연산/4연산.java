import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long s = Long.parseLong(st.nextToken());
        long t = Long.parseLong(st.nextToken());
        System.out.println(bfs(s, t));
    }
    
    private static String bfs(long s, long t) {
        if (s == t) return "0";
        Queue<CalcInfo> queue = new LinkedList<>();
        queue.offer(new CalcInfo("", s));
        Set<Long> v = new HashSet<>();
        v.add(s);
        long limit = 1000000001L;
        while (!queue.isEmpty()) {
            CalcInfo calcInfo = queue.poll();
            String calc = calcInfo.calc;
            long num = calcInfo.n;
            if (num == t) {
                return calc;
            }
            if (num * num < limit && !v.contains(num * num)) {
                queue.offer(new CalcInfo(calc + '*', num * num));
                v.add(num * num);
            }
            if (num + num < limit && !v.contains(num + num)) {
                queue.offer(new CalcInfo(calc + '+', num + num));
                v.add(num + num);
            }
            if (!v.contains(0L)) {
                queue.offer(new CalcInfo(calc + '-', 0L));
                v.add(0L);
            }
            if (num != 0 && !v.contains(1L)) {
                queue.offer(new CalcInfo(calc + '/', 1L));
                v.add(1L);
            }
        }
        return "-1";
    }
    
    private static class CalcInfo {
        String calc;
        long n;
        
        private CalcInfo(String calc, long n) {
            this.calc = calc;
            this.n = n;
        }
    }
}