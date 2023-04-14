package solve_1916

import java.io.StreamTokenizer
import java.util.*

private const val INF = Int.MAX_VALUE
private lateinit var graph : Array<LinkedList<Edge>>
private lateinit var distance : IntArray

private fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()

    graph = Array(n+1){
        LinkedList<Edge>()
    }

    repeat(m){
        val start = input()
        val end = input()
        val cost = input()

        graph[start].add(Edge(cost, end))
    }
    distance = IntArray(n+1) {
        INF
    }

    val start = input()
    val end = input()

    dij(start)

    println(distance[end])

}

private fun dij(start: Int){
    val queue = PriorityQueue<Edge>()
    queue.add(Edge(0,start))

    while(queue.isNotEmpty()){
        val (dist, num) = queue.poll()

        if(distance[num] < dist) continue

        for(edge in graph[num]){
            val newDist = dist + edge.dist
            if(newDist < distance[edge.num]){
                distance[edge.num] = newDist
                queue.add(Edge(newDist, edge.num))
            }
        }
    }


}

private data class Edge(val dist: Int, val num: Int) : Comparable<Edge>{
    override fun compareTo(other: Edge) = this.dist - other.dist
}


