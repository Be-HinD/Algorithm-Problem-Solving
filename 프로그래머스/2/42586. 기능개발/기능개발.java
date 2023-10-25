import java.util.*;
class Solution {
    static int[] arr, res;
    static ArrayList<Integer> list = new ArrayList<>();
    public int[] solution(int[] progresses, int[] speeds) {
        arr = new int[progresses.length];
        
        //작업을 마치는 데 걸리는 시간값 계산
        for(int i=0; i<progresses.length; i++) {
            int task = 100 - progresses[i];
            int time = speeds[i];
            if(task%time != 0) arr[i] = (task/time) + 1;
            else arr[i] = task/time;   
        }
        
        int cnt = 1;
        int pre = arr[0]; //초기값
        for(int i=1; i<arr.length; i++) {
            //다음 값이 더 낮다면 cnt만 증가
            if(pre >= arr[i]) {
                cnt++;
            } else { //다음 값이 더 높다면 값 추가 및 pre, cnt 갱신
                list.add(cnt);
                pre = arr[i];
                cnt = 1;
            }
        }
        list.add(cnt); //마지막 값 추가
        
        res = new int[list.size()]; //리스트 값들 배열로 변환
        for(int i=0; i<list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}