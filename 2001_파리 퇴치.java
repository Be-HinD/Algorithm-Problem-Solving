import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

    static int tc;
    static int N,M;
    static int[][] map;
    static int ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        tc = Integer.parseInt(st.nextToken());

        for(int i=0; i<tc;i++) { //testCase 반복
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //map 크기
            M = Integer.parseInt(st.nextToken()); //파리채 크기

            //map 입력
            map = new int[N][N];
            for(int k=0; k<N; k++) {
                st = new StringTokenizer(br.readLine());
                for(int l=0; l<N; l++) {
                    map[k][l] = Integer.parseInt(st.nextToken());
                }
            }
            ans = 0; //결과값
            int sum = 0; //중간 합
            //4중 for을 통해서 map[k][l] ~ map[k+M][l+M] 까지의 합을 구하고 결과값과 비교
            for(int k=0; k<=N-M; k++) {
                for(int l=0; l<=N-M; l++) {
                    for(int t=0; t<M; t++) {
                        for(int r=0; r<M; r++) {
                            sum += map[k+t][l+r];
                        }
                    }
                    ans = Math.max(ans, sum);
                    sum =0;
                }
            }
            System.out.printf("#%d %d",i+1, ans);
            System.out.println();

        }

    }
}
