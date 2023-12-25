import java.io.*;
import java.util.*;

//BOJ_2568 전깃줄 - 2
public class Main {
    static int N, point;
    static int[][] arr;
    static HashMap<Integer, Integer> hs = new HashMap<>();
    static int[] lis, backtrace;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        N = Integer.parseInt(br.readLine()); //수열의 크기

        arr = new int[N][2];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            hs.put(arr[i][0], arr[i][1]);
        }

        //A전봇대를 기준으로 오름차순 정렬
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        lis = new int[N];
        backtrace = new int[N];

        //가긴증부수 탐색
        //lis배열에 담는 값은 b전봇대의 번호
        for(int i=0; i<N; i++) {
            if(lis[point] < arr[i][1]) {
                lis[++point] = arr[i][1];
                backtrace[i] = point; //backtrace에 담기는 값은 i번째에서 들어간 lis의 인덱스 값.
            }
            else {
                int idx = BinarySearch(0, point, arr[i][1]);
                lis[idx] = arr[i][1];
                backtrace[i] = idx;
            }

        }

//        for(int i=0; i<N; i++) System.out.println(Arrays.toString(arr[i]));


        sb.append(N-point).append("\n");

        for(int i=N-1; i>=0; i--) {
            if(backtrace[i] == point) {
                arr[i][0] = -1;
                point--;
            }
        }

        for(int i=0; i<N; i++) {
            if(arr[i][0] != -1) sb.append(arr[i][0]).append("\n");
        }

        System.out.print(sb);

        //수열을 제외한 나머지 값의 오름차순 출력
        //부분수열 중 나머지 B전봇대의 값을 탐색

    }

    private static int BinarySearch(int low, int high, int key) {

        while(low < high) {
            int mid = (low + high) / 2;

            if(lis[mid] < key) {
                low = mid + 1;
            }
            else {
                high = mid;
            }
        }
        return high;
    }
}