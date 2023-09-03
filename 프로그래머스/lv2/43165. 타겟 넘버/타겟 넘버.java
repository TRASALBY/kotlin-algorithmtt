class Solution {
    int answer;
    public int solution(int[] numbers, int target) {
        answer = 0;
        
        backTrack(1, numbers, target, numbers[0]);
        backTrack(1, numbers, target, -numbers[0]);
        
        
        return answer;
    }
    
    
    public void backTrack(int idx, int[] numbers, int target, int now){
        if(idx == numbers.length){
            if(now == target){
                answer++;
            }
            return;
        }
        backTrack(idx+1, numbers, target, now + numbers[idx]);
        backTrack(idx+1, numbers, target, now - numbers[idx]);
        
    }
}