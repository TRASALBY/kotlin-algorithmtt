package solve_1504

import java.io.StreamTokenizer
import java.util.*

private const val INF = 200000001

private lateinit var graph: Array<LinkedList<Edge>>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val e = input()

    graph = Array(n + 1) {
        LinkedList<Edge>()
    }


    repeat(e) {
        val a = input()
        val b = input()
        val c = input()
        graph[a].add(Edge(b, c))
        graph[b].add(Edge(a, c))
    }

    val v1 = input()
    val v2 = input()

    val startOne = dijkstra(1)
    val startV1 = dijkstra(v1)
    val startV2 = dijkstra(v2)

    val answer1 = startOne[v1] + startV1[v2] + startV2[n]
    val answer2 = startOne[v2] + startV2[v1] + startV1[n]

    val answer = minOf(answer1, answer2)

    if(answer >= INF) {
        println(-1)
    } else{
        println(answer)
    }

}

private fun dijkstra(start: Int): IntArray {
    val queue = PriorityQueue<Edge>()
    val distance = IntArray(graph.size) {
        INF
    }
    distance[0] = 0
    distance[start] = 0
    queue.add(Edge(start, 0))

    while (queue.isNotEmpty()) {
        val (node, dist) = queue.poll()

        if(distance[node] < dist) continue
        graph[node].forEach{
            val nextNode = it.end
            val nextDist = dist + it.dist

            if(nextDist < distance[nextNode]) {
                distance[nextNode] = nextDist
                queue.add(Edge(nextNode, nextDist))
            }
        }
    }
    return distance
}

private data class Edge(
    val end: Int,
    val dist: Int,
) : Comparable<Edge> {
    override fun compareTo(other: Edge) = dist - other.dist

}