import java.util.*;
import java.io.*;

class Solution {
    
    HashMap<String, Integer> friendNum = new HashMap<>();
    
    int[] giftPoint;
    
    int[][] giftGraph;
    
    int[] nextMonthGiftCnt;
    
    StringTokenizer st;
    
    
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        int n = friends.length;
        for(int i = 0; i < n; i++){
            friendNum.put(friends[i], i);
        }
        
        giftPoint = new int[n];
        giftGraph = new int[n][n];
        
        nextMonthGiftCnt = new int[n];
        
        for(int i = 0; i < gifts.length; i++){
            st = new StringTokenizer(gifts[i]);
            String sender = st.nextToken();
            String receiver = st.nextToken();
            
            int senderNum = friendNum.get(sender);
            int receiverNum = friendNum.get(receiver);
            
            giftPoint[senderNum]++;
            giftPoint[receiverNum]--;
            
            giftGraph[senderNum][receiverNum]++;            
        }
        
        for(int i = 0; i < n - 1; i++){
            for(int j = i; j < n; j++){
                if(giftGraph[i][j] != 0 || giftGraph[j][i] != 0){
                    if(giftGraph[i][j] > giftGraph[j][i]) {
                        nextMonthGiftCnt[i]++;
                        continue;
                    } else if((giftGraph[i][j] < giftGraph[j][i])) {
                        nextMonthGiftCnt[j]++;    
                        continue;                
                    } 
                }                
                if(giftGraph[i][j] == giftGraph[j][i]){
                    if(giftPoint[i] > giftPoint[j]){
                        nextMonthGiftCnt[i]++;
                    } else if(giftPoint[i] < giftPoint[j]){
                        nextMonthGiftCnt[j]++;                    
                    }
                }
            }
        }
        
        for(int i = 0; i < n; i++){
            if(answer < nextMonthGiftCnt[i]){
                answer = nextMonthGiftCnt[i];
            }
        }
        
        
        
        return answer;
    }
}