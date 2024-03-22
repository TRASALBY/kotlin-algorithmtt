import java.util.*

class Solution {
    fun solution(jobs: Array<IntArray>): Int {
        var answer = 0
        
        val workQueue = PriorityQueue<Job>(){a, b -> a.cost - b.cost}
        val inQueue = PriorityQueue<Job>(){a, b -> a.start - b.start}
        
        jobs.forEach{
            inQueue.add(Job(it[0],it[1]))
        }
        
        var time = 0
        while(inQueue.isNotEmpty() || workQueue.isNotEmpty()){
            while(inQueue.isNotEmpty() && inQueue.peek().start <= time){
                workQueue.add(inQueue.poll())
            }
            
            if(workQueue.isNotEmpty()){
                val job = workQueue.poll()
                time += job.cost
                answer += time - job.start
            } else {
                time = inQueue.peek().start
            }
        }
            
        return answer / jobs.size
    }
}

data class Job(val start: Int, val cost: Int)