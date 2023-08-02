import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N, M;
	static int[][] arr; //누적 합 2차원 배열
	static int sum; //결과값 담을 변수
	static int x1,y1,x2,y2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder(); //출력 10만개를 담을 버퍼
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N+1][N+1]; //인덱스가 1부터 시작하기 때문에 N+1
		for(int i=1; i<N+1; i++) { //행마다 누적합을 저장
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<N+1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) + sum;
				sum = arr[i][j];
			}
			sum = 0;
		}
		for(int i=0; i<M; i++) { //테스트케이스 M번 반복
			sum = 0;
			st = new StringTokenizer(br.readLine());
			x1 = Integer.parseInt(st.nextToken());
			y1 = Integer.parseInt(st.nextToken());
			x2 = Integer.parseInt(st.nextToken());
			y2 = Integer.parseInt(st.nextToken());
			solve(x1, y1, x2, y2);
			sb.append(sum+"\n"); //결과 저장
		}
		System.out.println(sb);
	}
	static void solve(int x1, int y1, int x2, int y2) {
		for(int j=x1; j<=x2;j++) { //위에서 아래로 sum 합산
			sum += arr[j][y2] - arr[j][y1-1]; //x1부터 y2까지 행마다 누적합을 구하면서 합산
		}
	}
}
