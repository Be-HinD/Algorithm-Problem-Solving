import java.util.*;
class Solution {
    static final int DEF_SIZE = 1000055;
    static int maxNode;
    static List<List<Integer>> list;
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        //도넛 모양 그래프 == Cycle
        //막대 모양 그래프 == Cycle없이 모든 정점 순회 가능
        //8자 모양 그래프 == 두 개의 도넛 모양 그래프
        list = new ArrayList<>();
        for(int i=0; i<DEF_SIZE; i++) list.add(new ArrayList<>());
        
        int[] arr = new int[DEF_SIZE]; //진입 차수 계산용
        int[] outArr = new int[DEF_SIZE]; //진출 차수 계산용
        boolean[] v = new boolean[DEF_SIZE];
        for(int i=0; i<edges.length; i++) {
            int[] cur = edges[i];
            maxNode = Math.max(maxNode, cur[0]);
            maxNode = Math.max(maxNode, cur[1]);
            list.get(cur[0]).add(cur[1]);
            v[cur[0]] = true;
            v[cur[1]] = true;
            arr[cur[1]]++;
            outArr[cur[0]]++;
        }
        
        //생성 정점 구하기
        int root = 0;
        for(int i=1; i<=maxNode; i++) {
            if(arr[i] == 0 && outArr[i] >= 2 && v[i]) {
                root = i;
                break;
            }
        }
        
        int sum = 0;
        for(int idx : list.get(root)) {
            sum++;
            arr[idx]--;
        }
        
        for(int i=0; i<DEF_SIZE; i++) {
            if(!v[i] || i == root) continue;
            
            if((arr[i] == 0 && outArr[i] == 0) || (arr[i] == 0 && outArr[i] == 1)) {
                answer[2]++;
            }
            else if(arr[i] == 2 && outArr[i] == 2) {
                answer[3]++;
            }
        }
        answer[1] = sum - (answer[2] + answer[3]);
        
        // for(int start : list.get(root)) {
        //     int cnt = bfs(start);
        //     if(cnt == 1) {
        //         answer[1]++;
        //     }
        //     else if(cnt == 2) {
        //         answer[3]++;
        //     }
        //     else {
        //         answer[2]++;
        //     }
        // }
        
        answer[0] = root;
        return answer;
    }
    
    private static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        boolean[] v = new boolean[DEF_SIZE];
        int line = 0;   //진출 차수 계산
        boolean flag = false;   //Cycle 발생 플래그
        boolean retFlag = false;
        while(!q.isEmpty()) {
            int cur = q.poll();
            
            line = Math.max(line, list.get(cur).size());
            for(int idx : list.get(cur)) {
                if(idx == start) {
                    if(line > 1) return 2;
                    else return 1;
                }
                if(v[idx]) continue;
                //새로운 정점 탐색
                q.offer(idx);
                v[idx] = true;
            }
        }
        return 0;
    }
}