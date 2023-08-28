import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer, N, M, knowNums;
    static Set<Integer>[] peopleSet;
    static List<Integer>[] partyList;
    static boolean[] knowCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        answer = 0;
        N = Integer.parseInt(st.nextToken());
        peopleSet = new Set[N + 1];
        for (int i = 1; i <= N; i++) {
            peopleSet[i] = new HashSet<>();
        }
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        knowNums = Integer.parseInt(st.nextToken());
        knowCheck = new boolean[N + 1];
        if (knowNums > 0) {
            for (int i = 0; i < knowNums; i++) {
                knowCheck[Integer.parseInt(st.nextToken())] = true;
            }
        }
        partyList = new List[M];
        for (int party = 0; party < M; party++) {
            partyList[party] = new ArrayList<>();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                int person = Integer.parseInt(st.nextToken());
                partyList[party].add(person);
            }
            for (int person : partyList[party]) {
                for (int target : partyList[party]) {
                    if (person == target) continue;
                    peopleSet[person].add(target);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (knowCheck[i] && !peopleSet[i].isEmpty()) bfs(i);
        }
        for (int party = 0; party < M; party++) {
            if (checkParty(party)) answer++;
        }
        System.out.println(answer);
    }

    private static void bfs(int person) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(person);
        boolean[] v = new boolean[N + 1];
        v[person] = true;
        while (!queue.isEmpty()) {
            int p = queue.poll();
            knowCheck[p] = true;
            for (int target : peopleSet[p]) {
                if (v[target]) continue;
                v[target] = true;
                queue.offer(target);
            }
        }
    }

    private static boolean checkParty(int party) {
        for (int person : partyList[party]) {
            if (knowCheck[person]) return false;
        }
        return true;
    }
}