import java.util.*;
class Solution {
    static List<List<Integer>> list;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        /**
        키워드 추출
        어느 등대에서 출발해도 다른 모든 등대까지 이동가능
        한 뱃길의 양쪽 끝 등대 중 적어도 하나는 켜져 있도록 등대를 켜 두어야 합니다.
        lighthouse의 길이 = n – 1
        **/
        
        /**
        접근법
        리프노드와 연결된 정점들은 불이 반드시 켜져야함.
        매 반복마다 리프노드를 탐색.
        리프노드와 연결된 정점 개수만큼 answer++ 및 차수 갱신
        **/
        
        int[] degree = new int[n+1];
        list = new ArrayList<>();
        for(int i=0; i<=n; i++) list.add(new ArrayList<>());
        //인접리스트 초기화 및 차수배열 초기화
        for(int i=0; i<lighthouse.length; i++) {
            int[] edge = lighthouse[i];
            degree[edge[0]]++;
            degree[edge[1]]++;
            list.get(edge[0]).add(edge[1]);
            list.get(edge[1]).add(edge[0]);
        }
        
        Set<Integer> set = new HashSet<>();
        
        while(true) {
            
            //리프노드와 연결된 정점 탐색
            for(int i=1; i<=n; i++) {
                if(degree[i] == 1) {
                    
                    for(int node : list.get(i)) {
                        if(degree[node] > 0) {
                            set.add(node);
                        }
                    }
                }
            }
            
            if(set.isEmpty()) { //리프노드와 연결된 정점이 없다면.
                break;
            }
            
            for(int idx : set) {
                if(degree[idx] == 0) continue;
                
                answer++;
                degree[idx] = 0;    //이 정점이 리프노드가 됨.
                
                for(int node : list.get(idx)) {
                    degree[node]--;
                }
                
            }
            
            set.clear();
        }
        
        return answer;
    }
}