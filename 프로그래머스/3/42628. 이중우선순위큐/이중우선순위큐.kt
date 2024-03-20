import java.util.*

class Solution {
    fun solution(operations: Array<String>): IntArray {
        val minQueue = PriorityQueue<Int>()
        val maxQueue = PriorityQueue<Int>()
        
        operations.forEach{
            val(type, numS) = it.split(" ")
            val num = numS.toInt()
            if(type == "I"){
                minQueue.add(num)
                maxQueue.add(-num)
            } else {
                if(minQueue.isEmpty()) return@forEach
                if(num == 1){
                    minQueue.remove(-maxQueue.poll())
                } else {
                    maxQueue.remove(-minQueue.poll())
                }
            }
        }
        var answer = intArrayOf(-(maxQueue.poll() ?: 0), minQueue.poll() ?: 0)
        return answer
    }
}