public class Solution {
    int N, p, q;
    int[][] map;
    
    public long solution(int[][] land, int P, int Q) {
        N = land.length;
        p = P;
        q = Q;
        map = land;
        int left = 300;
        int right = 1;
        for (int[] r : land) {
            for (int block : r) {
                left = Math.min(left, block);
                right = Math.max(right, block);
            }
        }
        return binarySearch(left, right);
    }
    
    private long binarySearch(int left, int right) {
        int mid;
        long leftCost;
        long rightCost;
        long result = 0;
        while(left <= right){
            mid = (left + right)/ 2;
            leftCost = getCost(mid);
            rightCost = getCost(mid + 1);
            if(leftCost > rightCost){
                result = rightCost;
                left = mid + 1;
            }
            else{
                result = leftCost;
                right = mid - 1;
            }
        }
        return result;
    }
    
    private long getCost(int h) {
        long addCnt = 0;
        long removeCnt = 0;
        for (int[] r : map) {
            for (int block : r) {
                if (block < h) addCnt += h - block;
                if (block > h) removeCnt += block - h;
            }
        }
        return addCnt * p + removeCnt * q;
    }
}