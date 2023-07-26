import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] arr;
	static int next;
	static int idx = 0;
	static int[] ansarr;
	static int cnt = 0;
	static int loop = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		ansarr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		//map 값 삽입
		for(int i=0; i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//동작부
		ansarr[cnt++] = idx+1;
		next = arr[idx]; // 3
		arr[idx] = 0;
		
		///////
		for(int i=0; i<N-1; i++) {
			if(next > 0) { //양수
				for(int j=0; j<next;j++){
					idx++;
					if(idx > N-1) idx = 0;
					while(arr[idx] == 0) {
						idx++;
						if(idx > N-1) idx = 0;
					}
				}
			}
			if(next < 0) { //음수
				for(int j=0; j>next;j--){
					idx--;
					if(idx < 0) idx = arr.length - 1;
					while(arr[idx] == 0) {
						idx--;
						if(idx < 0) idx = arr.length - 1;
					}
				}
			}
			ansarr[cnt++] = idx +1;
			next = arr[idx];
			arr[idx] = 0;
		}
		///////
		
		for(int i=0; i<N;i++) {
			System.out.print(ansarr[i] + " ");
		}
	}
}
