import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main {
    static int N, W, L, cnt, nowWeight;
    static int[] arr, bridge;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        arr = new int[N];
        bridge = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int idx = 0;
        while (true) {
            cnt++; //시간증가
            if(bridge[0] != 0) { //다리의 맨 앞에 트럭이 있다면 제거 후 비우기
                nowWeight -= bridge[0];
                bridge[0] = 0;
            }
            for(int i=1; i<bridge.length; i++) { //다리위에 트럭이 한칸씩 건너는 로직
                bridge[i - 1] = bridge[i];
                bridge[i] = 0;
            }
            if((nowWeight+arr[idx]) <= L) { //하중보다 낮을 경우에만
                bridge[bridge.length-1] = arr[idx++]; //다리를 타고
                nowWeight += arr[idx-1]; //하중 증가
                if(idx == arr.length) { //다리를 다 올라탔을 경우
                    cnt += W; //마지막 트럭이 건너는 시간 추가 후
                    break; //종료
                }
            }
        }
        System.out.println(cnt);
    }
}
