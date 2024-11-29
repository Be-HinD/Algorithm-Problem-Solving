import java.io.*;
import java.util.*;

//BOJ_30023
class Main {
    static int N, res;
    static char[] arr;
    static Map<Character, Character> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = br.readLine().toCharArray();

        map = new HashMap<>();
        map.put('R', 'G');
        map.put('G', 'B');
        map.put('B', 'R');

        res = Integer.MAX_VALUE;
        char[] temp = arr.clone();
        int cnt = todo(temp, 0);
        if(cnt != -1) res = Math.min(res, cnt);

        temp = arr.clone();
        temp[0] = map.get(temp[0]);
        temp[1] = map.get(temp[1]);
        temp[2] = map.get(temp[2]);
        cnt = todo(temp, 1);
        if(cnt != -1) res = Math.min(res, cnt);

        temp = arr.clone();
        for(int i=0; i<2; i++) {
            temp[0] = map.get(temp[0]);
            temp[1] = map.get(temp[1]);
            temp[2] = map.get(temp[2]);
        }
        cnt = todo(temp, 2);
        if(cnt != -1) res = Math.min(res, cnt);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);

    }
    static int todo(char[] arr, int init) {
        char origin = arr[0];
        int ans = init;
        for(int i=1; i<N-2; i++) {
            char idx = arr[i];
            while (idx != origin) { //다를 경우 앞에 색으로 치환
                arr[i] = map.get(arr[i]);
                arr[i + 1] = map.get(arr[i + 1]);
                arr[i + 2] = map.get(arr[i + 2]);
                idx = arr[i];
                ans++;
            }
        }
        if(origin == arr[N-1] && arr[N-1] == arr[N-2]) return ans;
        return -1;
    }
}