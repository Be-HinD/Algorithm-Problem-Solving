import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int tc, N;
	static int[] dx = new int[] {0, 1, 0, -1}; //방향벡터
	static int[] dy = new int[] {1, 0, -1, 0}; //방향벡터
	static int[][] map; 
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		tc = Integer.parseInt(st.nextToken());
		for(int i=0; i<tc; i++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			System.out.printf("#%d", i+1);
			System.out.println();
			int dist = 0; //방향벡터 인덱스
			int r =0; //좌표값
			int c =0; //좌표값
			map = new int[N][N]; //2차원 배열 초기화
			for(int j=1; j<=N*N; j++) { //1부터 N*N까지 값 대입을 위한 반복
				map[r][c] = j; //현재 좌표에 값 넣어줌
				r+=dx[dist]; //방향벡터로 이동
				c+=dy[dist]; //방향벡터로 이동
				if(r >= N || r<0 || c >= N || c<0 || map[r][c] != 0) { //null체크 및 다음 이동할 곳이 값이 이미 들어간 경우
					r-=dx[dist]; //이동취소
					c-=dy[dist]; //이동취소
					dist = (dist + 1) % 4; //방향벡터 조정
					r+=dx[dist]; //방향벡터로 재이동
					c+=dy[dist]; //방향벡터로 재이동
				}
			}
			//결과값 출력
			for(int ar=0;ar<N;ar++) {
				for(int ac=0; ac<N;ac++) {
					System.out.print(map[ar][ac] + " ");
				}
				System.out.println();
			}
		}
	}
}
