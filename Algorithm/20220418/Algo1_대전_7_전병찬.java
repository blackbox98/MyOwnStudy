import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Algo1_대전_7_전병찬 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // bufferedReader를 통해 값을 받아옴
		int T = Integer.parseInt(br.readLine()); // 테스트케이스의 수 입력
		for (int tc = 1; tc <= T; tc++) { // 테스트케이스만큼 반복
			int[] map = new int[10]; // 경주장 배열 생성
			StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer를 사용해 입력 값을 공백 단위로 잘라서 받음
			for (int i = 0; i < map.length; i++) { // map의 크기만큼 반복
				map[i] = Integer.parseInt(st.nextToken()); // map 정보 채우기
			} // end for
			ArrayList<rabbit> list = new ArrayList<>(); // 각 토끼의 오르막/내리막 점프 한계치를 저장하기 위한 ArrayList
			for (int i = 0; i < 5; i++) { // 토끼의 수만큼 반복
				st = new StringTokenizer(br.readLine()); // 입력값을 공백 단위로 자르기
				list.add(new rabbit(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))); // 각 토끼의 정보 저장
			} // end for
			int ans = 0; // 결과값을 저장하기 위한 변수 선언 및 초기화
			for (int i = 0; i < list.size(); i++) { // 토끼의 수만큼 반복
				for (int j = 0; j < map.length - 1; j++) { // map - 1만큼 반복
					if(map[j] > map[j + 1]) { // 내리막길인 경우
						if(list.get(i).row < (map[j] - map[j + 1])) { // 해당 토끼의 내리막 점프 한계치를 초과한 경우
							break; // 더 이상 진행할 수 없으므로 완주 불가 => 반복문 탈출
						} //  end if
					} else if (map[j] < map[j + 1]) { // 오르막길인 경우
						if(list.get(i).high < (map[j + 1] - map[j])) { // 해당 토끼의 오르막 점프 한계치를 초과한 경우
							break;// 더 이상 진행할 수 없으므로 완주 불가 => 반복문 탈출
						} // end if
					} // end if
					if (j == map.length - 2) { // 완주에 성공한 경우
						ans++; // 완주한 토끼 추가
					} // end if
				} // end for
			} // end for
			System.out.printf("#%d %d\n", tc, ans); // 결과 출력
		} // end for
	}
	
	static class rabbit { // 각 토끼의 오르막/내리막 점프 한계치를 저장하기 위한 rabbit class
		int high, row; // 오르막/내리막 점프 한계치를 저장하는 변수 선언

		public rabbit(int high, int row) { // 생성자 정의
			this.high = high; // 오르막 점프 한계치를 저장
			this.row = row; // 내리막 점프 한계치를 저장
		} // 생성자 정의 완료
	} // rabbit class 정의 완료
}