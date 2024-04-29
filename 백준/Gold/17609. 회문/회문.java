import java.io.*;
import java.util.*;

//BOJ_17609 회문
public class Main {
    static int T;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();

        T = Integer.parseInt(br.readLine());

        for(int tc=0; tc<T; tc++) {
            String input = br.readLine();
            int l = 0;
            int r = input.length()-1;

            int deleteCnt = 0;

            boolean palind = false;
            boolean harf = false;
            boolean common = false;

            int prev = l;
            int next = r;

            int roofCnt = 0;

            while(l <= r) {

                if(input.charAt(l) == input.charAt(r)) {
                    //같은 문자열일 경우 패쓰
                }
                else {
                    //왼쪽 무시의 경우
                    if(input.charAt(l+1) == input.charAt(r)) {
                        if(deleteCnt == 0) {
                            prev = l;
                            next = r-1;
                        }
                        deleteCnt++;
                        l++;

                        if(deleteCnt > 1) {
                            if(roofCnt > 0) {

                                common = true;
                                break;
                            }
                            l = prev;
                            r = next;
                            roofCnt++;
                            deleteCnt = 1;
                            continue;
                        }
                        continue;

                    }
                    //오른쪽 무시의 경우
                    else if(input.charAt(l) == input.charAt(r-1)) {
                        if(deleteCnt == 0) {
                            prev = l+1;
                            next = r;
                        }
                        deleteCnt++;
                        r--;

                        if(deleteCnt > 1) {
                            if(roofCnt > 0) {
                                common = true;
                                break;
                            }
                            l = prev;
                            r = next;
                            roofCnt++;
                            deleteCnt = 1;
                            continue;
                        }
                        continue;
                    }
                    else {
                        //둘다 안맞을 경우
                        //왼쪽 오른쪽 분기쳐야되네?
                        if(roofCnt == 0) {
                            l = prev;
                            r = next;
                            roofCnt++;
                            deleteCnt = 1;
                            continue;
                        }
                        else {
                            common = true;
                            break;
                        }
                    }
                }

                l++;
                r--;

            }

            if(common) {
                sb.append(2).append("\n");
            }
            else if(deleteCnt == 1) {
                sb.append(1).append("\n");
            }
            else {
                sb.append(0).append("\n");
            }

        }

        System.out.println(sb);

    }
}