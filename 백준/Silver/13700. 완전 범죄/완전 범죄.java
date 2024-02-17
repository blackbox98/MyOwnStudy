import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Set<Integer> office = new HashSet<>();
    static int N, S, D, F, B, K;
    static boolean[] visited;
    
    private static class Node {
        // 위치와 이동횟수를 담을 변수
        int index;
        int count;

        private Node(int index, int count) {
            this.index = index;
            this.count = count;
        }

        private int getIndex() {
            return index;
        }

        private int getCount() {
            return count;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(br.readLine());

        // 마포구 건물의 개수
        N = Integer.parseInt(stringTokenizer.nextToken());

        // 털린 금은방(시작 위치)
        S = Integer.parseInt(stringTokenizer.nextToken());

        // 대도 x의 집(목표 위치)
        D = Integer.parseInt(stringTokenizer.nextToken());

        // 앞으로 한번에 달릴 수 있는 건물 수
        F = Integer.parseInt(stringTokenizer.nextToken());

        // 뒤로 한번에 달릴 수 있는 건물 수
        B = Integer.parseInt(stringTokenizer.nextToken());

        // 경찰서의 개수
        K = Integer.parseInt(stringTokenizer.nextToken());

        visited = new boolean[N + 1];

        // k가 0보다 크면 경찰서에 대한 정보를 담음
        if (K > 0) {
            stringTokenizer = new StringTokenizer(br.readLine());
            while (stringTokenizer.hasMoreTokens()) {
                office.add(Integer.valueOf(stringTokenizer.nextToken()));
            }
        }

        bfs(new Node(S, 0));

    }

    static void bfs(Node node) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        // 정답을 담을 변수
        int answer = 0;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.getIndex() == D) {
                answer = currentNode.getCount();
                break;
            }
            // 현위치
            int x = currentNode.getIndex();

            visited[x] = true;

            int frontMoved = x + F;
            int backMoved = x - B;

            // 이동했을 때 경찰서인지 검사
            boolean frontMoveIsPolice = office.contains(frontMoved);
            boolean backMoveIsPolice = office.contains(backMoved);

            // frontMoveIsPolice 값
            // true ==  앞으로 갔을 때 경찰서가 있음, false == 앞으로 갔을 때 경찰서가 없음
            if (!frontMoveIsPolice && frontMoved <= N && !visited[frontMoved]) {
                visited[frontMoved] = true;
                queue.add(new Node(frontMoved, currentNode.getCount() + 1));
            }
			
            if (!backMoveIsPolice && backMoved >= 1 && !visited[backMoved]) {
                visited[backMoved] = true;
                queue.add(new Node(backMoved, currentNode.getCount() + 1));
            }
        }
        System.out.println(answer == 0 ? "BUG FOUND" : answer);
    }
}