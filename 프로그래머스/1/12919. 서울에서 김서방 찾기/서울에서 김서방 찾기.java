class Solution {
    public String solution(String[] seoul) {
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<seoul.length; i++) {
            if("Kim".equals(seoul[i])) {
                sb.append("김서방은 ").append(i).append("에 있다");
            }
        }
        return sb.toString();
    }
}