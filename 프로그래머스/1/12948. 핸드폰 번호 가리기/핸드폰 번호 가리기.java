class Solution {
    public String solution(String phone_number) {
        int leng = phone_number.length();
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i<leng; i++) {
            sb.append(leng - i < 5 ? phone_number.charAt(i) : "*");
        }
        return sb.toString();
    }
}