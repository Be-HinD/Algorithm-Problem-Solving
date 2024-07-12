import java.util.*;
class Solution {
    public int solution(int[] picks, String[] minerals) {
        //picks의 입력순서는 다이아, 철, 돌
        /**
        minerals를 5개씩 끊어서 
        **/
        String[][] arr;
        if(minerals.length % 5 == 0) {
            arr = new String[minerals.length/5][5];
        }
        arr = new String[(minerals.length/5 + 1)][5];        
        
        int p = 0;
        int cnt = 0;
        for(int i=0; i<minerals.length; i++) {
            if(i != 0 && i % 5 == 0) p++;
            arr[p][i%5] = minerals[i];
        }

        int total = 0;
        for(int i=0; i<3; i++) total += picks[i];
        //5개씩 나누기 완료
        //다이야는 25, 철은 5, 돌은 1
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        for(int i=0; i<arr.length; i++) {
            if(total == 0) break;
            int sum = 0;
            for(int j=0; j<5; j++) {
                if(arr[i][j] == null) break;
                if(arr[i][j].equals("diamond")) {
                    sum += 25;
                }
                else if(arr[i][j].equals("iron")) {
                    sum += 5;
                }
                else {
                    sum += 1;
                }
            }
            pq.offer(new int[]{i,sum}); //인덱스 - 가중치
            total--;
        }
        
        int res = 0;
        while(picks[0]-- > 0) {
            if(pq.isEmpty()) {
                return res;
            }
            int[] cur = pq.poll();
            for(int i=0; i<5; i++) {
                if(arr[cur[0]][i] == null) break;
                res++;
            }
        }
        
        while(picks[1]-- > 0) {
            if(pq.isEmpty()) {
                return res;
            }
            int[] cur = pq.poll();
            for(int i=0; i<5; i++) {
                if(arr[cur[0]][i] == null) break;
                if(arr[cur[0]][i].equals("diamond")) {
                    res += 5;
                }
                else {
                    res++;
                }
            }
        }
        
        while(picks[2]-- > 0) {
            if(pq.isEmpty()) {
                return res;
            }
            int[] cur = pq.poll();
            for(int i=0; i<5; i++) {
                if(arr[cur[0]][i] == null) break;
                if(arr[cur[0]][i].equals("diamond")) {
                    res += 25;
                }
                else if(arr[cur[0]][i].equals("iron")) {
                    res += 5;
                }
                else res++;
            }
        }
        
        return res;
    }
}