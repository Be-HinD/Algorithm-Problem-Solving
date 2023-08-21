import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, Max;
	static int[] arr;
	static long ans;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); //나무의 수
		M = Integer.parseInt(st.nextToken()); //최소 길이 M
		
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for(int i=0; i<N; i++) { //배열 초기화
			int idx = Integer.parseInt(st.nextToken());
			Max = Math.max(Max, idx); //주어진 나무 중 최대 길이를 저장
			arr[i] = idx;
		}

		BinarySearch(); //이분 탐색
	}
	
	private static void BinarySearch() {
		long low = 1;
		long high = Max; // 1 ~ 나무의 최대 높이가 이분탐색할 배열값이 됨
		while(low <= high) {
			final long mid = low + (high-low)/2;
			//mid만큼 전기톱 설정 시 로직
			long cnt = 0; //자른 길이 저장 변수
			for(int idx : arr) {
				long value = idx-mid;
				if(value >= 0) { //자를 수 있을 때만
					cnt += value;
				}
			}
			if(cnt >= M) { //벤 나무의 길이가 최소길이 M을 만족할 때
				low = mid + 1; //특정 길이 M을 만족하는 경우가 있다해도 최대값이 아닐 수 있음
			} else {
				high = mid - 1;
			}
		}
		System.out.println(high); //최종적으로 비교했을 시 cnt>=M을 만족하지 않고 high가 조건에 맞는 위치로 내려오기 때문에 high를 출력
	}
}
