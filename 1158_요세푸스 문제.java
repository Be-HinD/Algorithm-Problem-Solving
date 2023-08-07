import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
public class Main{
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	StringBuffer sb = new StringBuffer();
    	
    	int N = Integer.parseInt(st.nextToken());
    	int M = Integer.parseInt(st.nextToken());
    	
    	Queue<Integer> queue = new LinkedList<>(); //자료구조 큐 사용
    	for(int i=1; i<=N; i++) {
    		queue.offer(i); //큐에 데이터 offer
    	}
    	sb.append("<");
    	while(queue.size() != 1) { //큐의 사이즈가 1이될 때 까지 진행
    		for(int j=0; j<M-1;j++) { //M-1번 큐의 앞에 부분 값을 offer
    			queue.offer(queue.poll());
    		}
    		sb.append(queue.poll() + ", "); //M번째의 데이터 poll
    	}
    	sb.append(queue.poll() + ">");
    	bw.write(sb.toString() + "\n");
    	bw.flush();
    	bw.close();
    	br.close();
    }
}
