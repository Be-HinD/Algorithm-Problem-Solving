import java.util.*;
class Solution {
    static boolean flag = false;
    static boolean[] v; 
    static String[] answer;
    static HashSet<String> hs;
    static int point;
    static String[] dist;
    
    public String[] solution(String[][] tickets) {
        //도착지를 기준으로 오름차순 정렬
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
		        return o1[1].compareTo(o2[1]);
	        }
        });
        
        //완탐 : 티켓에 대한 HashSet 추가
        hs = new HashSet<>();
        for(int i=0; i<tickets.length; i++) {
            if(!hs.contains(tickets[i][0])) {
                hs.add(tickets[i][0]);
            }
            if(!hs.contains(tickets[i][1])) {
                hs.add(tickets[i][1]);
            }
        }
        
        v = new boolean[tickets.length]; //방문배열 초기화
        dist = new String[tickets.length+1]; //경로
        dist[0] = "ICN";
        String des = "ICN"; //도착지 초기화
        hs.remove("ICN"); //시작위치 삭제
        dfs(0, tickets, des);
        return dist;
    }
    
    private static void dfs(int p, String[][] t, String des) {
        //기저조건
        if(flag) return;
        if(p == dist.length-1) { //배열이 완성되고
            System.out.println(hs.size());
            if(hs.isEmpty()) { //티켓을 모두 소진할 경우
                flag = true;
                return;
            }
            return;
        }
        //완탐으로 그리디하게 도착지 찾기
        for(int i=0; i<t.length; i++) {
            if(t[i][0].equals(des) && !v[i]) { //도착지이면서 방문하지 않은 곳에 대해
                dist[p+1] = t[i][1]; //탐색 기록
                // System.out.println((p+1) + " " + t[i][1]);
                des = t[i][1]; //도착지 갱신
                v[i] = true; //방문체크
                if(hs.contains(des)) { //hashset에 값이 있을 경우
                    hs.remove(des); //해시셋 삭제
                    dfs(p+1, t, des);
                    if(flag) return;
                    hs.add(des); //백트래킹
                } else { //없을 경우
                    dfs(p+1, t, des);
                    if(flag) return;
                }
                v[i] = false; //백트래킹
                des = t[i][0]; //비교할 도착지 복구
            }
        }
    }
}