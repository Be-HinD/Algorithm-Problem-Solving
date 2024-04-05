import java.util.*;
class Solution {
    static class Item {
        int num;
        int cnt;
        public Item(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        } 
    }
    public int[] solution(String[] genres, int[] plays) {
        
        /**
        접근법
        각 장르 별 HashMap으로 전체 재생 수를 기록
        Iter를 통해 제일 많이 재생된 장르를 탐색
        해당 장르에 연결된 재생 수(고유번호 순)로 정렬된 List에서 순차 출력
        
        Map : key(classic), value(1,450)
        List : classic = 3, 0, 2 (고유번호가 값)
        **/
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<genres.length; i++) {
            String type = genres[i];
            int cnt = plays[i];
            if(map.containsKey(type)) {
                map.put(type, map.get(type) + cnt);
            }
            else {
                map.put(type, cnt);
            }
        }
        
        
        Queue<Integer> result = new ArrayDeque<>();
        // 높은 재생 수 장르 탐색
        while(true) {
            String ans = "";
            int temp = 0;
            if(map.isEmpty()) {
                break;
            }
            for(String key : map.keySet()) {

                if(temp < map.get(key)) {
                    temp = map.get(key);
                    ans = key;
                }
            }
            
            map.remove(ans);
           
            PriorityQueue<Item> pq = new PriorityQueue<>(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                if(o1.cnt == o2.cnt) {
                    return o1.num - o2.num;
                }
                return o2.cnt - o1.cnt;
            }
        });
            
            for(int i=0; i<genres.length; i++) {
                if(genres[i].equals(ans)) {
                    pq.offer(new Item(i, plays[i]));
                }
            }
        
            int max = 0;
            while(!pq.isEmpty()) {
                int idx = pq.poll().num;
                result.offer(idx);
                if(++max == 2) {
                    break;
                }
            }
        }
        
        int[] res = new int[result.size()];
        int p = 0;
        while(!result.isEmpty()) {
            res[p++] = result.poll();
        }
        return res;
    }
}