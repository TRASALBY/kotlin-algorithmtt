import java.util.*

class Solution {
    fun solution(n: Int, costs: Array<IntArray>): Int {
        var answer = 0
        
        val visited = BooleanArray(n)
        
        val map = Array(n){
            IntArray(n) {
                Int.MAX_VALUE
            }
        }
        
        costs.forEach{
            val (start, end, cost) = it
            map[start][end] = cost
            map[end][start] = cost
        }
        
        val queue = PriorityQueue<Edge>()
        
        queue.add(Edge(0,0,0))
        
        while(queue.isNotEmpty()){
            val cur = queue.poll()
            if(visited[cur.end].not()){
                answer += cur.cost
                visited[cur.end] = true
                for(i in 0 until n){
                    if(map[cur.end][i] != Int.MAX_VALUE && visited[i].not()){
                        queue.add(Edge(cur.end, i, map[cur.end][i]))
                    }
                }
            }
            if(visited.all{it}) break
            
        }
        
        
        return answer
    }
}

data class Edge(val start: Int, val end: Int, val cost: Int) : Comparable<Edge>{
    override fun compareTo(other : Edge) = compareValuesBy(this, other, {it.cost})
}