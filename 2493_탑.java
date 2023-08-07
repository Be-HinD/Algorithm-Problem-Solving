import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main{
	static int N; //입력 개수
	static Stack<peek_idx> stack;
	static peek_idx idx;
	static int num = 1; //탑의 번호 인덱스
    public static void main(String[] args) throws IOException {
    	
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	
    	stack = new Stack<peek_idx>(); //peek_idx자료형 스택 선언
    	
    	st = new StringTokenizer(br.readLine());
    	for(int i=0; i<N;i++) { //입력 N만큼 반복
    		idx= new peek_idx(Integer.parseInt(st.nextToken()), num++); //탑의 정보를 받아와 객체 생성
    		if(stack.isEmpty()) { //스택이 비어있다면 탑이 없으므로 0 출력 후 현재 탑 push
    			System.out.print(0 + " ");
    			stack.push(idx);
    		} else { // 스택에 탑이 있는 경우
    			while(true) {
    				if(stack.isEmpty()) { //스택이 빌 때는 0 출력 후 현재 탑 push
    					System.out.print(0 + " ");
    	    			stack.push(idx);
    	    			break;
    				}
    				if(stack.peek().height >= idx.height) { //peek한 탑의 높이가 현재 탑의 높이보다 높은 경우 peek한 탑의 번호 출력 후 현재 탑 push
        				System.out.print(stack.peek().num + " ");
        				stack.push(idx);
        				break;
        			} else { //peek한 탑의 높이가 더 낮은 경우엔 뒤에 입력되는 탑들은 현재 탑에서 받을 수 있으니 pop으로 제거
    					stack.pop();
        			} 
    			}
    		}
    	}
    }
}
class peek_idx{ //탑의 정보를 저장할 수 있는 객체
	int num; //탑의 번호 <1부터 시작>
	int height; //탑의 높이 <입력값>
	peek_idx(int height, int num){ //생성자로 초기화
		this.height = height;
		this.num = num;
	}
}
