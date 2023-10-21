import java.util.*;
class Solution {
    static int[] arr; //순열 배열
    static int res, initPirodo; //결과값
    static int[][] map; //입력 trans 전역으로
    static boolean[] v; //순열 방문체크
    public int solution(int k, int[][] dungeons) {
        map = dungeons; //입력배열을 전역멤버변수로 변환
        initPirodo = k; //입력 피로도 전역멤버변수로 변환
        Arrays.sort(map, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0]; //최소 필요 피로도 기준 오름차순 정렬
            }
        });
        
        if(k < map[0][0]) return 0; //아무 던전도 들어갈 수 없는 경우
        
        for(int i=0; i<map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        for(int i=1; i<=map.length; i++) { //배열 길이만큼 순열
            arr = new int[i]; //순열 배열 길이 초기화
            v = new boolean[i]; //방문배열 초기화
            Perm(0);
        }
        return res;
    }
    
    private static void Perm(int cnt) {
        if(cnt == arr.length) {
            //조합 완성
            if(res == arr.length) return;
            logic();
        } else {
            for(int i=0; i<arr.length; i++) {
                if(v[i]) continue;
                arr[cnt] = i;
                v[i] = true;
                Perm(cnt+1);
                v[i] = false;
            }
        }
    }
    
    private static void logic() {
        int pirodo = initPirodo;
        int cnt = 0;
        for(int i=0; i<arr.length; i++) {
            int idx = arr[i];
            //idx번째 던전을 탐색
            if(pirodo >= map[idx][0]) { //입장할 수 있을 때
                pirodo -= map[idx][1]; //피로도 감소
                cnt++; //입장 던전 회수 증가
            }
        }
        res = Math.max(res, cnt);
    }
}