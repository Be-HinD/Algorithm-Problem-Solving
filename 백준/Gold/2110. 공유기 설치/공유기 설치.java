import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int N, M, Max;
	static int[] arr;
	static long Min, ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //집의 개수
		M = Integer.parseInt(st.nextToken()); //공유기 개수
		
		arr = new int[N];
		for(int i=0; i<N; i++) { //배열 초기화
			int idx = Integer.parseInt(br.readLine());
			arr[i] = idx;
			Max = Math.max(Max, idx);
		}
		Arrays.sort(arr);
		BinarySearch();
	}
	private static void BinarySearch() {
		int low = 1;
		int high = Max; // 1부터 집의 최대거리 사이에서 설정
		while(low <= high) {
			final int mid = low + (high-low)/2;
			Min = Integer.MAX_VALUE; //집들 사이의 최소값 비교를 위한 변수 초기화
			int cnt = 1; //제일 처음 집 arr[0]에 공유기를 설치하고 시작
			int value = arr[0]; //value : 거리 비교를 위한 이전 공유기 설치된 집의 위치값
			for(int i=1; i<arr.length; i++) { //1부터 집들과 거리를 비교
				int diff = arr[i] - value; //공유기가 설치된 집과 이후에 나오는 집과의 거리차이
				if(diff >= mid) { //mid만큼 거리가 있을 경우 해당 위치에 설치
					cnt++;
					Min = Math.min(Min, diff);
					value = arr[i];
				}
			}
			if(cnt >= M) { //공유기 설치가 완료 될 경우
				ans = Math.max(ans, Min); //결과값 중 최대값을 갱신
				low = mid + 1; //공유기 설치개수가 만족해도 최대값이 다른 Case가 있을 수 있음
			} else { //설치된 공유기의 개수가 M이 되지 않을 경우
				high = mid - 1;
			}
		}
		System.out.println(ans);
	}
}