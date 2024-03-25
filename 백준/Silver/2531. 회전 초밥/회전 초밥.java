import java.io.*;
import java.util.*;

//BOJ_2531 회전 초밥
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   //접시 수
        int d = Integer.parseInt(st.nextToken());   //초밥 가지 수
        int k = Integer.parseInt(st.nextToken());   //연속해서 먹는 수
        int c = Integer.parseInt(st.nextToken());   //쿠폰 번호

        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());
            arr[i] = idx;
        }

        //구간합 문제 k = 4
        //구간합 중 쿠폰번호가 있는지 체크 = 30
        //쿠폰번호가 등장하는 위치들이 있음
        //쿠폰번호가 등장하지않는 k개의 연속된 수가 있는지 찾는 문제

        //7 9 7 30   ||    2 7 9 25


        //투 포인터 풀이
        int l = 0;
        int r = k-1;
        int sum = 0;
        //최초 한번 초기화
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<k; i++) {
            int idx = arr[i];
            if(map.containsKey(idx)) {
                map.put(idx, map.get(idx) + 1);
            }
            else if(!map.containsKey(idx)) {
                map.put(idx, 1);
                sum++;
            }
        }
        int res = 0;

        if(!map.containsKey(c)) {
            res = sum + 1;
        }
        else {
            res = sum;
        }

        while(true) {
            l++;
            r++;
            if(l == N) break;
            int rIdx = r % N;
            //l 제거
            if(map.get(arr[l-1]) > 1) {
                map.put(arr[l-1], map.get(arr[l-1]) - 1);
            }
            else if(map.get(arr[l-1]) == 1) {
                sum--;
                map.remove(arr[l-1]);
            }

            //r 추가
            if(map.containsKey(arr[rIdx])) {
                map.put(arr[rIdx], map.get(arr[rIdx]) + 1);
            }
            else if(!map.containsKey(arr[rIdx])) {
                sum++;
                map.put(arr[rIdx], 1);
            }

            if(map.containsKey(c)) {
               res = Math.max(res, sum);
            }
            else {
                res = Math.max(res, sum + 1);
            }

        }

        System.out.println(res);



        //아래 풀이는 최대 O(N^2)
//        int res = 0;
//        for(int i=0; i<N; i++) {
//            //i == 시작 번호
//            Set<Integer> set = new HashSet<>();
//            int cnt = 0;
//            for(int j=i; j<i+k; j++) {
//                int idx = j % N;
//                if(!set.contains(arr[idx])) {
//                    set.add(arr[idx]);
//                    cnt++;
//                }
//            }
//            if(!set.contains(c)) cnt++;
//            res = Math.max(res, cnt);
//        }
//
//        System.out.println(res);
    }
}