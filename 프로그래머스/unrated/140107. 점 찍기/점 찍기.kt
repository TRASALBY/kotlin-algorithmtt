class Solution {
    fun solution(k: Int, d: Int): Long {
        var answer: Long = 0
        val newd = d.toLong()
        val newK = k.toLong()
        for(x in 0L..newd step(newK)){
            val y = kotlin.math.sqrt((newd * newd - x * x).toDouble()).toLong()
            answer+= y / k + 1
        }
        return answer
    }
}