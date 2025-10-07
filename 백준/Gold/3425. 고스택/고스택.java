import java.io.*;
import java.util.*;

//BOJ_3425 (Data Structure) 문제 이해가 좀 난해함.
public class Main {
    static final int MAX = 1000000000;
    static int t, n, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /**
         * DIV의 경우 : 피연산자 중 음수가 '한개' 있다면 음수, 그 외는 모두 양수
         * MOD의 경우 : 피제수의 부호를 따라감.
         * ERROR의 경우 : 수가 부족해서 연산 수행 못할 때, Zero Divide, 연산값 >= 10^9
         * 문제설명
         * END가 나오기 전까지의 Command를 저장
         * END 이후의 초기값 Vi에 대해서 각 Command 동일하게 실행
         * **/

        boolean loopFlag = true;
        while(true) {
            List<String> cmdList = new ArrayList<>();
            while(true) {
                st = new StringTokenizer(br.readLine());
                String cmd = st.nextToken();
                if("QUIT".equals(cmd)) {
                    loopFlag = !loopFlag;
                    break;
                }
                if("END".equals(cmd)) break;

                if("NUM".equals(cmd)) cmd += " " + st.nextToken();
                cmdList.add(cmd);
            }

            if(!loopFlag) break;

            int cnt = Integer.parseInt(br.readLine());
            for(int i=0; i<cnt; i++) {
                int idx = Integer.parseInt(br.readLine());
                proc(cmdList, idx);
            }
            System.out.println(br.readLine());
        }
    }

    static void proc(List<String> list, int idx) {
        Stack<Long> stack = new Stack<>();
        stack.push((long) idx);

        boolean flag = true;
        for(String cmd : list) {
            if(!goStack(stack, cmd)) {
                flag = false;
                break;
            }
        }

        if(flag && stack.size() == 1) {
            System.out.println(stack.pop());
        }
        else {
            System.out.println("ERROR");
        }
    }

    static boolean goStack(Stack<Long> st, String cmd) {

        if(cmd.contains("NUM")) {
            String[] arr = cmd.split(" ");
            st.push((long) Integer.parseInt(arr[1]));
        }
        else if(cmd.equals("POP")) {
            if(st.isEmpty()) return false;
            st.pop();
        }
        else if(cmd.equals("INV")) {
            if(st.isEmpty()) return false;
            st.push(st.pop() * -1);
        }
        else if(cmd.equals("DUP")) {
            if(st.isEmpty()) return false;
            long idx = st.pop();
            st.push(idx);
            st.push(idx);
        }
        else if(cmd.equals("SWP")) {
            if(st.size() < 2) return false;
            long first = st.pop();
            long second = st.pop();
            st.push(first);
            st.push(second);
        }
        else if(cmd.equals("ADD")) {
            if(st.size() < 2) return false;
            long first = st.pop();
            long second = st.pop();
            if(Math.abs(first + second) > MAX) return false;
            st.push(first + second);
        }
        else if(cmd.equals("SUB")) {
            if(st.size() < 2) return false;
            long first = st.pop();
            long second = st.pop();
            if(Math.abs(second-first) > MAX) return false;
            st.push(second - first);
        }
        else if(cmd.equals("MUL")) {
            if(st.size() < 2) return false;
            long first = st.pop();
            long second = st.pop();
            if(Math.abs(first * second) > MAX) return false;
            st.push(first * second);
        }
        else if(cmd.equals("DIV")) {
            //DIV의 경우 : 피연산자 중 음수가 '한개' 있다면 음수, 그 외는 모두 양수
            if(st.size() < 2) return false;
            long first = st.pop();
            long second = st.pop();
            if(first == 0) return false;
            if((first >=0 && second >= 0) || (first<0 && second<0)) {
                st.push(Math.abs(second) / Math.abs(first));
            }
            else {
                st.push((Math.abs(second) / Math.abs(first)) * -1);
            }
        }
        else if(cmd.equals("MOD")) {
            //MOD의 경우 : 피제수의 부호를 따라감.
            if(st.size() < 2) return false;
            long first = st.pop();
            long second = st.pop();
            if(first == 0) return false;

            if(second<0) {
                st.push(Math.abs(second) % Math.abs(first) * -1);
            }
            else {
                st.push((Math.abs(second) % Math.abs(first)));
            }
        }

        return true;
    }
}