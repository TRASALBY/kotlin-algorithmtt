import java.util.*

class Solution {
    fun solution(k: Int, score: IntArray): IntArray {
        val queue = PriorityQueue<Int>()
        
        val answer = IntArray(score.size){ idx ->
            queue.add(score[idx])
            if(queue.size > k){
                queue.poll()
            }
            queue.peek()
        }
        

        return answer
    }
}