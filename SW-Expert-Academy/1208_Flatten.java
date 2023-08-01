import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] arr;
	static String line;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int tc =1; tc<=10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
	
			st = new StringTokenizer(br.readLine());
			arr = new int[100];
			for(int i=0; i<100; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			System.out.print("#" + tc + " ");
			recursion(0);
		}
	}
	static void recursion(int cnt) { //매개변수로 평탄화 진행회수
		if(cnt == N) { //평탄화 회수가 N에 도달했을 때
			Arrays.sort(arr); //오름차순 정렬
			int result = arr[arr.length-1] - arr[0]; //max-min
			System.out.println(result); //결과 출력
			return;
		} else {
			//평탄화 작업
			Arrays.sort(arr); //오름차순 정렬
			arr[arr.length-1]--; //max값 -1
			arr[0]++; //min값 +1
			recursion(cnt + 1); //재귀호출
		}
	}
}
