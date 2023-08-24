import java.io.*;
import java.util.*;

public class Main {
    static int N, d, k, c;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken())-1; //인덱스 0번부터 사용하기위해서서
        arr = new int[N];
        int[] eat = new int[d];	// 해당 종류의 초밥을 몇개 먹었는지 저장하는 배열

        for(int i =0 ; i < N ; i++){ //초밥 입력력
            arr[i] = Integer.parseInt(br.readLine())-1;
        }
        int res = 0; //결과값
        int cnt = 0; //먹은 초밥의 종류개수

        for(int i =0 ; i < k ; i++){ //초기 4개의 초밥 갱신신
            if(eat[arr[i]]++ == 0) cnt++;
        }

        for(int i =0 ; i < N ; i++){ //슬라이딩 윈도우 시작

            if(res <= cnt){     // MAX 값 새로 갱신
                if(eat[c] == 0) res = cnt+1; //쿠폰초밥을 안먹은 경우
                else res = cnt; //쿠폰초밥을 먹은 경우
            }
            int j = (i+k)%N;  // start 및 end 갱신
            if(eat[arr[j]] ++ == 0) cnt++;
            if(-- eat[arr[i]] == 0) cnt--;
        }
        System.out.println(res);
    }
}
