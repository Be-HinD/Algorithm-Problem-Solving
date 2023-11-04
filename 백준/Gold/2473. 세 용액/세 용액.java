import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static long min;
    static int[] res = new int[3];
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


        //입력이 최대값으로 들어올 수 있으므로 초기값 주의
        min = 3000000001L;

        if(N == 3) { //입력이 3개일 경우
            System.out.println(arr[0] + " " + arr[1] + " " + arr[2]);
        }

        
        //한 개의 수를 정해놓고 나머지를 투 포인터로 찾는 풀이
        //수가 겹치지 않기 위해 i는 N-3까지만
        for(int i=0; i<N-2; i++) {
            twoPointer(i);
        }

        Arrays.sort(res);
        System.out.println(res[0] + " " + res[1] + " " + res[2]);
    }

    private static void twoPointer(int index) {
        int lp = index + 1; //고른 수 뒤부터
        int rp = arr.length - 1; //배열의 끝으로 범위 지정

        while(lp < rp) {
            //처음에 더하는 부분 int인채로 더했다가 계속 39% 틀림...
            //long으로 치환을 왜 해줘야하는지 모르겠음.
            long mid = (long) arr[lp] + (long) arr[index] + (long) arr[rp];

            if(Math.abs(mid) < min) {
                //최소값을 찾은 경우 값 갱신
                min = Math.abs(mid);
                res[0] = arr[lp];
                res[1] = arr[index];
                res[2] = arr[rp];
            }

            if(mid == 0) break;
            else if(mid > 0) rp--;
            else lp++;
        }
    }
}