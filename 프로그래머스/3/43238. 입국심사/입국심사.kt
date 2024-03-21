class Solution {
    fun solution(n: Int, times: IntArray): Long {
        var answer: Long = 0
        
        var start : Long = 0;
        var end : Long = ((times.maxOrNull()?.toLong() ?: 0L) * n)
        
        while(start < end){
            val mid = (start + end) / 2
            var cnt = 0L
            for(i in times.indices){
                val time = times[i].toLong()
                cnt += (mid / time)
                if(cnt >= n) break
            }
            if(cnt >= n){
                end = mid
            } else {
                start = mid + 1
            }
            
            
        }
        
        return start
    }
}