import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "";
        
        /**
        TreeMap으로 결과값 출력 // 재생시간(Integer), 음악 이름(String)
        각 곡마다 재생시간 기준으로 String 이어 붙이기로 하나의 악보 완성 = 나머지 연산으로 대체 가능할 듯
        m과 악보 비교 -> 그리디 : m의 0번과 악보의 0~max까지 일일이 비교.
        그리디는 2억 넘어가는 연산 횟수.
        백트래킹 요소 : m의 길이보다 재생시간이 짧다면 false
        일단 백트래킹 하나에 그리디로 풀이 ㄱ : 시간초과 x
        아 뒤에 #이 ㅋㅋㅋㅋㅋ xx이네 ㅋㅋ 
        **/
        
        TreeMap<Integer, String> result = new TreeMap<>((a, b) -> b - a); //재생시간 내림차순
        
        for(int i=0; i<musicinfos.length; i++) { //최대 100이하
            String cur = musicinfos[i];
            String[] info = cur.split(",");
            
            int diffTime = calcMiniutes(info[0], info[1]); //분 단위 == 음표 개수
            int inputLength = m.length(); //음표 개수
            for(int j=0; j<m.length(); j++) {
                if(m.charAt(j) == '#') inputLength--;
            }
            
            //step 1. 후보 악보의 전체 시간 음표 개수 탐색
            
            String acbo = "";
            
            int po = 0;
            int sp = 0;
            for(int j=0; j<diffTime; j++) {
                int idx = sp++ % info[3].length();
                acbo += info[3].charAt(idx);
                if(info[3].charAt(idx) == '#') {
                    j--;    //현재 값이 정식음표가 아니기 때문에 한 개 더 추가
                }
            }
            int idx = sp % info[3].length();
            if(info[3].charAt(idx) == '#') acbo += '#';
            
            if(inputLength > diffTime) continue; //백트
            
            
            for(int j=0; j<acbo.length(); j++) {
                if(m.charAt(0) != acbo.charAt(j)) continue;
                
                if(m.length() == 1) {
                    if(result.get(diffTime) != null) continue;
                    result.put(diffTime, info[2]);
                    break;
                }
                
                if((acbo.length() - j) < m.length()) break;
                
                if(m.charAt(1) == '#' && j < acbo.length()) {
                    if(m.charAt(0) != acbo.charAt(j) || m.charAt(1) != acbo.charAt(j+1)) continue;
                }
                //m의 첫 악보와 동일하다면 뒤 탐색
                boolean flag = true;
                int p = 0;
                int lastP = j;
                for(int k=0; k<m.length(); k++) {
                    if(lastP >= acbo.length()) {
                        flag = false;
                        break;
                    }
                    if(m.charAt(k) != acbo.charAt(lastP++)) {
                        flag = false;
                        break;
                    }
                }
                
                if(flag) {
                    //맞는 악보
                    if(lastP < acbo.length() && acbo.charAt(lastP) == '#') continue;
                    if(result.get(diffTime) != null) continue;
                    result.put(diffTime, info[2]);
                    break;
                }
            }
            
        }
        
        if(result.isEmpty()) {
            return "(None)";
        }
        else {
            int key = result.firstKey();
            return result.get(key);
        }
        
    }
    static int calcMiniutes(String start, String end) {
        String[] s = start.split(":");
        String[] e = end.split(":");
        
        int hours = Integer.parseInt(e[0]) - Integer.parseInt(s[0]);
        int minutes = Integer.parseInt(e[1]) - Integer.parseInt(s[1]);
        return (hours * 60) + minutes;
    }
}