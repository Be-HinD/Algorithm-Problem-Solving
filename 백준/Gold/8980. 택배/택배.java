import java.io.*;
import java.util.*;

//BOJ_8980 택배
public class Main {
    static int M, w;
    static int[][] box;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());    //보내는 마을 수
        w = Integer.parseInt(st.nextToken());    //무게

        int N = Integer.parseInt(br.readLine());

        /*
        * 접근법
        * 순차적으로 도착한 마을에서 최대한의 택배 박스만큼 싣고 차례로 나눠준다.
        * 3차원 배열로 입력을 받고, 택배를 보내는 마을기준으로 정렬, 같다면 받는 마을 기준 오름차순
        * */

        /*
        * 위 접근대로 갈 시에 1번에서 N번까지 최대무게를 싣을 경우 정해가 아니게 됨.
        *
        * */

        box = new int[N][3];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            box[i][0] = Integer.parseInt(st.nextToken());   //보내는 마을번호
            box[i][1] = Integer.parseInt(st.nextToken());   //받는 마을번호
            box[i][2] = Integer.parseInt(st.nextToken());   //보내는 박스개수
        }

        Arrays.sort(box, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] == o2[1]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            }
        });

//        for(int i=0; i<N; i++) {
//            System.out.println(box[i][0] + " : " + box[i][1] + " : " + box[i][2]);
//        }

        //받을 곳을 기준으로 정렬, 만약 내리는 곳이 같다면 태우는곳을 기준으로 내림차순!
        int[] arr = new int[M+1]; //마을마다 내려야 하는 물건들
        Arrays.fill(arr, w);

        int res = 0;
        for(int i=0; i<N; i++) {
            if(arr[box[i][0]] > 0) {
                int min =Integer.MAX_VALUE;
                for(int j=box[i][0]; j<box[i][1]; j++) {
                    min = Math.min(min, arr[j]);
                }
                if(min >= box[i][2]) min = box[i][2];
                for(int j=box[i][0]; j<box[i][1]; j++) {
                    arr[j] -= min;
                }
                res += min;
            }
        }

        System.out.println(res);


    }
}