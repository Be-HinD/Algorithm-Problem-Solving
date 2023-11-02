import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//BOJ_13418 학교 탐방하기
public class Main {
    static int T, N, res;
    static boolean[] v, curv;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) { //TestCase
            N = Integer.parseInt(br.readLine()); //정점 개수
            res = 0;
            arr = new int[N];
            v = new boolean[N];
            curv = new boolean[N];

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N; i++) {
                int end = Integer.parseInt(st.nextToken()) - 1;
                //단방향처럼 문제 설명했는데 양방향임.
                arr[i] = end;
                if(i == end) { //솔로팀 완성
                    v[i] = true;
                    res++;
                }
            }

            for(int i=0; i<N; i++) {
                if(!v[i]) {
                    dfs(i);
                }
            }
            sb.append(N-res).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int cur) {
        //방문체크가 된 인원에 대해
        if(curv[cur]) {
            v[cur] = true;
            res++;
        } else {
            //방문체크가 되지 않은 인원에 대해
            curv[cur] = true;
        }

        int next = arr[cur]; //다음 학생
        if(!v[next]) {
            dfs(next);
        }

        curv[cur] = false;
        v[cur] = true;
    }
}