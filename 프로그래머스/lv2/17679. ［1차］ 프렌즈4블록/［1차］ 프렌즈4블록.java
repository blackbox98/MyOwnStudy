import java.util.*;

class Solution {
    static int answer;
    static char[][] map;
    
    public int solution(int m, int n, String[] board) {
        answer = 0;
        map = new char[m][n];
        for (int r = 0; r < map.length; r++) {
            char[] tmp = board[r].toCharArray();
            for (int c = 0; c < map[r].length; c++) {
                map[r][c] = tmp[c];
            }
        }

        while (true) {
            if (checkMap()) {
                deleteBlock();
                cleanMap();
            }
            else break;
        }

        return answer;
    }
    
    private static boolean checkMap() {
        boolean result = false;
        for (int r = 1; r < map.length; r++) {
            for (int c = 1; c < map[r].length; c++) {
                char target = map[r][c];
                if (target != '.' && target == map[r - 1][c - 1] && target == map[r - 1][c] && target == map[r][c - 1]) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    private static void deleteBlock() {
        List<Point> deleteList = new ArrayList<>();
        for (int r = 1; r < map.length; r++) {
            for (int c = 1; c < map[r].length; c++) {
                char target = map[r][c];
                if (target != '.' && target == map[r - 1][c] && target == map[r][c - 1] && target == map[r - 1][c - 1]) {
                    deleteList.add(new Point(r, c));
                    deleteList.add(new Point(r - 1, c));
                    deleteList.add(new Point(r, c - 1));
                    deleteList.add(new Point(r - 1, c - 1));
                }
            }
        }

        for (Point p :
                deleteList) {
            if (map[p.r][p.c] != '.') {
                map[p.r][p.c] = '.';
                answer++;
            }
        }
    }

    private static void cleanMap() {
        List<Character> list;
        int idx;
        for (int c = 0; c < map[0].length; c++) {
            list = new ArrayList<>();
            idx = 0;
            for (int r = map.length - 1; r >= 0; r--) {
                if (map[r][c] != '.') {
                    list.add(map[r][c]);
                    map[r][c] = '.';
                }
            }
            for (int r = map.length - 1; r > map.length - 1 - list.size(); r--) {
                map[r][c] = list.get(idx++);
            }
        }

    }

    private static class Point {
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}