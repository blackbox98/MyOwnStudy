import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        Map<Integer, Integer> totalTimeMap = new HashMap<>();
        Map<Integer, Integer> lastTimeMap = new HashMap<>();
        Map<Integer, Boolean> stateMap = new HashMap<>();
        for (String record : records) {
            String[] info = record.split(" ");
            String[] tmp = info[0].split(":");
            int time = (Integer.parseInt(tmp[0]) * 60) + Integer.parseInt(tmp[1]);
            int number = Integer.parseInt(info[1]);
            String state = info[2];

            totalTimeMap.put(number, totalTimeMap.getOrDefault(number, 0));
            if (stateMap.getOrDefault(number, false)) {
                int parkingTime = time - lastTimeMap.getOrDefault(number, time);
                totalTimeMap.put(number, totalTimeMap.get(number) + parkingTime);
            }
            lastTimeMap.put(number, time);
            stateMap.put(number, "IN".equals(state));
        }

        int endTime = (23 * 60) + 59;
        List<carInfo> list = new ArrayList<>();
        for (int number : stateMap.keySet()) {
            if (stateMap.get(number)) {
                int parkingTime = endTime - lastTimeMap.get(number);
                totalTimeMap.put(number, totalTimeMap.get(number) + parkingTime);
            }
            list.add(new carInfo(number, totalTimeMap.get(number)));
        }
        Collections.sort(list);

        int defaultTime = fees[0];
        int defaultCharge = fees[1];
        int unitTime = fees[2];
        int unitCharge = fees[3];
        
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            carInfo info = list.get(i);
            int charge = defaultCharge;
            if (info.time > defaultTime) {
                int remainTime = info.time - defaultTime;
                charge += ((remainTime) / unitTime) * unitCharge;
                if (remainTime % unitTime > 0) charge += unitCharge;
            }
            answer[i] = charge;
        }
        return answer;
    }
    
    private static class carInfo implements Comparable<carInfo> {
        int number;
        int time;

        carInfo(int number, int time) {
            this.number = number;
            this.time = time;
        }

        @Override
        public int compareTo(carInfo o) {
            return Integer.compare(this.number, o.number);
        }
    }
}