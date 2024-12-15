import java.io.*;
import java.util.*;

//BOJ_20922
class Main {
    static int N, K, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //최장길이부분수열
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> map = new HashMap<>();

        int l=0, r=0, cnt=0;

        while(l<=r && r<N) {
            if(map.get(arr[r]) == null || map.get(arr[r]) < K) {
                map.put(arr[r], map.getOrDefault(arr[r], 0) + 1);
                r++;
                cnt++;
            }
            else {
                res = Math.max(res, r-l);
                for(int i=l; i<r; i++) {
                    map.put(arr[i], map.get(arr[i])-1);
                    if(arr[i] == arr[r]) {
                        l = i+1;
                        break;
                    }
                }
            }
        }
        
        res = Math.max(res, r-l); //42% 처리

        System.out.println(res);

    }
}