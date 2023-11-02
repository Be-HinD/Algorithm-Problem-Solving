import java.util.*;
class Solution {
    static class Info {
        String word;
        int cnt;
        public Info(String word, int cnt) {
            this.word = word;
            this.cnt = cnt;
        }
    }
    static int length, res;
    static List<String> list;
    
    public int solution(String begin, String target, String[] words) {
        
        length = begin.length(); //문자열 길이
        list = Arrays.asList(words); //리스트로 변환
        
        if(!list.contains(target)) return 0; //변환할 수 없는 경우
        
        bfs(begin, target);
        
        return res;
        
    }
    
    private static void bfs(String begin, String target) {
        Queue<Info> q = new ArrayDeque<>();
        q.offer(new Info(begin, 0));
        boolean[] v = new boolean[list.size()];
    
        while(!q.isEmpty()) {
            Info cur = q.poll();
            if(cur.word.equals(target)) {
                res = cur.cnt;
                return;
            }
            
            for(int i=0; i<list.size(); i++) {
                if(v[i]) continue;
                
                String item = list.get(i);
                
                int cnt = 0;
                //1번 조건 체크
                for(int j=0; j<item.length(); j++) {
                    if(cur.word.charAt(j) != item.charAt(j)) {
                        cnt++;
                        if(cnt > 1) break;
                    }
                }
                //1번 조건을 만족할 때만 큐에 추가
                if(cnt < 2) {
                    v[i] = true;
                    q.offer(new Info(item, cur.cnt+1));
                }
            }
        }
    }
}