
import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        ArrayList<Integer> tempAnswer = new ArrayList<>();
        StringTokenizer st = new StringTokenizer(today, ".");
        
        int year = Integer.parseInt(st.nextToken());
        int month = Integer.parseInt(st.nextToken());
        int days = Integer.parseInt(st.nextToken());
        
        Date todayDate = new Date(year, month, days);
        
        HashMap<String, Integer> termHash = new HashMap<>();
        
        for(int i = 0; i < terms.length; i++){
            st = new StringTokenizer(terms[i]);
            String a = st.nextToken();
            int b = Integer.parseInt(st.nextToken());
            termHash.put(a,b);
        }
        
        for(int i = 0; i < privacies.length; i++){
            String p = privacies[i];
            String[] pArr = p.split(" ");
            String d = pArr[0];
            String type = pArr[1];
            st = new StringTokenizer(d, ".");
            int pYear = Integer.parseInt(st.nextToken());
            int pMonth = Integer.parseInt(st.nextToken());
            int pDays = Integer.parseInt(st.nextToken());
            
            Date pDate = new Date(pYear, pMonth, pDays);
            
            Date validity = pDate.getValidity(termHash.get(type));
            
            if(todayDate.checkValidity(validity)){
                tempAnswer.add(i+1);
            }
        }
        
        answer = new int[tempAnswer.size()];
        for(int i = 0; i < answer.length; i++){
            answer[i] = tempAnswer.get(i);
        }
        
        return answer;
    }
}

class Date{
    int year, month, days;
    
    
    public String toString(){
        return String.format("%d %d %d", year, month, days);
    }
    
    Date(int year, int month, int days){
        this.year = year;
        this.month = month;
        this.days = days;
    }
    
    Date getValidity(Integer validityMonth){
        int newDays = days;
        int newMonth = month + validityMonth;
        int newYear = year;
        
        if(newMonth > 12){
            newYear += newMonth / 12;
            newMonth %= 12;
        }
        if (newMonth == 0) {
            newMonth = 12;
            newYear -= 1;
        }
        return new Date(newYear, newMonth, newDays);
    }
    
    boolean checkValidity(Date other){
        if(this.year < other.year) {
                return false;
            } else if (this.year > other.year) {
                return true;
            } else {
                if(this.month < other.month) {
                    return false;
                } else if (this.month > other.month) {
                    return true;
                } else {
                    if(this.days < other.days) {
                        return false;
                    } else if(this.month > other.month) {
                        return true;
                    }
                    else {
                        return true;
                    }
                }
            }
        }

}