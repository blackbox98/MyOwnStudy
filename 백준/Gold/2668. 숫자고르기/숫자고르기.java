import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    static int[] nums;
    static boolean[] v;
    static List<Integer> list;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine()) - 1;
        }
        v = new boolean[N];
        list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            v[i] = true;
            dfs(i, i);
            v[i] = false;
        }
        Collections.sort(list);
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        for (int n : list) sb.append(n + 1).append("\n");
        System.out.println(sb);
    }
    
    private static void dfs(int s, int e) {
        if (!v[nums[s]]) {
            v[nums[s]] = true;
            dfs(nums[s], e);
            v[nums[s]] = false;
        }
        if (nums[s] == e) list.add(e);
    }
}