import java.io.*;
import java.util.*;

//BOJ_17140
class Main {
    static int r, c, k, maxRow, maxCol, res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        /**
         *
         * **/
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[100][100];
        for(int i=0; i<3; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        maxRow = 2; maxCol = 2;
        res = -1;
        for(int i=0; i<101; i++) {  //74% 틀렸습니다 i<100 => i<101로 수정
            if(arr[r][c] == k) {
                res = i;
                break;
            }

            //R 또는 C연산 진행
            if(maxRow >= maxCol) {
                //R연산
                rCalc(arr);
            }
            else {
                //C연산
                cCalc(arr);
            }
        }

        System.out.println(res);
    }
    static void rCalc(int[][] map) {
        int mCol = 0;
        for(int i=0; i<=maxRow; i++) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            for(int j=0; j<=maxCol; j++) {
                if(map[i][j] == 0) continue;
                hm.put(map[i][j], hm.getOrDefault(map[i][j], 0) + 1);
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }));
            for(int key : hm.keySet()) {
                pq.offer(new int[]{key, hm.get(key)});
            }
            int idx = 0;
            while(!pq.isEmpty() && idx < 100) {
                int[] cur = pq.poll();
                map[i][idx++] = cur[0];
                map[i][idx++] = cur[1];
            }
            for(int j=idx; j<100; j++) {
                map[i][j] = 0;
            }
            mCol = Math.max(mCol, idx-1);
        }

        maxCol = mCol;
    }

    static void cCalc(int[][] map) {
        int mRow = 0;
        for(int j=0; j<=maxCol; j++) {
            HashMap<Integer, Integer> hm = new HashMap<>();
            for(int i=0; i<=maxRow; i++) {
                if(map[i][j] == 0) continue;
                hm.put(map[i][j], hm.getOrDefault(map[i][j], 0) + 1);
            }
            PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
                if(o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }));
            for(int key : hm.keySet()) {
                pq.offer(new int[]{key, hm.get(key)});
            }
            int idx = 0;
            while(!pq.isEmpty() && idx < 100) {
                int[] cur = pq.poll();
                map[idx++][j] = cur[0];
                map[idx++][j] = cur[1];
            }
            for(int i=idx; i<100; i++) {
                map[i][j] = 0;
            }
            mRow = Math.max(mRow, idx-1);
        }
        maxRow = mRow;
    }
}