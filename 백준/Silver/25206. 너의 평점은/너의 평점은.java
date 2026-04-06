import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<String, Double> gradeMap = new HashMap<>();
        gradeMap.put("A+", 4.5);
        gradeMap.put("A0", 4.0);
        gradeMap.put("B+", 3.5);
        gradeMap.put("B0", 3.0);
        gradeMap.put("C+", 2.5);
        gradeMap.put("C0", 2.0);
        gradeMap.put("D+", 1.5);
        gradeMap.put("D0", 1.0);
        gradeMap.put("F",  0.0);

        double gradeSum = 0;    //총 학점
        double scoreSum = 0;    //전공과목별 합

        for(int i=0; i<20; i++) {
            st = new StringTokenizer(br.readLine());

            st.nextToken(); //delete

            double gradeScore = Double.parseDouble(st.nextToken()); //학점
            String grade = st.nextToken(); //등급

            if("P".equals(grade)) {
                continue;
            }

            gradeSum += gradeScore;

            double gradeVal = gradeMap.get(grade);  //과목평점

            scoreSum += gradeScore * gradeVal;

        }

        double res = scoreSum / gradeSum;
        System.out.printf("%.6f", res);
    }
}