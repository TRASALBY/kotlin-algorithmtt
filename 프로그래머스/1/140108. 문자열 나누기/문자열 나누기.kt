class Solution {
    fun solution(s: String): Int {
        var answer: Int = 0
        
        var xCnt = 0
        var otherCnt = 0
        var x : Char = ' '
        
        for(i in 0 until s.length){
            if(xCnt == 0){
                x = s[i]
                xCnt++
                continue
            }
            if(s[i] != x){
                otherCnt++
            } else {
                xCnt++
            }
            
            if(xCnt == otherCnt){
                answer++
                xCnt = 0
                otherCnt = 0
            }
        }
        if(xCnt != 0){
            answer++
        }
        
        return answer
    }
}