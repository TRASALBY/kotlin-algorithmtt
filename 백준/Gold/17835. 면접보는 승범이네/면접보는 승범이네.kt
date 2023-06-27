import java.io.StreamTokenizer
import java.util.LinkedList
import java.util.PriorityQueue


private const val INF = Long.MAX_VALUE
private lateinit var edgeList: Array<LinkedList<Edge>>

fun main() = StreamTokenizer(System.`in`.bufferedReader()).run {
    fun input(): Int {
        nextToken()
        return nval.toInt()
    }

    val n = input()
    val m = input()
    val k = input()

    edgeList = Array(n + 1) {
        LinkedList<Edge>()
    }

    repeat(m) {
        val u = input()
        val v = input()
        val c = input().toLong()
        edgeList[v].add(Edge(u, c))
    }


    val goalList = IntArray(k) {
        input()
    }

    val dist = dijkstra(goalList, n)

    var max = Long.MIN_VALUE
    var maxIndex = -1


    for(i in 1..n){
        if(dist[i] > max && dist[i] != INF){
            max = dist[i]
            maxIndex = i
        }
    }

    println(maxIndex)
    println(max)

}

private fun dijkstra(goalList: IntArray, n: Int): LongArray {
    val dist = LongArray(n + 1) { INF }
    val queue = PriorityQueue<Edge>()

    goalList.forEach {
        queue.add(Edge(it, 0))
        dist[it] = 0
    }

    dist[0] = 0

    while (queue.isNotEmpty()) {
        val (node, cost) = queue.poll()

        if(dist[node] < cost) continue
        edgeList[node].forEach{
            val nextNode = it.end
            val nextDist = cost + it.cost

            if(nextDist < dist[nextNode]) {
                dist[nextNode] = nextDist
                queue.add(Edge(nextNode, nextDist))
            }
        }
    }

    return dist
}


private data class Edge(
    val end: Int,
    val cost: Long
) : Comparable<Edge> {
    override fun compareTo(other: Edge): Int =
        (this.cost - other.cost).toInt()
}