import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, min;
    static int[] res = new int[2];
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) { //입력
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr); //오름차순 정렬

        min = Integer.MAX_VALUE;

        //투 포인터
        int lp = 0;
        int rp = N-1;
        while(lp != N && rp != -1 && lp != rp) { //배열 끝을 탐색하거나 포인터가 동일해질 경우
            long diff = arr[lp] + arr[rp];
            if(Math.abs(diff) < min) { //최소값을 찾을 경우 값 갱신
                res[0] = arr[lp];
                res[1] = arr[rp];
                min = (int) Math.abs(diff);
            }
            if(diff == 0) break; //합이 0일 경우 처리
            else if(diff < 0) lp++; //두 수의 합이 음수일 경우 음수값 증가
            else rp--; //두 수의 합이 양수일 경우 양수값 감소
        }

        System.out.println(res[0] + " " + res[1]);
    }
}
