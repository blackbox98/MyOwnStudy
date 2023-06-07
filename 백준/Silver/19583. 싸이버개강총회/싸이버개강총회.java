import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1 = new StringTokenizer(br.readLine());
        StringTokenizer st2 = new StringTokenizer(st1.nextToken(), ":");
        int S = Integer.parseInt(st2.nextToken()) * 60 + Integer.parseInt(st2.nextToken());
        st2 = new StringTokenizer(st1.nextToken(), ":");
        int E = Integer.parseInt(st2.nextToken()) * 60 + Integer.parseInt(st2.nextToken());
        st2 = new StringTokenizer(st1.nextToken(), ":");
        int Q = Integer.parseInt(st2.nextToken()) * 60 + Integer.parseInt(st2.nextToken());
        Set<String> beforeCheck = new HashSet<>();
        Set<String> answer = new HashSet<>();
        String s = br.readLine();
        while (s != null && !s.isEmpty()) {
            st1 = new StringTokenizer(s);
            st2 = new StringTokenizer(st1.nextToken(), ":");
            int timeLine = Integer.parseInt(st2.nextToken()) * 60 + Integer.parseInt(st2.nextToken());
            String name = st1.nextToken();
            if (timeLine <= S) beforeCheck.add(name);
            if (timeLine >= E && timeLine <= Q) {
                if (beforeCheck.contains(name)) answer.add(name);
            }
            s = br.readLine();
        }
        System.out.println(answer.size());
    }
}