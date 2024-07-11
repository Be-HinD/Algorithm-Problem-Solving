import java.io.*;
import java.util.*;
public class Main {
    static int N, L, res;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());   //물 웅덩이 개수
        L = Integer.parseInt(st.nextToken());   //널빤지 길이
        arr = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken()); //시작 위치
            int e = Integer.parseInt(st.nextToken()); //끝 위치
            //시작부분은 인덱스가 겹쳐야함. 끝은 겹치지 않아도 됨.
            arr[i][0] = s;
            arr[i][1] = e;
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int last = 0;
        for(int i=0; i<N; i++) {
            if(arr[i][1] < last) continue;
            if(arr[i][0] < last) {
                int diff = arr[i][1] - last;    //끝나는 지점 - 마지막 공사 지점
                int cnt = diff / L;
                if(diff % L != 0) {
                    cnt++;
                }
                res += cnt;
                last = last + (L * cnt);
            }
            else {
                int diff = arr[i][1] - arr[i][0];
                int cnt = diff / L;
                if(diff % L != 0) {
                    cnt++;
                }
                res += cnt;
                last = arr[i][0] + (L * cnt);
            }
        }
        System.out.println(res);
    }
}