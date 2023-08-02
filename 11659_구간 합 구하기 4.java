import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int a, b;
	static StringTokenizer st;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[N+1]; //입력이 1부터 시작하기 때문에 N+1

		int sum =0; //결과값 담을 변수
		for(int i=1; i<N+1; i++) { //1번 인덱스부터 누적합을 배열에 저장
			arr[i] = Integer.parseInt(st.nextToken()) + sum;
			sum = arr[i];
		}

		for(int i=0; i<M;i++) { //3~5까지의 누적합은 누적합 배열에서 arr[5] - arr[2]
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			System.out.println(arr[b] - arr[a-1]);
		}
	}
}
