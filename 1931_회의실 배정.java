import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Test {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        //입력
        List<room> list = new ArrayList<room>();
        for(int i=0; i<N; i++) {
        	st = new StringTokenizer(br.readLine());
        	list.add(new room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        //회의 종료시간을 기준으로 정렬하도록 compare 재정의
        Collections.sort(list, new Comparator<room>() {
        	@Override
        	public int compare(room o1, room o2) {
        		if(o1.e == o2.e) {
        			return o1.s - o2.s;
        		}
        		return o1.e - o2.e;
        	}
		});
        
        int idx = 0; //index값
        int end = list.get(idx).e; //현재기준 마지막으로 추가된 회의의 종료시간
        int ans = 1; //출력값
        
        room r; //객체 생성
        while(++idx < N) { //입력길이만큼 반복
        	r = list.get(idx); //idx번째 room객체
        	if(end <= r.s) { //마지막 종료시간보다 현재 회의의 시작시간이 같거나 크다면 추가가능 
        		end = r.e; //종료시간 갱신
        		ans++; //회의 추가
        	}
        }
        System.out.println(ans);
        
    }
}

class room { //입력으로 들어오는 회의의 시작시간 및 종료시간을 담을 클래스
	int s;
	int e;
	room(int s, int e) {
		this.s = s;
		this.e = e;
	}
}
