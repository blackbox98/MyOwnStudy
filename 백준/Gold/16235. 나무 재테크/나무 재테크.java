import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] land, A;
    static PriorityQueue<Tree> pq;
    /*
    0 1 2
    3 x 4
    5 6 7
     */
    static int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        land = new int[N][N];
        A = new int[N][N];
        for (int r = 0; r < N; r++) {
            Arrays.fill(land[r], 5);
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                A[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        pq = new PriorityQueue<>();
        int r, c, age;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            age = Integer.parseInt(st.nextToken());
            pq.offer(new Tree(r, c, age));
        }
        TreeInvestment(K);
        System.out.println(pq.size());
    }

    private static void TreeInvestment(int K) {
        PriorityQueue<Tree> aliveTree = new PriorityQueue<>();
        Queue<Tree> deathTree = new LinkedList<>();
        Tree tree;
        int tR, tC, tAge, nr, nc;
        while (!pq.isEmpty() && K-- > 0) {
            while (!pq.isEmpty()) {
                tree = pq.poll();
                tR = tree.r;
                tC = tree.c;
                tAge = tree.age;
                if (land[tR][tC] >= tAge) {
                    land[tR][tC] -= tAge;
                    tree.age++;
                    aliveTree.offer(tree);
                } else deathTree.offer(tree);
            }
            while (!deathTree.isEmpty()) {
                tree = deathTree.poll();
                land[tree.r][tree.c] += tree.age / 2;
            }
            while (!aliveTree.isEmpty()) {
                tree = aliveTree.poll();
                pq.offer(tree);
                if (tree.age % 5 == 0) {
                    tR = tree.r;
                    tC = tree.c;
                    for (int[] d : dir) {
                        nr = tR + d[0];
                        nc = tC + d[1];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;
                        pq.offer(new Tree(nr, nc, 1));
                    }
                }
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    land[r][c] += A[r][c];
                }
            }
        }
    }

    private static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        private Tree(int r, int c, int age) {
            this.r = r;
            this.c = c;
            this.age = age;
        }

        @Override
        public int compareTo(Tree o) {
            return this.age - o.age;
        }
    }
}