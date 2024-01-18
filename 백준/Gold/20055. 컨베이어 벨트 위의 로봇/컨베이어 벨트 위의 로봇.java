import java.io.*;
import java.util.*;

//BOJ_20055 컨베이어 벨트 위의 로봇
public class Main {
    static class belt {
        int life;
        boolean isRide;

        public belt(int life, boolean isRide) {
            this.life = life;
            this.isRide = isRide;
        }
    }
    static int N, K, res, ans;
    static belt[] arr, copy;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        //1. 회전(로봇과 함께)
        //2. 회전 이 후 로봇은 지알아서 다음칸 비었으면 움직임
        //2-1 내구도 남아있어야함


        /*
        * 접근
        * 로봇을 올리는게 먼저
        * */

        arr = new belt[N*2];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N*2; i++) arr[i] = new belt(Integer.parseInt(st.nextToken()), false);

        while(true) {
            ans++;

            //회전
            belt[] copy = new belt[N*2];

            for(int i=0; i<(N*2)-1; i++) {
                copy[i+1] = new belt(arr[i].life, arr[i].isRide);
            }
            copy[0] = new belt(arr[(N*2)-1].life, arr[(N*2)-1].isRide);

//            arr = copy;/
            arr = new belt[N*2];
            for(int i=0; i<N*2; i++) {
                arr[i] = new belt(copy[i].life, copy[i].isRide);
            }

            if(arr[N-1].isRide) arr[N-1].isRide = false;    //절벽 끝 로봇 자살

            //로봇 이동
            for(int i=N-2; i>=0; i--) {
                if(!arr[i].isRide) continue;
                if(arr[i+1].life > 0 && !arr[i+1].isRide) {    //내구도가 남아있는 경우
                    arr[i+1].isRide = true;
                    arr[i+1].life--;
                    if (arr[i + 1].life == 0) {
                        res++;
                    }
                    arr[i].isRide = false;
                }

            }

            if(arr[N-1].isRide) arr[N-1].isRide = false;    //절벽 끝 로봇 자살


            //로봇 탑승
            if(!arr[0].isRide && arr[0].life != 0) {
                arr[0].life--;
                if(arr[0].life == 0) {
                    res++;
                }
                arr[0].isRide = true;
            }

            //검증
            if(res >= K) break;

        }

        System.out.println(ans);
    }
}