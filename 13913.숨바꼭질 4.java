import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, K, ans, cnt;
    static int[] v = new int[100055]; //방문 배열
    static int[] vv = new int[100055]; //이동 경로 저장 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        ans = Integer.MAX_VALUE;
        if(N == K) { //수빈이와 동생이 같은 위치에 있을 경우 전처리
            System.out.println(0);
            System.out.println(N);
            return;
        }
        bfs();

        int[] arr = new int[ans+1];
        arr[0] = N; //시작위치
        arr[arr.length-1] = K; //도착위치

        int num = K; //포인터
        for(int i=arr.length-2; i>0; i--) { //vv배열을 이용해서 경로추적 및 arr배열에 값 넣기
            arr[i] = vv[num];
            num = vv[num];
        }
        System.out.println(ans); //걸린 시간
        for(int idx : arr) { //이동 경로 출력
            System.out.print(idx + " ");
        }

    }

    private static void bfs() {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{N, 0});

        while(!queue.isEmpty()) {
            int[] idx = queue.poll();

            if (idx[0] == K) { //동생위치인지 비교
                if (v[idx[0]] == ans) cnt++; //이전 도착지점 도달시간과 같다면 증가
                else cnt = 1; //다르다면 처음부터 다시 카운트
                ans = Math.min(ans, idx[1]); //최소시간 갱신 (cnt갱신 후 갱신해야함)
            }

            if(idx[1] >= ans) continue; //백트래킹 : 구해진 시간을 넘어설 경우 (60ms정도 차이남)

            int point = idx[0] * 2; //순간이동
            if(point>100000 || (v[point] != 0 && v[point] < (idx[1] + 1))); //방문한 적이 있는데 현재 시간이 더 크다면 비교할 필요 없음.
            else {
                v[point] = idx[1] + 1; //방문배열 값 갱신
                vv[point] = idx[0]; //이 전 이동 포인트 저장
                queue.offer(new int[]{point, idx[1] + 1});
            }

            point = idx[0] - 1; //-1
            if(point<0 || point>100000 || (v[point] != 0 && v[point] < (idx[1] + 1)));
            else {
                v[point] = idx[1] + 1;
                vv[point] = idx[0];
                queue.offer(new int[]{point, idx[1] + 1});
            }

            point = idx[0] + 1; //+1
            if(point>100000 || (v[point] != 0 && v[point] < (idx[1] + 1)));
            else {
                v[point] = idx[1] + 1;
                vv[point] = idx[0];
                queue.offer(new int[]{point, idx[1] + 1});
            }
        }
    }
}
