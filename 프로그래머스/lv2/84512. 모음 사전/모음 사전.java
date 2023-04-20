class Solution {
    static char[] dic = {'A', 'E', 'I', 'O', 'U'};
    static int answer = 0;
    static int size = dic.length;
    static int idx = 0;
    static String WORD;
    static boolean find = false;
    
    public int solution(String word) {
        WORD = word;
        findIdx(new StringBuilder());
        return answer;
    }
    
    private static void findIdx(StringBuilder target) {
        if (find) return;
        if (target.toString().equals(WORD)) {
            answer = idx;
            find = true;
            return;
        }
        if (target.length() == size) return;
        
        for (int i = 0; i < size; i++) {
            target.append(dic[i]);
            idx++;
            findIdx(target);
            target.deleteCharAt(target.length() - 1);
        }
    }
}