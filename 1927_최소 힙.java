import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.*;
	public class Main {
		static int N;
		static int x;
		
	    public static void main(String[] args) throws IOException {
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        StringTokenizer st = new StringTokenizer(br.readLine());
	        StringBuilder sb = new StringBuilder();
	        N = Integer.parseInt(st.nextToken());
	        
	        //우선순위 큐 생성을 통한 최소힙 구현
	        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>();
	        
	        while(N-- != 0) {
	        	x = Integer.parseInt(br.readLine());
	        	if(x==0) { //입력정수 x가 0일 때
	        		if(minHeap.isEmpty()) sb.append(0 + "\n"); //최소힙이 비어있다면 0
	        		else sb.append(minHeap.poll() + "\n"); //값이 있다면 poll()한 최소값
	        	} else { //입력정수 x가 0이 아닐 때
	        		minHeap.offer(x); //최소힙에 해당되는 정수 offer()
	        	}
	        }
	        System.out.println(sb);
	    }
	}
