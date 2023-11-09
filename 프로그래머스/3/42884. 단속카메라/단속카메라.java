import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        //진입,진출 배열의 진출시간을 기준으로 오름차순 정렬
        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        
        //제일 시작 부분에 카메라를 설치
        int camera = -30000;
        
        for(int i=0; i<routes.length; i++) {
            if(camera < routes[i][0]) {
                //현재 설치된 카메라보다 이번 차량의 진입시간이 클 경우
                //== -15 지점에 1개를 설치하였는데
                //다음 차량의 진입시간이 -18이고, 오름차순 정렬에 의해
                //다음 차량의 진출시간이 -15보다 더 크다는걸 알고 있기에
                //넘어가고
                //이 후 -14의 진입시간을 가진 차와 비교 시 카메라를 새롭게 설치.
                camera = routes[i][1];
                answer++;
            }
        }
        
        return answer;
    }
}