import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

//BOJ_20210 파일 탐색기
public class Main {
    static int N, res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());    //문자열 개수

        /**
         * 1. 숫자가 있을 경우 한 단위로 비교
         * 2. 숫자끼리는 십진법으로 더 작은 것이 앞으로
         * 3. 같은 값일 경우 앞 부분의 0개수가 적은 것이 앞으로
         * **/

        List<String[]> list = new ArrayList<>();


        for(int i=0; i<N; i++) {
            String idx = br.readLine();
            List<String> item = new ArrayList<>();

            String temp = "";
            for(int j=0; j<idx.length(); j++) {
                char c = idx.charAt(j);
                if(Character.isDigit(c)) {
                    if(!temp.isEmpty()) {
                        item.add(temp);
                        temp = "";
                    }
                    String digit = String.valueOf(c);
                    while(++j < idx.length()) {
                        if(!Character.isDigit(idx.charAt(j))) {
                            j--;
                            break;
                        }
                        digit += idx.charAt(j);
                    }
                    item.add(digit);
                }
                else { //문자
                    temp += idx.charAt(j);
                }
            }
            if(!temp.isEmpty()) item.add(temp);
            list.add(item.toArray(new String[0]));
        }

//        for(String[] idx : list) {
//            System.out.println(Arrays.toString(idx));
//        }

        PriorityQueue<String[]> pq = new PriorityQueue<>(new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int leng = Math.min(o1.length, o2.length);

                for(int i=0; i<leng; i++) {
                    if(!o1[i].equals(o2[i])) {
                        //다를 경우 숫자인지 확인
                        if(Character.isDigit(o1[i].charAt(0)) && Character.isDigit(o2[i].charAt(0))) {
                            //숫자일 경우
                            //0이 아닌 지점 찾기 및 0개수 저장
                            int zeroCntO1 = 0, zeroCntO2 = 0;
                            int startO1 = 0, startO2 = 0;
                            for(int j=0; j<o1[i].length(); j++) {
                                if(o1[i].charAt(j) == '0') {
                                    startO1++;
                                    zeroCntO1++;
                                    continue;
                                }
                                break;
                            }

                            for(int j=0; j<o2[i].length(); j++) {
                                if(o2[i].charAt(j) == '0') {
                                    startO2++;
                                    zeroCntO2++;
                                    continue;
                                }
                                break;
                            }
                            //로직 체크 필요
                            if(o1[i].length() - startO1 != o2[i].length() - startO2) {
                                return (o1[i].length() - startO1) - (o2[i].length() - startO2);
                            }

                            while(startO1<o1[i].length() && startO2<o2[i].length()) {
                                if(o1[i].charAt(startO1) != o2[i].charAt(startO2)) {
                                    return Character.compare(o1[i].charAt(startO1), o2[i].charAt(startO2));
                                }
                                startO1++;
                                startO2++;
                            }
                            return zeroCntO1 - zeroCntO2;
                        }
                        else {
                            //숫자 || 문자일 경우
                            if(Character.isDigit(o1[i].charAt(0)) && !Character.isDigit(o2[i].charAt(0))) return -1;
                            else if(!Character.isDigit(o1[i].charAt(0)) && Character.isDigit(o2[i].charAt(0))) return 1;
                                //o1 o2가 다른 값이며, 문자일 경우
                            else {
                                if (o1[i].length() == o2[i].length()) {
                                    for(int j=0; j<o1[i].length(); j++) {
                                        int diff = Character.toLowerCase(o1[i].charAt(j)) - Character.toLowerCase(o2[i].charAt(j));
                                        if(diff != 0) {
                                            return diff;
                                        }
                                        else {
                                            //같은 문자열 일 경우
                                            diff = (int) o1[i].charAt(j) - (int) o2[i].charAt(j);
                                            if(diff != 0) return Character.compare(o1[i].charAt(j), o2[i].charAt(j));
                                        }
                                    }

                                }
                                else {
                                    int min = Math.min(o1[i].length(), o2[i].length());
                                    for(int j=0; j<min; j++) {
                                        int diff = Character.toLowerCase(o1[i].charAt(j)) - Character.toLowerCase(o2[i].charAt(j));
                                        if(diff != 0) {
                                            return diff;
                                        }
                                        else {
                                            //같은 문자열 일 경우
                                            diff = (int) o1[i].charAt(j) - (int) o2[i].charAt(j);
                                            if(diff != 0) return Character.compare(o1[i].charAt(j), o2[i].charAt(j));
                                        }
                                    }
                                    //비교 후 다 같을 경우
                                    return o1[i].compareTo(o2[i]);
                                }
                            }
                        }
                    }
                    //같을 경우 계속 비교
                }
                return o1.length - o2.length;
            }
        });

        for(String[] idx : list) {
            pq.offer(idx);
        }

        StringBuilder sb = new StringBuilder();

        while(!pq.isEmpty()) {
            for(String idx : pq.poll()) {
                sb.append(idx);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}