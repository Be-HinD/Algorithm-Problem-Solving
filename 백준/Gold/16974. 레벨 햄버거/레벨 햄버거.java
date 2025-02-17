import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	//long자료형을 사용하여 배열을 선언하여 큰 수를 다룰 수 있도록 한것
    static long[] burgerCount, pattyCount;	//각 레벨별로 햄버거와 패티의 개수 저장
    
    static int levelCount;	//입력으로 주어지는 레벨의 수 저장하는 변수
    static long position;	//찾고자 하는 위치를 저장하는 변수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        levelCount = Integer.parseInt(st.nextToken());	//N 버거, 레벨의 수 
        position = Long.parseLong(st.nextToken());		//밑에 X장 먹었다, 위치
		
        //배열 초기화
        burgerCount = new long[levelCount + 1];		//해당 레벨의 햄버거 전체요소(번과 패티)개수
        pattyCount = new long[levelCount + 1];		//해당 레벨까지의 패티 개수

		//초기값으로 첫번째 레벨에서의 햄버거와 패티 수 저장
        burgerCount[0] = 1;
        pattyCount[0] = 1;

		//반복문을 사용하여 각 레벨에서의 햄버거와 패티 수를 계산
        for (int i = 1; i <= levelCount; ++i) {
            burgerCount[i] = 1 + burgerCount[i - 1] + 1 + burgerCount[i - 1] + 1;
            pattyCount[i] = pattyCount[i - 1] + 1 + pattyCount[i - 1];
        }
		//solve 함수 호출하여 주어진 레벨과 위치에 대한 패티개수 계산
        System.out.println(solve(levelCount, position));
    }

	//패티 개수 계산
    private static long solve(int level, long position) {
		//레벨 0 버거 -> 패티 한 장
        if (level == 0) {
            if (position == 0) return 0;
            else if (position == 1) return 1;
        }
		
        //위치가 1인 경우
        if (position == 1) {
            return 0;
        } else if (position <= 1 + burgerCount[level - 1]) {
            return solve(level - 1, position - 1);
        } else if (position == 1 + burgerCount[level - 1] + 1) {
            return pattyCount[level - 1] + 1;
        } else if (position <= 1 + burgerCount[level - 1] + 1 + burgerCount[level - 1]) {
            return pattyCount[level - 1] + 1 + solve(level - 1, position - (1 + burgerCount[level - 1] + 1));
        } else {
            return pattyCount[level - 1] + 1 + pattyCount[level - 1];
        }
    }
}