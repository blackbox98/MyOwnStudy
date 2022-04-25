// import된 class
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Algo1_대전_7_전병찬 { // start class Algo1_대전_7_전병찬

	public static void main(String[] args) throws IOException { // start main
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // BufferedReader를 사용하여 입력값 받기
		StringTokenizer st = new StringTokenizer(br.readLine()); // StringTokenizer를 사용하여 입력값을 공백을 기준으로 자르기
		int start = Integer.parseInt(st.nextToken()); // 시작값 저장
		int end = Integer.parseInt(st.nextToken()); // 끝값 저장
		ArrayList<Word> list = new ArrayList<Word>(); // 입력받은 수와 해당 수를 영어로 바꾼 문자열을 저장하는 ArrayList
		for (int i = start; i <= end; i++) { // start for
			String s = null; // 입력받은 수를 영어로 저장하는 변수
			if (i < 10) { // 입력받은 수가 10 미만인 경우
				s += change(i); // change 메소드 실행
			} else { // 입력받은 수가 10 이상인 경우
				s += change(i / 10); // change 메소드 실행
				s += change(i % 10); // change 메소드 실행
			} // end else
			list.add(new Word(i, s)); // 해당 수와 영어 문자열을 list에 저장
		} // end for
		Collections.sort(list); // 정렬
		for (int i = 0; i < list.size(); i++) { // start for
			System.out.print(list.get(i).num + " "); // 결과 출력
			if (i % 10 == 9) { // 출력 형식을 맞추기 위한 조건 (한 줄에 10개씩 출력)
				System.out.println(); // 개행
			} // end if
		} // end for
	} // end main()

	private static String change(int i) { // 매개변수로 받은 수를 영어로 변환해주는 메소드
		switch (i) { // start switch
		case 0: // 0인 경우
			return "zero "; // 결과 리턴
		case 1: // 1인 경우
			return "one "; // 결과 리턴
		case 2: // 2인 경우
			return "two "; // 결과 리턴
		case 3: // 3인 경우
			return "three "; // 결과 리턴
		case 4: // 4인 경우
			return "four "; // 결과 리턴
		case 5: // 5인 경우
			return "five "; // 결과 리턴
		case 6: // 6인 경우
			return "six  "; // 결과 리턴
		case 7: // 7인 경우
			return "seven "; // 결과 리턴
		case 8: // 8인 경우
			return "eight "; // 결과 리턴
		case 9: // 9인 경우
			return "nine "; // 결과 리턴
		} //  end switch
		return null; // 위의 모든 조건에 해당하지 않는 경우
	} // end change()

	static class Word implements Comparable<Word> { // 입력받은 수와 해당 수의 영어를 저장하는 class
		int num; // 입력받은 수를 저장
		String str; // 입력받은 수의 영어 문자열을 저장

		public Word(int num, String str) { // 생성자
			this.num = num; // 수 저장
			this.str = str; // 영어 문자열 저장
		} // end 생성자

		@Override
		public int compareTo(Word o) { // 정렬을 위한 compareTo
			int min = Math.min(this.str.length(), o.str.length()); // 두 영어 문자열 중 더 짧은 길이를 얻어내기 위한 변수
			for (int i = 0; i < min; i++) { // start for
				int n = Integer.compare(this.str.charAt(i) - '0', o.str.charAt(i) - '0'); // 문자열을 숫자로 바꾸어 사전 순으로 정렬하기 위한 compare
				if(n != 0) { // 위 과정에서의 비교 결과 같은 문자가 아닌 경우
					return n; // 결과 리턴
				} //  end if
			} // end for
			return 0; // 결과 리턴
		} // end compareTo
	} // end Word
} // end Algo1_대전_7_전병찬