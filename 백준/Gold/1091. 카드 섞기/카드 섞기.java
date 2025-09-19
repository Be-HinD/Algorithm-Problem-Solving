import java.io.*;
import java.util.*;

//BOJ_1091
public class Main {
    static int n,res;
    static int[] origin, arr, p, s;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * 해가 나오지 않는 경우 ? -> 초기 입력값과 섞은 값 비교해서 동일하면 -1
         * 1. P대로 줄 수 있는지 확인
         * 2. 못주면 섞고 1번부터 반복
         * **/

        n = Integer.parseInt(br.readLine());

        origin = new int[n];
        arr = new int[n];
        for(int i=0; i<n; i++) {
            origin[i] = i; arr[i] = i;
        }

        p = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
        }

        s = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }

        while(true) {
            if(check()) break;

            shuffle();

            if(!isPossible()) {
                res = -1;
                break;
            }

            res++;
        }

        System.out.println(res);
    }

    static boolean isPossible() {
        // 원본 배열과 같은지 비교

        for(int i=0; i<n; i++) {
            if(origin[i] != arr[i]) return true;
        }

        return false;
    }

    static void shuffle() {
        //arr 배열 섞기
        int[] temp = new int[n];

        for(int i=0; i<n; i++) {
            temp[s[i]] = arr[i];
        }

        arr = temp;
    }

    static boolean check() {

        /**
         * 각 사람이 최종적으로 받을 수 있는 카드들을 정리
         * 0인 사람 : 0, 3, 6 ....
         * 1인 사람 : 1, 4, 7 ....
         * 2인 사람 : 2, 5, 8 ....
         * **/
        List<List<Integer>> list = new ArrayList<>();
        for(int i=0; i<3; i++) list.add(new ArrayList<>());

        for(int i=0; i<n; i++) {
            if(i % 3 == 0) {
                list.get(0).add(arr[i]);
            }
            else if(i % 3 == 1) {
                list.get(1).add(arr[i]);
            }
            else {
                list.get(2).add(arr[i]);
            }
        }

        //
        for(int i=0; i<n; i++) {
            if(!list.get(p[i]).contains(origin[i])) return false;
        }

        return true;
    }
}