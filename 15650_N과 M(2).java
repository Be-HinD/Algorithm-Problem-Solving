import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] arr; //출력값을 위한 배열
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[M];
		recursion(0, 1); //재귀함수 호출
	}
	
	static void recursion(int cnt, int start) {
		if(cnt == M) { //M번 수를 선택했다면
			for(int i=0; i<arr.length;i++) { //arr 값 출력
				System.out.print(arr[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i=start; i<=N;i++) { //중복없는 수열을 위해 앞선 번호보다 1높은 인덱스부터 탐색
			arr[cnt] = i;
			recursion(cnt +1, i +1); //자리 수 1증가 및 인덱스 1증
		}
	}
}
