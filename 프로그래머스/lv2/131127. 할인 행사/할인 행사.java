import java.util.HashMap;
import java.util.Map;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            String product = want[i];
            int num = number[i];
            map.put(product, num);
        }
        Map<String, Integer> saleMap = new HashMap<>();
        int end;
        for (end = 0; end < 10; end++) {
            String product = discount[end];
            saleMap.put(product, saleMap.getOrDefault(product, 0) + 1);
        }
        if(check(map, saleMap)) answer++;
        int start = 0;
        while (end < discount.length) {
            String removeProd = discount[start++];
            String addProd = discount[end++];
            saleMap.put(removeProd, saleMap.get(removeProd) - 1);
            saleMap.put(addProd, saleMap.getOrDefault(addProd, 0) + 1);
            if(check(map, saleMap)) answer++;
        }
        return answer;
    }
    
    private static boolean check(Map<String, Integer> map, Map<String, Integer> saleMap) {
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            String product = entry.getKey();
            int num = entry.getValue();
            if (saleMap.containsKey(product)) {
                if (saleMap.get(product) != num) return false;
            }
            else return false;
        }
        return true;
    }
}