import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K;
    static int[][] chessBoard;
    static Deque<Piece>[][] pieceDeque;
    static Map<Integer, Piece> map;
    static int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; // 우 좌 상 하

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        chessBoard = new int[N][N];
        pieceDeque = new Deque[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                chessBoard[r][c] = Integer.parseInt(st.nextToken());
                pieceDeque[r][c] = new ArrayDeque<>();
            }
        }
        map = new HashMap<>();
        int r, c, d;
        for (int n = 1; n <= K; n++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            d = Integer.parseInt(st.nextToken()) - 1;
            map.put(n, new Piece(n, r, c, d));
            pieceDeque[r][c].offer(new Piece(n, r, c, d));
        }
        int answer = 0;
        while (++answer < 1000 && playGame()) ;
        System.out.println(answer == 1000 ? -1 : answer);
    }

    private static boolean playGame() {
        int pn, pr, pc, pd, nr, nc;
        Piece piece, target;
        for (int i = 1; i <= K; i++) {
            piece = map.get(i);
            pn = piece.n;
            pr = piece.r;
            pc = piece.c;
            pd = piece.d;
            if (pieceDeque[pr][pc].isEmpty() || pn != pieceDeque[pr][pc].peekFirst().n) continue;
            nr = pr + dir[pd][0];
            nc = pc + dir[pd][1];
            if (check(nr, nc) || chessBoard[nr][nc] == 2) {
                target = pieceDeque[pr][pc].pollFirst();
                target.d = changeDir(target.d);
                pieceDeque[pr][pc].offerFirst(target);
                nr = pr + dir[target.d][0];
                nc = pc + dir[target.d][1];
                if (check(nr, nc) || chessBoard[nr][nc] == 2) continue;
                movePiece(chessBoard[nr][nc], pr, pc, nr, nc);
            } else movePiece(chessBoard[nr][nc], pr, pc, nr, nc);
            if (pieceDeque[nr][nc].size() >= 4) return false;
        }
        return true;
    }

    private static boolean check(int r, int c) {
        return r < 0 || r >= N || c < 0 || c >= N;
    }

    private static int changeDir(int d) {
        switch (d) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            case 3:
                return 2;
        }
        return -1;
    }

    private static void movePiece(int type, int pr, int pc, int nr, int nc) {
        Piece target;
        if (type == 1) {
            while (!pieceDeque[pr][pc].isEmpty()) {
                target = pieceDeque[pr][pc].pollLast();
                target.r = nr;
                target.c = nc;
                map.put(target.n, target);
                pieceDeque[nr][nc].offerLast(target);
            }
        } else {
            while (!pieceDeque[pr][pc].isEmpty()) {
                target = pieceDeque[pr][pc].pollFirst();
                target.r = nr;
                target.c = nc;
                map.put(target.n, target);
                pieceDeque[nr][nc].offerLast(target);
            }
        }
    }

    private static class Piece {
        int n;
        int r;
        int c;
        int d;

        private Piece(int n, int r, int c, int d) {
            this.n = n;
            this.r = r;
            this.c = c;
            this.d = d;
        }
    }
}