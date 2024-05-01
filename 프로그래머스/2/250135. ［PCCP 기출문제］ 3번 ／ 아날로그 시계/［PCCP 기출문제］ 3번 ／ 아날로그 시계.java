import java.util.*;

class Solution {
    // 초침 1분 -> 360도, 1초 -> 6도
    // 분침 1시간 360도. 1분 -> 6도, 1초 -> 1/10도
    // 시침 1시간 -> 30도, 1분 1/2도, 1초 -> 1/120 
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = 0;
        
        Time now = new Time(h1,m1,s1);
        Time end = new Time(h2,m2,s2);
        
        int cnt = 0;
        double[] lastAngle = now.getAngle();
        
        if(lastAngle[0] == lastAngle[1] && lastAngle[1] == lastAngle[2]){
            answer++;
        }
        
        while(!now.equals(end)){
            now.addSec(1);
            double[] angle = now.getAngle();
            
            // if(cnt++ < 5){
            //     System.out.println(now);
            //     System.out.println(Arrays.toString(angle)); 
            //     System.out.println(Arrays.toString(lastAngle)); 
            // }
            
            if((lastAngle[2] < lastAngle[0] && angle[0] <= angle[2]) ||
              ((lastAngle[2] < lastAngle[0] && angle[2] == 0))){
                // System.out.println("시 초과");
                answer++;
            }
            
            if(
                (lastAngle[2] < lastAngle[1] && angle[1] <= angle[2]) || 
                (lastAngle[2] < lastAngle[1] && angle[2] == 0)
            ){
                // System.out.println("분 초과");
                answer++;
            }
            if(angle[0] == angle[1]  && angle[1] == angle[2]){
                // System.out.println("12시");
                answer--;
            }
        
            
            lastAngle = angle;
        }
        
        
        return answer;
        
        
    }
    
    

    class Time {
        double h;
        double m;
        double s;
        
        Time(int h, int m, int s){
            this.h = h;
            this.m = m;
            this.s = s;
        }
        
         
        void addSec(int sec){
            s += sec;
            if(s >=60){
                s -= 60;
                m += 1;
            }
            if(m >= 60){
                m -= 60;
                h+=1;
            }
            if(h >= 24){
                h-=24;
            }
        }
        
        boolean equals(Time other){
            return this.h == other.h && this.m == other.m && this.s == other.s;
        }
        
        double[] getAngle(){
            double[] angle = new double[3];
            angle[0] = (h % 12) * 30 + (m * 0.5) + (s / 120);
            angle[1] = (m * 6) + (s / 10);
            angle[2] = s * 6;

            return angle;
        }
        
        public String toString(){
            return String.format("h: %f m: %f s: %f", h, m, s);
        }
    }
}