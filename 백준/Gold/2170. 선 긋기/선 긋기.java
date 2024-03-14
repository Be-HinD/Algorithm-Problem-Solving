import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

//BOJ_2170 선 긋기
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        //2 3
        //4 5
        //1 6

        //1 6
        //2 3
        //4 5

        int x = arr[0][0];
        int y = arr[0][1];
        int res = y - x;
        for(int i=1; i<N; i++) {
            int start = arr[i][0];
            int end = arr[i][1];
            if(start >= x && end <= y) {
                //선분이 겹칠 경우 무시
                continue;
            }

            if(start <= y && end > y) {
                //더 이어질 경우
                res += end - y;
                y = end;
                continue;
            }

            if(start > y) {
                //선분이 이어지지 않고 뒤쪽에 있는 경우
                res += end - start;
                x = start;
                y = end;
            }
        }

        System.out.println(res);

    }
}