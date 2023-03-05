class Solution {
    public int[] solution(String[] wallpaper) {
        int lux = wallpaper.length;
        int luy = wallpaper[0].length();
        int rdx = 0;
        int rdy = 0;
        for (int r = 0; r < wallpaper.length; r++) {
            for (int c = 0; c < wallpaper[0].length(); c++) {
                if (wallpaper[r].charAt(c) == '#') {
                    lux = lux > r ? r : lux;
                    luy = luy > c ? c : luy;
                    rdx = rdx < (r + 1) ? (r + 1) : rdx;
                    rdy = rdy < (c + 1) ? (c + 1) : rdy;
                }
            }
        }
        return new int[]{lux, luy, rdx, rdy};
    }
}