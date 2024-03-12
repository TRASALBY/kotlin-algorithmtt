import java.util.*

class Solution {
    fun solution(n: Int, roads: Array<IntArray>, sources: IntArray, destination: Int): IntArray {
        var answer= IntArray(sources.size)
        val edgeList = Array<ArrayList<Int>>(n+1){
            arrayListOf()
        }
        
        
        
        roads.forEach{ (start, end) ->
            edgeList[start].add(end)
            edgeList[end].add(start)
        }
        
        
        sources.forEachIndexed{ idx, start ->
            val queue: PriorityQueue<Edge> = PriorityQueue()
            val dist = IntArray(n + 1){
                Int.MAX_VALUE
            }
            val visited = BooleanArray(n + 1)
            dist[start] = 0
            visited[start] = true
            
            queue.add(Edge(start, 0))
            
            var find = false
            while(queue.isNotEmpty()){
                val cur = queue.poll()
                if(cur.end == destination) break
                for(i in edgeList[cur.end].indices){
                    val next = edgeList[cur.end][i]
                    if(visited[next]) continue
                    if(dist[next] > dist[cur.end] + 1){
                        dist[next] = dist[cur.end] + 1
                        if(next == destination){
                            find = true
                            break
                        }
                        queue.add(Edge(next, dist[next]))
                        visited[next] = true
                    }
                }
                if(find) break
            }
            
            val goalDist = dist[destination]
            answer[idx] = if(goalDist == Int.MAX_VALUE) -1 else goalDist
        }
        
        
        return answer
        
    }
}

data class Edge(val end: Int, val cost: Int) : Comparable<Edge>{
    override fun compareTo(other: Edge): Int = compareValuesBy(this, other, {it.cost})
}


// 플로이드 워셜로는 시간 초과
//     val dist = Array(n + 1) {
//             IntArray(n+1){
//                 Int.MAX_VALUE
//             }
//         }
        
//         for(i in 1..n){
//             dist[i][i] = 0
//         }
        
//         roads.forEach{ (start, end) ->
//             dist[start][end] = 1
//             dist[end][start] = 1
//         }
        
//         for(k in 1..n){
//             for(i in 1..n){
//                 if(dist[i][k] == Int.MAX_VALUE) continue
//                 for(j in 1..n){
//                     if(dist[k][j] == Int.MAX_VALUE) continue
//                     if(dist[i][j] > dist[i][k] + dist[k][j]){
//                         dist[i][j] = dist[i][k] + dist[k][j]
//                     }
//                 }
//             }
//         }
        
//         answer = sources.map{
//             if(dist[it][destination] == 
//                 Int.MAX_VALUE) -1 else dist[it][destination]
//         }.toIntArray()
//         return answer
//     }