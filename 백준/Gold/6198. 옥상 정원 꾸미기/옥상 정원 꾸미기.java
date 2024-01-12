import java.io.*;
import java.util.*;

//BOJ_6198 옥상 정원 꾸미기
public class Main {
    static long res;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());

        Stack<Integer> stack = new Stack<>();

        stack.push(Integer.parseInt(br.readLine()));    //첫 값

        for(int i=1; i<N; i++) {
            int idx = Integer.parseInt(br.readLine());

            if(stack.peek() > idx) {    //현재 빌딩보다 이전 빌딩이 더 높다면 현재 값 추가
                res += stack.size();
                stack.push(idx);
            }
            else {      //현재 빌딩(idx)이 이전 빌딩보다 같거나 더 낮을 경우 (ex : 10 - 3(idx))

                while(!stack.isEmpty()) {
                    //스택이 빌 때까지 한 개씩 꺼내서 현재 빌딩의 높이보다 더 큰 빌딩을 만날 때 까지 비교하면서 스택 삭제
                    int diff = stack.peek();
                    if(diff > idx) {
                        break;
                    }
                    stack.pop();
                }

                if(stack.isEmpty()) {       //현재 빌딩(idx)보다 더 높은 빌딩이 없을 경우
                    stack.push(idx);
                    continue;
                }

                //현재 빌딩(idx)보다 더 높은 빌딩을 만났을 경우
                res += stack.size();
                stack.push(idx);
            }

        }

        System.out.println(res);

    }
}