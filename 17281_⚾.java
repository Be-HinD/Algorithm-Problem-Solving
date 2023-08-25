import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, p, Max;
	static int input[][]; //입력 맵
	static int arr[] = new int[] {1,2,3,4,5,6,7,8}; //4번주자를 제외한 8개 번호에 대한 순열을 위한 배열
	static int[] solutionArr = new int[9]; //체크할 타자번호 배열
	static int[] logicArr = new int[8]; //순열위한 배열
	static boolean[] visited; //순열 방문체크용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		input = new int[N][9];
		for(int i=0; i<N; i++) { //입력
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<9; j++) {
				input[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[8];
		todo(0);
		System.out.println(Max); //정답 출력
	}
	
	
	static void todo(int cnt) { //순열 메서드
		if(cnt == 8) {
			//순열완성 타자 순서 정했음.
			int ct= 0;
			for(int i=0; i<8; i++) { //solutionArr : 최종 타자순서
				if(ct == 3) ct++; //4번타자는 고정되있기 때문에 넘어감
				solutionArr[ct++] = logicArr[i];
			}
			int score = Solution(); //로직
			Max = Math.max(Max, score);
		} else {
			for(int i=0; i<8; i++) {
				if(!visited[i]) {
					logicArr[cnt] = arr[i];
					visited[i] = true;
					todo(cnt + 1);
					visited[i] = false;
				}
			}
		}
	}
	
	private static int Solution() {
		int base[] = new int[4]; //1~3루 관리를 위한 배열
		int score = 0; //현재 타자순열에 대한 점수값
		int batter = 0; //현재 타자번호
		for(int i=0; i<N; i++) { //이닝
			int out = 0; //out카운트
			int Next = 0; //3out 체크용
			while(true) { //타자 진행
				int bat = batter % 9;
				int idx = input[i][solutionArr[bat]]; //뭐 쳤는지
				batter += 1;
				switch(idx) { //들어온 입력에 따른 행동
				case 0: //out
					out++;
					if(out == 3) {
						Next = 1; //탈출용 체크
						for(int k=1; k<4; k++) { //초기화
							base[k] = 0;
						}
						break;
					}
					break;
				case 1: //1루타
					if(base[3] == 1) {
						score++;
						base[3] = 0;
					}
					if(base[2] == 1) {
						base[3] = 1;
						base[2] = 0;
					}
					if(base[1] == 1) {
						base[2] = 1;
						base[1] = 0;
					}
					base[1] = 1;
					break;
				case 2: //2루타
					if(base[3] == 1) {
						score++;
						base[3] = 0;
					}
					if(base[2] == 1) {
						score++;
						base[2] = 0;
					}
					if(base[1] == 1) {
						base[3] = 1;
						base[1] = 0;
					}
					base[2] = 1;
					break;
				case 3: //3루타
					if(base[3] == 1) {
						score++;
						base[3] = 0;
					}
					if(base[2] == 1) {
						score++;
						base[2] = 0;
					}
					if(base[1] == 1) {
						score++;
						base[1] = 0;
					}
					base[3] = 1;
					break;
				case 4: //홈런
					for(int k=1; k<4; k++) {
						if(base[k] == 1) {
							score++;
							base[k] = 0;
						}
					}
					score++;
					break;
				}
				if(Next == 1) {
					Next = 0;
					break;
				}
			}
		}
		return score; //결과 리턴
	}
}
