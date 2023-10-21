import java.util.*;
class Solution {
    static int[] arr; //조합 배열
    static int N, piro, res; //조합 개수
    static int[][] map;
    static boolean[] v;
    public int solution(int k, int[][] dungeons) {
        map = dungeons;
        for(int i=1; i<=dungeons.length; i++) {
            piro = k;
            arr = new int[i];
            v = new boolean[i];
            Comb(0);
        }
        return res;
    }
    
    private static void Comb(int cnt) {
        if(cnt == arr.length) {
            //조합 완성
            logic();
        } else {
            for(int i=0; i<arr.length; i++) {
                if(v[i]) continue;
                arr[cnt] = i;
                v[i] = true;
                Comb(cnt+1);
                v[i] = false;
            }
        }
    }
    private static void logic() {
        int pirodo = piro;
        int cnt = 0;
        for(int i=0; i<arr.length; i++) {
            int idx = arr[i];
            //idx번째 던전을 탐색
            if(pirodo >= map[idx][0]) { //만족할 때
                pirodo -= map[idx][1];
                cnt++;
            }
        }
        res = Math.max(res, cnt);
    }
}