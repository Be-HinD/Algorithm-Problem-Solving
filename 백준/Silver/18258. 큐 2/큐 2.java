import java.io.*;
import java.util.*;

//BOJ_18258 큐 2
public class Main {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        ArrayDeque<Integer> ad = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<N; i++) {
            String[] oper = br.readLine().split(" ");

            switch (oper[0]) {
                case "push":
                    ad.addLast(Integer.parseInt(oper[1]));
                    break;
                case "pop":
                    if(isZero(ad)) {
                        sb.append(-1).append("\n");
                        break;
                    }
                    sb.append(ad.pollFirst()).append("\n");
                    break;
                case "size":
                    sb.append(ad.size()).append("\n");
                    break;
                case "empty":
                    if(isZero(ad)) {
                        sb.append(1).append("\n");
                        break;
                    }
                    sb.append(0).append("\n");
                    break;
                case "front":
                    if(isZero(ad)) {
                        sb.append(-1).append("\n");
                        break;
                    }
                    sb.append(ad.peekFirst()).append("\n");
                    break;
                case "back":
                    if(isZero(ad)) {
                        sb.append(-1).append("\n");
                        break;
                    }
                    sb.append(ad.peekLast()).append("\n");
                    break;
            }

        }

        System.out.println(sb);
    }

    private static boolean isZero(ArrayDeque<Integer> ad) {
        if(ad.isEmpty()) return true;
        return false;
    }
}