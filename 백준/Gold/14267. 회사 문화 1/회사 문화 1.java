import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] employees;
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        employees = new List[n];
        answer = new int[n];
        for (int i = 0; i < n; i++) {
            employees[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int idx = Integer.parseInt(st.nextToken());
            if (idx == -1) continue;
            employees[idx - 1].add(i);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int compliment = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            answer[compliment] += w;

        }
        dfs(0);
        for (int score : answer) {
            System.out.print(score + " ");
        }
    }

    private static void dfs(int parent) {
        for (int child : employees[parent]) {
            answer[child] += answer[parent];
            dfs(child);
        }
    }
}