import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skill) {
        int R = board.length;
        int C = board[0].length;
        int[][] map = new int[R][C];
        for (int[] skillInfo : skill) {
            if (skillInfo[0] == 1) skillInfo[5] *= -1;
            map[skillInfo[1]][skillInfo[2]] += skillInfo[5];
            if (skillInfo[3] + 1 < R) map[skillInfo[3] + 1][skillInfo[2]] -= skillInfo[5];
            if (skillInfo[4] + 1 < C) map[skillInfo[1]][skillInfo[4] + 1] -= skillInfo[5];
            if (skillInfo[3] + 1 < R && skillInfo[4] + 1 < C) map[skillInfo[3] + 1][skillInfo[4] + 1] += skillInfo[5];
        }
        for (int r = 0; r < R; r++) {
            for (int c = 1; c < C; c++) {
                map[r][c] += map[r][c - 1];
            }
        }
        for (int c = 0; c < C; c++) {
            for (int r = 1; r < R; r++) {
                map[r][c] += map[r - 1][c];
            }
        }
        int answer = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (board[r][c] + map[r][c] > 0) answer++;
            }
        }
        return answer;
    }
}