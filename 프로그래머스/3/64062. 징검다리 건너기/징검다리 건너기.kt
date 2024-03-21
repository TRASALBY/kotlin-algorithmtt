class Solution {
    fun solution(stones: IntArray, k: Int): Int {
        
        fun search(start: Int, end: Int): Int{
            if(start >= end){
                return start
            }

            val mid = (start + end) / 2
            var cnt = 0
            for(i in stones.indices){
                val stone = stones[i]                
                if(stone - mid <= 0){
                    cnt++
                    if(cnt >= k){
                        return search(start, mid)
                    }
                }else {
                    cnt = 0
                }
            }
            return search(mid + 1, end)
        }
        
        var start = 0
        var end = 200_000_000
        
        

        return search(start, end)
        
    }
    
    

}