import java.util.*;


class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        Queue<Integer> queue = new LinkedList<>();
        int[] needDays = new int[progresses.length];
        
        for(int i = 0; i < progresses.length; i++){
            int needProgress = (100 - progresses[i]);
            int needDay = needProgress / speeds[i];
            if(needProgress % speeds[i] != 0){
                needDay++;
            }
            queue.add(needDay);
        }
        
        ArrayList<Integer> answerList = new ArrayList<>();
        int needDay = queue.poll();
        int cnt = 1;
        
        while(!queue.isEmpty()){
            if(needDay >= queue.peek()){
                cnt += 1;
                queue.poll();
            } else {
                answerList.add(cnt);
                cnt = 1;
                needDay = queue.poll();
            }
        }
        answerList.add(cnt);
    
        
        answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++){
            answer[i] = answerList.get(i);
        }
        
        return answer;
    }
}